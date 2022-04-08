# SpringBoot整合消息队列

## 一、手动模拟消息

> 案例介绍:
> 模拟创建一个订单之后经过服务调用或处理各种业务之后发送短信。

1、写一个订单接口和短信接口。

`OrderService.java`

```java
public interface OrderService {
    public void order(String id);
}
```

`MessageService.java`

```java
public interface MessageService {

    public void sendMessage(String id);

    public String doMessage();
}
```

2、简单实现这两个接口。

`OrderServiceImpl.java`

```java

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MessageService messageService;

    @Override
    public void order(String id) {
        //一系列操作，包含各种服务调用，处理各种业务
        System.out.println("业务订单开始处理");
        //短信消息处理
        messageService.sendMessage(id);
        System.out.println("业务订单处理结束");
        System.out.println("================================");
    }
}
```

`MessageServiceImpl.java`

```java

@Service
public class MessageServiceImpl implements MessageService {

    private ArrayList<String> list = new ArrayList<String>();

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入消息队列，id:" + id);
        list.add(id);
    }

    @Override
    public String doMessage() {
        if (list.size() <= 0) {
            return "无消息可处理";
        }
        String remove = list.remove(0);
        System.out.println("已完成短信发送业务，id:" + remove);
        return remove;
    }
}
```

3、创建两个controller测试即可。

`OrderController.java`

```java

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{id}")
    public void order(@PathVariable(value = "id") String id) {
        orderService.order(id);
    }
}
```

`MessageController.java`

```java

@RestController
@RequestMapping(value = "/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String doMessage() {
        return messageService.doMessage();
    }
}
```

4、测试结果

![](./readme.assets/readme-1649344239766.png)

## 二、springboot整合ActiveMQ

### 1、下载安装ActiveMQ

> 下载地址： https://activemq.apache.org/components/classic/download/

* 启动服务
    ```shell
    activemq.bat
    ```

* 访问ActiveMQ服务器
    ```http request
    http://127.0.0.1:8161/
    ```
  服务端口(编程)： 61616， 管理后台端口： 8161

  用户名&密码： admin
  ![](./readme.assets/readme-1649404776259.png)

### 2、整合ActiveMQ

1）导依赖

```xml
<!--activemq-->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

2）做配置

```yml
server:
  port: 80

spring:
  activemq:
    broker-url: tcp://localhost:61616
```

3）写代码

需要在`OrderServiceImpl.java`中替换一下实现。

```java
@Autowired
//private MessageService messageService;
private MessageActiveMQService messageService;
```

`MessageActiveMQService.java`

```java
public interface MessageActiveMQService {
    public void sendMessage(String id);

    public String doMessage();
}
```

`MessageActiveMQServiceImpl.java`

```java

@Service
public class MessageActiveMQServiceImpl implements MessageActiveMQService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(String id) {
        jmsMessagingTemplate.convertAndSend(id);
        System.out.println("待发送短信的订单已纳入消息队列，id:" + id);
    }

    @Override
    public String doMessage() {
        String id = jmsMessagingTemplate.receiveAndConvert(String.class);
        System.out.println("已完成短信发送业务，id:" + id);
        return id;
    }
}
```

`MessageActiveMQController.java`

```java

@RestController
@RequestMapping(value = "/msgMQ")
public class MessageActiveMQController {

    @Autowired
    private MessageActiveMQService messageActiveMQService;

    @GetMapping
    public String doMessage() {
        return messageActiveMQService.doMessage();
    }
}
```

4）运行测试 会报个异常：

```shell
java.lang.IllegalStateException: No 'defaultDestination' or 'defaultDestinationName' specified. Check configuration of JmsMessagingTemplate.
```

需要在配置文件中加个默认的配置：

```yml
server:
  port: 80

spring:
  activemq:
    broker-url: tcp://localhost:61616
  jms:
    template:
      # 配置一个默认的保存位置
      default-destination: test
```

然后重启运行就可以了。

**注意：** 一般在代码中进行配置（但也需要有一个默认的配置），在代码中配置如下：

```java
jmsMessagingTemplate.convertAndSend("order.queue.id",id);
```

在使用的时候也需要指定`destinationName`：

```java
String id=jmsMessagingTemplate.receiveAndConvert("order.queue.id",String.class);
```

5）改进

加一个监听器，自动接收消息并处理。

`MessageListener.java`

```java
/**
 * 消息队列的监听器
 */
@Component
public class MessageListener {

    //添加一个队列的监听器，只要一有消息就会被该方法接收并处理
    @JmsListener(destination = "order.queue.id")
    //该方法处理完之后，将返回值又发送到另一个消息队列处理
    @SendTo(value = "order.other.queue.id")
    public String sendMsg(String id) {
        System.out.println("已完成短信发送操作,id:" + id);
        return id;
    }

    //可以处理上一个消息队列处理之后加入新队列的消息
    @JmsListener(destination = "order.other.queue.id")
    public void processOtherMessage(String id) {
        System.out.println("完成处理订单的操作，id:" + id);
    }
}

```

6）默认的是点对点的配置，可以在配置中设置发布订阅的形式。

```yml
server:
  port: 80

spring:
  activemq:
    broker-url: tcp://localhost:61616
  jms:
    template:
      # 配置一个默认的保存位置
      default-destination: test
    # 发布订阅的形式，默认是点对点
    pub-sub-domain: true
```
![](./readme.assets/readme-1649410291882.png)
