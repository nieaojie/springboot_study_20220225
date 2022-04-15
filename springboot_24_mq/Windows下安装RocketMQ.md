# Windows下安装RocketMQ

## 一、软件下载

下载地址： https://rocketmq.apache.org/  

## 二、软件安装

解压缩即可。

默认服务端口： `9876`

## 三、配置环境变量

* ROCKETMQ_HOME：`F:\software\rocketmq-4.9.3`
* PATH：`%ROCKETMQ_HOME%\bin`
* NAMESRV_ADDR（建议）： `127.0.0.1:9876`

## 四、启动

1. 启动命名服务器，双击`mqnamesrv.cmd`，出现：

   ```shell
   Java HotSpot(TM) 64-Bit Server VM warning: Using the DefNew young collector with the CMS collector is deprecated and will likely be removed in a future release
   Java HotSpot(TM) 64-Bit Server VM warning: UseCMSCompactAtFullCollection is deprecated and will likely be removed in a future release.
   
   The Name Server boot success. serializeType=JSON
   ```

2. 启动broker，双击`mqbroker.cmd`，出现：

   ```shell
   The broker[DESKTOP-89PFPFU, 192.168.113.1:10911] boot success. serializeType=JSON and name server is 127.0.0.1:9876
   ```

   **如果遇到**：rocketmq broker启动报错，找不到或无法加载主类的问题。

   **原因：**JAVAHOME环境变量路径中包含空格。

   **解决：**修改runbroker.cmd脚本，将对应行修改为：

   ```shell
   set "JAVA_OPT=%JAVA_OPT% -cp "%CLASSPATH%""
   ```

## 五、测试

* 服务器功能测试：生产者

  ```cmd
  tools org.apache.rocketmq.example.quickstart.Producer
  ```

* 服务器功能测试：消费者

  ```cmd
  tools org.apache.rocketmq.example.quickstart.Consumer
  ```

  
