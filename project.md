## 简易RPC项目
 * 项目结构?
    1. RpcServer :包含 NettyServer, 负责网络通信，zkServer: 服务统一管理，负载均衡等（负责将服务注册到zk）
    2. RpcClient :包含 NettyClient, 负责网络数据传输，zkClient: 根据服务名取出服务列表
    3. Linsener  : 使用ApplicationLinserner 的 ContextRefredEven事件启动服务端，与客户端，将服务注册，将远程服务的属性注入代理对象等工作。
    4. Hanlder   : 通过反射方式调用远程服务，然后将处理信息返回到客户端。
   
 * 项目简介？   
    使用Netty做网络通信框架，zk管理服务，spring框架管理类。搭建一个简单的远程控制访问框架。  
    
 * 项目启动？  
    首先，使用ApplicationLinserner 监听器的 ContextRefredEven事件 负责 整体项目的启动，
    首先，将所有需要暴露的远程服务的方法，在zk中注册保存，然后启动Netty服务端。
    然后，将所有需要远程服务的属性，生成代理对象，注入。
    在代理对象中：
      获取所有该方法的服务列表，负载均衡，RPC请求，客户端发送，服务端接收，并通过Hanlder 反射处理，将结构返回。  
  * 项目有哪些难点？  
    1. 如何启动项目？使用监听器。
    2. 如何知道那些方法是要暴露的？那些属性是需要远程服务的？通过自定义注解，带有自定义注解的属性，方法进行操作。
    3. 如何将项目设置未一个Spring-Start项目？在Spring.factories配置文件中配置配置类的路径，然后再配置类中加载所有需要的类到容器中
    
  ## 抢红包模块？
  
 
