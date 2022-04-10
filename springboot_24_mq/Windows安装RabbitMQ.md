# Windows安装RabbitMQ  

> RabbitMQ基于Erlang语言编写，需要安装Erlang  
>
> Erlang的下载地址：https://www.erlang.org/downloads  
>
> 安装：一键傻瓜式安装，安装完毕需要重启， 需要依赖Windows组件  
>
> 环境变量配置：
>
> * ERLANG_HOME
> * PATH

## 一、环境配置

下载完Erlang的安装包`otp_win64_24.3.3.exe` 之后，点击安装，安装过程需要重启。

配置环境变量：

* ERLANG_HOME：`D:\Program Files\erl-24.3.3`
* PATH：`%ERLANG_HOME%\bin`

## 二、安装RabbitMQ

> 下载：https://rabbitmq.com/install-windows.html  
>
> 安装：傻瓜式安装。
>
> 启动：使用cmd，需要管理员权限。

cmd切换到目录：`D:\Program Files\RabbitMQ Server\rabbitmq_server-3.9.14\sbin`

⚫ 启动服务

```shell
rabbitmq-service.bat start
```

⚫ 关闭服务

```shell
rabbitmq-service.bat stop
```

⚫ 查看服务状态

```shell
rabbitmqctl status  
```

也可以在设备管理器中运行和关闭 `RabbitMQ` 服务。

## 三、服务管理可视化（插件形式）  

还是在具有管理员权限的cmd窗口，可以执行以下命令：

* 查看已安装的插件列表  

  ```shell
  rabbitmq-plugins.bat list
  ```

* 开启服务管理插件

  ```shell
  rabbitmq-plugins.bat enable rabbitmq_management
  ```

* 访问服务器

  ```shell
  http://localhost:15672
  ```

  ◆ 服务端口： 5672，管理后台端口： 15672
  ◆ 用户名&密码： guest  