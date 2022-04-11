# Windows下安装Kafka

## 一、下载

下载地址： https://kafka.apache.org/downloads

windows 系统下3.0.0版本存在BUG，建议使用2.X版本。

## 二、安装

解压缩即可。

## 三、启动

* 启动注册中心`zookeeper`，默认端口：`2181`。

  ```cmd
  zookeeper-server-start.bat ../../config/zookeeper.properties
  ```

* 启动服务器`kafka`，默认端口：`9092`。

  ```cmd
  kafka-server-start.bat ../../config/server.properties
  ```

## 四、相关操作

* 创建topic

  ```cmd
  kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka2022
  ```

* 查看topic

  ```cmd
  kafka-topics.bat --zookeeper 127.0.0.1:2181 --list
  ```

* 删除topic

  ```cmd
  kafka-topics.bat --delete --zookeeper localhost:2181 --topic kafka2022
  ```

* 生产者功能测试

  ```cmd
  kafka-console-producer.bat --broker-list localhost:9092 --topic kafka2022
  ```

* 消费者功能测试

  ```cmd
  kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka2022 --from-beginning
  ```

