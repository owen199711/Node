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
* AboutPolicy: 直接抛异常
* CallerRunsPolicy: 用调用者所在的线程来执行任务
* DiscardOldestPolicy: 丢弃阻塞队列里面最老的任务
* DiscardPolicy: 直接丢弃任务

3. submit和excute提交任务有什么区别？
submit提交任务有返回值，放回一个future结果对象  
excute提交任务没有返回值。

