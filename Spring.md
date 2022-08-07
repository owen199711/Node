## Spring，IOC的理解？
总：总的来说首先Spring,IOC使用控制反转的思想，实现了一个容器用来管理和控制bean的生命周期，原来我们创建bean通过new的方式，现在可以通过Spring容器帮我们创建和管理bean，我们使用
只需要通过getBean从容器中取对象即可使用。
分：
具体来说：Spring需要帮我们实现，Bean的创建，对象生命周期管理，对象销毁等工。
对象创建过程：Spring 定义BeanFatory 和DeafultListableBeanFactory两个Bean容器，使用Map存储Bean对象。
然后需要读取对应的Bean定义信息，生成BeanDefinetion对象，
我们可以通过BeanFatoryPostProcessor的处理，可以修改Bean的定义信息。如我们配置MySQL数据源的时候的配置信息，username=${jdbc.username}
    通过BeanFatoryPostProcessor修改Bean的定义信息。
然后是BeanPostProcessor的注册，我们可通过这个接口实现对Bean的扩展
类信息加载完成，Spring通过反射机制实现对象创建，放入容器中，然后我们可以通过getBean方法拿到Bean进行使用

## 上面有谈到Bean的生命周期，那你聊一下Spring Bean的生命周期？
总的来说：Spring Bean的生命周期包括Bean的创建，使用，销毁过程：创建过程包括：实例化，属性注入，初始化前，初始化，初始化后，创建完成
具体来说：
    实例化：是在堆为对象开辟空间。
    然后，进行属性注入，对有Autoware的注解属性，进行赋值，分别通过name,type在容器中找到对应的类，赋值给属性，如果类未加载，则对该类进行加载。（会存在循环依赖问题）
    然后是实例化前，通过BeanPostProcessor接口实现
    实例化：调用init函数
    实例化后，BeanPostProcessor后置处理器调用。
    完成后将Bean放入Spring容器中
    使用
    销毁：调用DeopsiableBean接口destoryMethond方法。
 
 ## 上面有讲到属性注入过程，会存在循环依赖问题，Spring是如何解决循环依赖问题的呢？
 
 首先：Spring通过提前暴露Bean的信息实现，循环依赖问题的解决，通过三级缓存，将Bean创建过程中（实例化完成未初始化的对象，和初始化完成的对象，和一个函数式接口）
 假设（a-b) 创建a: 首先从容器中获取是否有 a --> 没有创建： ---> 完成实例化 --> 放入三级缓存 ---> 属性注入 需要 b-->创建b
 容器获取 b: 没有创建 -->完成实例化 --> 放入三级缓存，  --> 属性注入  需要 a-->从容器获取 可以拿到，将 b 移到二级缓存，完成 b的创建 放入一级缓存
 a: 完成创建。
 为什么需要三级缓存？
  解决AOP问题，如果对象需要代理对象，在第一次对象对外暴露的时候，会调用函数是接口（getEarlyRefence，判断是否需要代理对象)。如果需要创建代理对象，不需要就直接返回
  原对象。
