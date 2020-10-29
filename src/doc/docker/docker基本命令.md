## Docker基本命令

### 基本操作

#### 拉取镜像

- docker pull 镜像名称

  docker pull [Registry]/[Repository]/[Image]:[Tag] 

  如果什么都不指定就会从dockerHub 拉取latest 的镜像

  docker pull busybox

#### 重命名镜像

- docker tag 给镜像打标签

   docker tag [SOURCE_IMAGE] [:TAG] [TARGET_IMAGE] [:TAG]

  等于是版本 镜像名称跟版本

#### 查看镜像

- docker images 

#### 删除镜像

- docker rmi 或者docker image rm

  docker rmi mybusybox  

#### 构建镜像

构建镜像主要有两种方式：

使用docker commit命令从运行中的容器提交为镜像；

使用docker build命令从 Dockerfile 构建镜像。

首先介绍下如何从运行中的容器提交为镜像。我依旧使用 busybox 镜像举例，使用以下命令创建一个名为 busybox 的容器并进入 busybox 容器。

```  shell
$ docker run --rm --name=busybox -it busybox sh
```


执行完上面的命令后，当前窗口会启动一个 busybox 容器并且进入容器中。在容器中，执行以下命令创建一个文件并写入内容：

```shell
touch hello.txt && echo "I love Docker. " > hello.txt
```


此时在容器的根目录下，已经创建了一个 hello.txt 文件，并写入了 "I love Docker. "。下面，我们新打开另一个命令行窗口，运行以下命令提交镜像：

```shell
$ docker commit busybox busybox:hello
sha256:cbc6406aaef080d1dd3087d4ea1e6c6c9915ee0ee0f5dd9e0a90b03e2215e81c
```


然后使用上面讲到的docker image ls命令查看

  

### Dockerfile

#### 常见指令

| 指令       | 指令简介                                                     |
| ---------- | ------------------------------------------------------------ |
| FROM       | Dockerfile 除了注释第一行必须是 FROM ，FROM 后面跟镜像名称，代表我们要基于哪个基础镜像构建我们的容器。 |
| RUN        | RUN 后面跟一个具体的命令，类似于 Linux 命令行执行命令。      |
| ADD        | 拷贝本机文件或者远程文件到镜像内                             |
| COPY       | 拷贝本机文件到镜像内                                         |
| USER       | 指定容器启动的用户                                           |
| ENTRYPOINT | 容器的启动命令                                               |
| CMD        | CMD 为 ENTRYPOINT 指令提供默认参数，也可以单独使用 CMD 指定容器启动参数 |
| ENV |	指定容器运行时的环境变量，格式为 key=value|
| ARG |	定义外部变量，构建镜像时可以使用 build-arg = 的格式传递参数用于构建|
| EXPOSE |	指定容器监听的端口，格式为 [port]/tcp 或者 [port]/udp|
| WORKDIR |	为 Dockerfile 中跟在其后的所有 RUN、CMD、ENTRYPOINT、COPY 和 ADD 命令设置工作目录。|

以一个简单的Dockerfile为例

```dockerfile
## 基础镜像
FROM centos:7 
## 将本机文件复制到容器中
COPY nginx.repo /etc/yum.repos.d/nginx.repo
## 执行命令
RUN yum install -y nginx
## 指定监听80端口
EXPOSE 80
## 环境变量
ENV HOST=mynginx
## 容器启动参数
CMD ["nginx","-g","daemon off;"]

```



### Dockerfile 最佳实践

#### 遵循的原则

- 单一职责

- 提供注释信息

- 保持容器最小化 

- 合理选择基础镜像

- 使用.dockerignore文件

- 尽量使用**构建缓存**：把不经常改动的命令放在前面

- 使用国内的源

- 最小化镜像层数

#### 常见指令

- run 指令换行
- 

  

