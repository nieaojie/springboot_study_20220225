# j2cache的整合过程
>j2cache整合redis和ehcache
## 一、导包
> 加注解的表示新需要的依赖，不加注解表示基础环境依赖。
```xml
<dependencies>
  <!--ehcache-->
  <dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
  </dependency>

  <!--j2cache与springboot整合的包 -->
  <dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-spring-boot2-starter</artifactId>
    <version>2.8.0-release</version>
  </dependency>
  
  <!-- j2cache核心包 -->
  <dependency>
    <groupId>net.oschina.j2cache</groupId>
    <artifactId>j2cache-core</artifactId>
    <version>2.8.4-release</version>
  </dependency>

  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```
## 二、添加j2cache的配置
###1、在application.yml文件中配置j2cache的路径。
```yml
server:
  port: 80
j2cache:
  config-location: j2cache.properties

```
###2、创建j2cache的配置文件
找到j2cache的核心包 `net.oschina.j2cache:j2cache-core:2.8.4-release` ，里面有配置文件。
把`j2cache.properties`配置复制到项目的配置路径下（可作为学习使用）。

下面是自定义的`j2cache.properties`配置：
```properties
# 一级缓存：供应商+配置
j2cache.L1.provider_class=ehcache
ehcache.configXml=ehcache.xml
#设置是否启用二级缓存：默认是开启的
j2cache.l2-cache-open=true
# 二级缓存：供应商+配置
j2cache.L2.provider_class=net.oschina.j2cache.cache.support.redis.SpringRedisProvider
j2cache.L2.config_section=redis
redis.hosts=localhost:6379
# 一级缓存中的数据如何到达二级缓存(通过发布订阅的方式来获取ehcache的数据)
j2cache.broadcast=net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
redis.mode=single
redis.namespace=j2cache #j2cache:smsCode:15936553896
```
ehcache的配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
  <diskStore path="D:\ehcache" />

  <!--默认缓存策略 -->
  <!-- external：是否永久存在，设置为true则不会被清除，此时与timeout冲突，通常设置为false-->
  <!-- diskPersistent：是否启用磁盘持久化-->
  <!-- maxElementsInMemory：最大缓存数量-->
  <!-- overflowToDisk：超过最大缓存数量是否持久化到磁盘-->
  <!-- timeToIdleSeconds：最大不活动间隔，设置过长缓存容易溢出，设置过短无效果，可用于记录时效性数据，例如验证码-->
  <!-- timeToLiveSeconds：最大存活时间-->
  <!-- memoryStoreEvictionPolicy：缓存清除策略-->
  <defaultCache
    eternal="false"
    diskPersistent="false"
    maxElementsInMemory="1000"
    overflowToDisk="false"
    timeToIdleSeconds="60"
    timeToLiveSeconds="60"
    memoryStoreEvictionPolicy="LRU" />

  <!-- name:就是在@Cachable中指定的那个value值 -->
  <cache
    name="smsCode"
    eternal="false"
    diskPersistent="false"
    maxElementsInMemory="1000"
    overflowToDisk="false"
    timeToIdleSeconds="10"
    timeToLiveSeconds="10"
    memoryStoreEvictionPolicy="LRU" />

</ehcache>
```

## 三、代码中使用
```java
@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;
    //1.获取CacheChannel
    @Autowired
    private CacheChannel cacheChannel;
    
    @Override
    public String sendCodeToSMS(String tel) {
        String code = codeUtils.generateCode(tel);
        //2.使用缓存
        cacheChannel.set("smsCode", tel, code);
        return code;
    }

    @Override
    public boolean check(SMSCode smsCode) {
        String code = cacheChannel.get("smsCode", smsCode.getTel()).asString();
        return smsCode.getCode().equals(code);
    }
}

```