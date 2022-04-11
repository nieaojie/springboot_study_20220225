# springboot整合Kafka

## 一、安装Kafka

> [Windows下安装Kafka](./Windows下安装Kafka.md)
>
>安装完成后，先启动Kafka，然后新建一个topic：kafka2022。

## 二、整合。

### 1、导依赖。

`pom.xml`

```xml
 <!--kafka-->
<dependency>
  <groupId>org.springframework.kafka</groupId>
  <artifactId>spring-kafka</artifactId>
</dependency>
```

### 2、做配置。

`application.yml`

```yml
server:
  port: 80

spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: group_order
```

**注意:**
在配置完监听器之后，需要指定`spring.kafka.consumer.group-id`

### 3、写代码。

1）写两个接口。

`OrderKafkaService.java`

```java
public interface OrderKafkaService {
    public void order(String id);
}
```

`MessageKafkaService.java`

```java
public interface MessageKafkaService {
    public void sendMessage(String id);
}

```

2）实现这两个接口。

`OrderKafkaServiceImpl.java`

```java

@Service
public class OrderKafkaServiceImpl implements OrderKafkaService {

    @Autowired
    private MessageKafkaService messageKafkaService;

    @Override
    public void order(String id) {
        System.out.println("================================");
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageKafkaService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
```

`MessageKafkaServiceImpl.java`

```java

@Service
public class MessageKafkaServiceImpl implements MessageKafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafkaTemplate.send(String topic, @Nullable V data);
     */
    @Override
    public void sendMessage(String id) {
        kafkaTemplate.send("kafka2022", id);
        System.out.println("待发送短信的订单已纳入消息队列(kafka impl)，id:" + id);
    }
}

```

3）监听器。

`KafkaMessageListener.java`

```java

@Component
public class KafkaMessageListener {

    /**
     * 1)不能自定义参数类型了，需要使用ConsumerRecord。
     * 2)因为使用到了监听器，所以在注解中，需要指定一个groupId，同时也需要在配置文件中指定，否则启动会报错。
     * @param record 使用这个参数接收值
     */
    @KafkaListener(topics = "kafka2022", groupId = "group_order")
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("已完成短信发送业务（kafka listener）:id" + record.value());
    }
}
```

4）controller测试类。

`OrderKafkaController.java`

```java

@RestController
@RequestMapping(value = "/orderKafka")
public class OrderKafkaController {
    @Autowired
    private OrderKafkaService orderKafkaService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderKafkaService.order(id);
    }
}
```

5）运行结果。

```text
待发送短信的订单已纳入消息队列(kafka impl)，id:11523423242
业务订单处理结束
================================
已完成短信发送业务（kafka listener）:id11523423242
```
