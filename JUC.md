## 线程池：
1. 线程创建的方法有哪些？
```text
使用Thread,实现Run方法
实现Runnabel接口，实现Run方法，无返回值。
实现callable即可，实现call方法，方法有返回值，可以处理需要返回值的任务；
使用线程池：ExcutorServer

参数：
corePoolSize: 核心线程数
maximumPoolSize:  最大线程数
keepAliveTime:  最大线程数存活时间
TimeUnit:  时间单位
BlockingQueue:  阻塞队列
ThreadFactory:  线程工厂
RejectedExecutionHandler:  拒绝策略

四个实现类：
newFixedThreadPool():  固定大小的线程池，使用LinkedBlockingQueue()队列
newScheduleThreadPool():  周期线程池，使用DelayQueue,
newCachedThreadPool():  有缓存的线程池,使用SynchronousQueue(),适用于大量，短小的任务
newSingleThreadExcutor(): 单线程线程池，使用LinkedBlockingQueue，

常用的阻塞队列？
ArrayBlockingQueue: 数组实现的有界队列  
LinkedBlockingQueue:  链表实现的无界队列  
DelayQueue:  延迟队列，一个任务定时周期执行的延迟队列
PriorityBlockingQueue:  优先级队列，无界队列   
SynchronousQueue:  同步队列：插入操作必须等另一个线程调用移除操作，不存储元素，否则处于阻塞状态。
```

2. 拒绝策略有哪些？
```text
* AboutPolicy: 直接抛异常
* CallerRunsPolicy: 用调用者所在的线程来执行任务
* DiscardOldestPolicy: 丢弃阻塞队列里面最老的任务
* DiscardPolicy: 直接丢弃任务
```

3. submit和excute提交任务有什么区别？
```text
submit提交任务有返回值，放回一个future结果对象  
excute提交任务没有返回值。
```

4. ThreadLocal的原理和使用场景？


5. TThreadLocal内存泄露原因，如何避免？



6. volatile关键字的理解？
禁止指令重排，保证线程对共享的数据可见性，如果主存数据发生变化，其他线程内的缓存数据都会失效，需要从新从主存加载。


7. Java内存模型？
```text
内存模型：CPU与主存速度不匹配，CPU内部有缓存，保证缓存一致性，出现了内存模型。
Java内存模型：JMM，定义JVM可以屏蔽不同硬件和操作系统的差异情况下，保证内存访问保存一致性，的规范。  
从抽象角度jMM定义了线程与主存之间的抽象关系。每个线程会有共享数据的副本，共享数据在主存中，线程之间通信？如a与b通信，a将共享数据刷新到主存，b从主存读取数据，
完成a和b的通信。  
java内存模型：同步步骤：lock --> read(数据读入工作内存) --> load(数据赋值给工作内存副本变量) -->   
use --> assign(数据赋值给工作内存变量) --> store(数据输出主存) --> write(输出赋值给主存变量) --> unlock  
```
8. 

