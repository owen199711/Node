### Spring，IOC的理解？
```angular2html
1. 使用控制反转思想，实现了一个容器用来管理和控制bean的生命周期，使用bean只需要从容器中取出来就行。
2. Spring帮我们实现了，bean的创建，生命周期的管理，销毁等工作。
3. 创建过程：Spring 定义BeanFactory 和 DefaultListableBeanFactory两个bean容器，使用Map集合存储bean对象。
   首先读取bean的定义信息，生成BeanDefinition对象，Spring通过反射机制实现对象的创建。
   我们可以通过实现BeanFactoryPostProcessor接口，修改Bean的定义信息。
```

### 上面有谈到Bean的生命周期，那你聊一下Spring Bean的生命周期？
<img src="/images/ioc.png">

```angular2html
1. Spring bean的生命周期：实例化，属性注入，初始化，销毁
2. 实例化：为bean的成员变量初始化默认值。
3. 属性注入：对bean的变量进行赋值
4. 检查是否实现Aware接口，并进行响应处理。
5. BeanPostProcessor 前置处理
6. 是否实现InitailizinBean 接口
7. BeanPostProcessor 后置处理
```
 
 ### 上面有讲到属性注入过程，会存在循环依赖问题，Spring是如何解决循环依赖问题的呢？
```angular2html
1. 使用三级缓存解决。
```
 
 首先：Spring通过提前暴露Bean的信息实现，循环依赖问题的解决，通过三级缓存，将Bean创建过程中（实例化完成未初始化的对象，和初始化完成的对象，和一个函数式接口）
 假设（a-b) 创建a: 首先从容器中获取是否有 a --> 没有创建： ---> 完成实例化 --> 放入三级缓存 ---> 属性注入 需要 b-->创建b
 容器获取 b: 没有创建 -->完成实例化 --> 放入三级缓存，  --> 属性注入  需要 a-->从容器获取 可以拿到，将 b 移到二级缓存，完成 b的创建 放入一级缓存
 a: 完成创建。
 为什么需要三级缓存？
  解决AOP问题，如果对象需要代理对象，在第一次对象对外暴露的时候，会调用函数是接口（getEarlyRefence，判断是否需要代理对象)。如果需要创建代理对象，不需要就直接返回
  原对象。

### spring 中单例bean是不是线程安全的？
```angular2html
  不是线程安全的
  spring 中有个@Scop 注解，默认是单例的bean， 因为一般注入的bean都是无状态的的没有线程安全问题，如果定义的属性是有状态的需要考虑线程安全问题，可以尝试加锁保证线程安全。
```

### spring 中事务失效的场景
```angular2html
1. 异常捕获处理：事务通知只有捕获到异常才会进行回滚，如果用户自己处理了异常，事务通知无法感知，事务会失效。解决：在catch中抛出异常。
2. 抛出检查异常：默认只会回滚非检查异常，如果抛出检查异常，事务会失效。解决：配置rollBackFor=Exception.class, 设置只要抛出异常就回滚
3. 非public方法导致事务失效：spring为方法创建代理，添加事务通知，前提都是方法是public的。解决：改为public
```

### spring 中有那些注解
```angular2html
 web:
   @Controller
   @RestController : @Controller 和 @ResponseBody
   @ResQuestMapping
   @ResQuestBody
容器：
  @Component
  @Service
  @AutoWare
  @Resource
  @Value : 使用"#{}" 或"${}" 取数据
AOP:
  @Aspect
  @After
  @Before
  @Around
  @PointCut
```
### Spring中使用了那些设计模式？
```angular2html
1. 工厂模式
2. 代理模式
3. 单例模式
4. 策略模式
5. 适配器模式
```

### 


