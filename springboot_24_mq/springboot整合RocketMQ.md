# springboot整合RocketMQ

> 环境配置：[Windows下安装RocketMQ](./Windows下安装RocketMQ.md)

## 一、导依赖

```xml
<!-- rocketmq -->
<dependency>
  <groupId>org.apache.rocketmq</groupId>
  <artifactId>rocketmq-spring-boot-starter</artifactId>
  <version>2.2.1</version>
</dependency>
```

## 二、做配置

application.yml

```yml
server:
  port: 80

rocketmq:
  name-server: localhost:9876
  producer:
    group: group_rocketmq
```

**说明：**

需要配置一个默认的`rocketmq.producer.group`，否则会报错。

```text
***************************
APPLICATION FAILED TO START
***************************

Description:

Field rocketMQTemplate in com.example.rocketmq.service.impl.MessageServiceRocketMQImpl required a bean of type 'org.apache.rocketmq.spring.core.RocketMQTemplate' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)


Action:

Consider defining a bean of type 'org.apache.rocketmq.spring.core.RocketMQTemplate' in your configuration.
```

## 三、写代码

### 1、写两个接口。

`MessageRocketMQService.java`

```java
public interface MessageRocketMQService {
    public void sendMessage(String id);
}
```

`OrderRocketMQService.java`

```java
public interface OrderRocketMQService {
    public void order(String id);
}
```

### 2、实现这两个接口。

`MessageServiceRocketMQImpl.java`

```java

@Service
public class MessageServiceRocketMQImpl implements MessageRocketMQService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMessage(String id) {
        //发送的是同步消息（不太常用）
        //rocketMQTemplate.convertAndSend("order_id", id);
        //发送异步消息（常用）
        //回调函数
        SendCallback callback = new SendCallback() {
            //成功的回调函数
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("短信发送成功！！！");
            }

            //失败的回调函数
            @Override
            public void onException(Throwable throwable) {
                System.out.println("短信发送失败！！！");
            }
        };
        rocketMQTemplate.asyncSend("order_id", id, callback);
        System.out.println("待发送短信的订单已纳入消息队列(rocketmq impl)，id:" + id);
    }
}

```

`OrderServiceRocketMQImpl.java`

```java

@Service
public class OrderServiceRocketMQImpl implements OrderRocketMQService {

    @Autowired
    private MessageService messageService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
```

### 3、配置监听器。

`MyRocketMQMessageListener.java`

```java
/**
 * @author: nie
 * @create: 2022-04-11 16:39
 * @description: 1)rocketmq监听器有自己的规范，需要实现RocketMQListener<T>接口
 *               该接口传入的泛型参数为消息队列中的消息的类型。
 *               2)@RocketMQMessageListener必须传入两个参数，topic主题，consumerGroup（配置文件中配置的）
 **/
@Component
@RocketMQMessageListener(topic = "order_id", consumerGroup = "group_rocketmq")
public class MyRocketMQMessageListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String id) {
        System.out.println("已完成短信发送业务（rocketmq listener）:id" + id);
    }
}
```

### 4、controller测试类。

`OrderRocketMQController.java`

```java

@RestController
@RequestMapping(value = "/orderRocketMQ")
public class OrderRocketMQController {
    @Autowired
    private OrderRocketMQService orderRocketMQService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderRocketMQService.order(id);
    }
}
```

### 5、运行截图。

```text
================================
业务订单开始处理
待发送短信的订单已纳入消息队列(rocketmq impl)，id:11523423242
业务订单处理结束
================================
短信发送成功！！！
已完成短信发送业务（rocketmq listener）:id11523423242
```