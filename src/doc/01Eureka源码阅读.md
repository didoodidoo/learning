### Euraka源码阅读 

#### 阅读前环境的准备

- 直接拉源码下来编译费时费力不讨好，可以直接从SpringCloud的启动开始读起，你只需要写一个SpringCloud的demo，起一个@EnabledEurekaServer的Application就足够了。

- 由 @EnableEurekaServer注解开始，可以看到它引入了一个EurekaServerMarkerConfiguration.class类，这个类只是用来起一个标识作用

- SpringBoot的starter都是通过spi有spring.factories引入的，eurekaServer也不例外，去spring-cloud-starter-netflix-eureka-server中的spring.factories看一眼

  ![image-20201017100510278](.\img\image-20201017100510278.png)

```java
org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration
```

引入的是这个类，这个类一般是个工厂， 找到这个类，能看到他用@bean创建了一些运行时必要的类，同时用@Import引入了一个EurekaServerInitializerConfiguration,这个类继承了一个

Spring的Aware，够熟悉吧![image-20201017093120833](.\img\image-20201017093120833.png)

```java
@Override
public void start() {
   new Thread(() -> {
      try {
         // TODO: is this class even needed now?
         eurekaServerBootstrap.contextInitialized(
               EurekaServerInitializerConfiguration.this.servletContext);
         log.info("Started Eureka Server");

         publish(new EurekaRegistryAvailableEvent(getEurekaServerConfig()));
         EurekaServerInitializerConfiguration.this.running = true;
         publish(new EurekaServerStartedEvent(getEurekaServerConfig()));
      }
      catch (Exception ex) {
         // Help!
         log.error("Could not initialize Eureka servlet context", ex);
      }
   }).start();
}
```

另起了一个线程启动了server，好了 大功告成 在这个启动这里打一个断点，接下来就会进到EurekaServerBootStrap类里面，

```java
public void contextInitialized(ServletContext context) {
   try {
       //初始化环境
      initEurekaEnvironment();
      initEurekaServerContext();

      context.setAttribute(EurekaServerContext.class.getName(), this.serverContext);
   }
   catch (Throwable e) {
      log.error("Cannot bootstrap eureka server :", e);
      throw new RuntimeException("Cannot bootstrap eureka server :", e);
   }
}
```

到这就已经和老师讲代码的起点一样了。

#### 1. Server启动

##### 1.1 环境初始化

​	这部分比较简单，我觉得可以直接跳过，知道它加载了一些配置就行了。

##### 1.2  initEurekaServerContext(); 

​	

```java
protected void initEurekaServerContext() throws Exception {
   // For backward compatibility
   // 在SpringCloud中没有显示的创建eurekaClient了，在那个configuration里面可以看到AutoWired了一个
   JsonXStream.getInstance().registerConverter(new V1AwareInstanceInfoConverter(),
         XStream.PRIORITY_VERY_HIGH);
   XmlXStream.getInstance().registerConverter(new V1AwareInstanceInfoConverter(),
         XStream.PRIORITY_VERY_HIGH);

   if (isAws(this.applicationInfoManager.getInfo())) {
      this.awsBinder = new AwsBinderDelegate(this.eurekaServerConfig,
            this.eurekaClientConfig, this.registry, this.applicationInfoManager);
      this.awsBinder.start();
   }

   EurekaServerContextHolder.initialize(this.serverContext);

   log.info("Initialized server context");

    //从邻近的EurekaServer同步信息
   int registryCount = this.registry.syncUp();
   this.registry.openForTraffic(this.applicationInfoManager, registryCount);

   //注册监控统计信息
   EurekaMonitors.registerAllStats();
}
```

registry.syncUp() 方法用于在当前 Eureka Server 节点启动时从邻近的Eureka Server同步注册信息，并返回同步得到的应用数量。当存在多个Eureka Server时，该方法会有实际的作用，用于达到各个节点之间数据的最终一致性。可以通过 `eureka.server.registry-sync-retries` 配置调整同步重试次数。如果未获取到应用实例，则 Eureka-Server 会有一段时间( 默认：5 分钟，可配 )不允许被 Eureka-Client 获取注册信息，避免影响 Eureka-Client ，具体过程见1.3

```java
public void openForTraffic(ApplicationInfoManager applicationInfoManager, int count) {
    // Renewals happen every 30 seconds and for a minute it should be a factor of 2.
    //计算一分钟需要发送几次心跳 
    this.expectedNumberOfClientsSendingRenews = count;
    updateRenewsPerMinThreshold();
    logger.info("Got {} instances from neighboring DS node", count);
    logger.info("Renew threshold is: {}", numberOfRenewsPerMinThreshold);
    this.startupTime = System.currentTimeMillis();
    if (count > 0) {
        this.peerInstancesTransferEmptyOnStartup = false;
    }
    DataCenterInfo.Name selfName = applicationInfoManager.getInfo().getDataCenterInfo().getName();
    boolean isAws = Name.Amazon == selfName;
    if (isAws && serverConfig.shouldPrimeAwsReplicaConnections()) {
        logger.info("Priming AWS connections for all replicas..");
        primeAwsReplicas(applicationInfoManager);
    }
    logger.info("Changing status to UP");
    applicationInfoManager.setInstanceStatus(InstanceStatus.UP);
    super.postInit();
}
```

registry.openForTraffic() 方法
 在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。

##### 1.3  eurekaServer集群间同步



​	先看一下这个syncUp方法

```java
@Override
public int syncUp() {
    // Copy entire entry from neighboring DS node
    int count = 0;

    for (int i = 0; ((i < serverConfig.getRegistrySyncRetries()) && (count == 0)); i++) {
        // 从配置中读取集群的节点信息 如果读到的数量为0 就休眠一会等待重试
        if (i > 0) {
            try {
                Thread.sleep(serverConfig.getRegistrySyncRetryWaitMs());
            } catch (InterruptedException e) {
                logger.warn("Interrupted during registry transfer..");
                break;
            }
        }
        //获取注册信息 
        Applications apps = eurekaClient.getApplications();
        //把注册信息注册给 自己
        for (Application app : apps.getRegisteredApplications()) {
            for (InstanceInfo instance : app.getInstances()) {
                try {
                    if (isRegisterable(instance)) {
                        //注册
                        register(instance, instance.getLeaseInfo().getDurationInSecs(), true);
                        count++;
                    }
                } catch (Throwable t) {
                    logger.error("During DS init copy", t);
                }
            }
        }
    }
    return count;
}
```

可以看到方法很简单 就是从他自己的那个eureka里面获取了注册信息，一个client向server获取数据就是这个client正常的功能，如何获取的其实是client的功能在server的启动过程中没有必要深究。如果这个client超过重试次数仍然没有获取到注册信息，则 Eureka-Server 会有一段时间( 默认：5 分钟，可配 )不允许被 Eureka-Client 获取注册信息，避免影响 Eureka-Client 。

在这之后集群间节点的同步是由通过pull的方式，server在接收到Eureka-Client 的 Register、Heartbeat、Cancel、StatusUpdate、DeleteStatusOverride 操作，固定间隔( 默认值 ：500 毫秒，可配 )向 Eureka-Server 集群内其他节点同步( **准实时，非实时** )。这个延迟的作用是为了批处理吗？

同步的并不是状态而是操作，和redis同步指令异曲同工。这里面还有一些小细节，比如同步的消息会带时间戳，server收到同步之后会丢弃掉时间比较旧的消息。具体同步操作是怎么做的呢？就不在这里写了不然没完没了了。

##### 1.4

##### 1.5

##### 1.6

##### 1.7

##### 1.8 总结

