## 线程池：
### 线程创建的方法有哪些？
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

### 拒绝策略有哪些？
```text
* AboutPolicy: 直接抛异常
* CallerRunsPolicy: 用调用者所在的线程来执行任务
* DiscardOldestPolicy: 丢弃阻塞队列里面最老的任务
* DiscardPolicy: 直接丢弃任务
```

### submit和excute提交任务有什么区别？
```text
submit提交任务有返回值，放回一个future结果对象  
excute提交任务没有返回值。
```

### ThreadLocal的原理和使用场景？
```angular2html
1. 可以理解为线程存储数据的盒子，在threadLocalMap中存储数据，使用threadLocal对象作为key.
2. 线程通过线程id获取到对应的threadLocalMap,通过threadLocal对象key获取值。
使用场景：
  1. 线程内部传递数据
```

### TThreadLocal内存泄露原因，如何避免？
```angular2html
1. 由于threadLocal的key是弱引用，会被gc,而value依旧存在内存中，产生内存泄漏。
2. 每次使用完数据，调用remove方法。手动清除数据。
```


### volatile关键字的理解？
```angular2html
1. 禁止指令重排，保证线程对共享的数据可见性，如果主存数据发生变化，其他线程内的缓存数据都会失效，需要从新从主存加载。
```


### Java内存模型？
```text
内存模型：CPU与主存速度不匹配，CPU内部有缓存，保证缓存一致性，出现了内存模型。
Java内存模型：JMM，定义JVM可以屏蔽不同硬件和操作系统的差异情况下，保证内存访问保存一致性，的规范。  
从抽象角度jMM定义了线程与主存之间的抽象关系。每个线程会有共享数据的副本，共享数据在主存中，线程之间通信？如a与b通信，a将共享数据刷新到主存，b从主存读取数据，
完成a和b的通信。  
java内存模型：同步步骤：lock --> read(数据读入工作内存) --> load(数据赋值给工作内存副本变量) -->   
use --> assign(数据赋值给工作内存变量) --> store(数据输出主存) --> write(输出赋值给主存变量) --> unlock  
```
### synchronize原理
```angular2html
1. 使用monitor对象实现，monitory对象结构：WaitSet: 等待队列，await方法的对象存储在这 EntrySet: 阻塞队列，获取锁失败的对象存储在这 Owner: 获取锁的线程
2. thread 进入synchronize 代码块与一个monitor对象关联，检查monitor owner是否有数据，没有则表明无锁，有则放入entrySet队列中等待。
3. 如何关联monitor对象的呢？锁对象的对象头中记录指向monitor对象的指针。
```

### monitor 是重量级锁，你了解锁升级的过程吗？
<img src="/images/obj.png">

```angular2html
1. monitor是重量级锁，涉及到，内核态和用户态的切换，以及线程上下切换，成本比较高。
2. 锁升级需要提到锁的状态，锁有四种状态：
   无锁：没有多个线程进入同步代码块
   偏向锁：只有一个线程进入同步代码块的时候，锁对象中 记录线程 id, 下次线程进入判断是否是自己的id.
   轻量级锁：两个线程交替进入同步代码块，锁记录中存储对象头的 lock_record 地址，通过cas 操作交换，所记录中的 lock_record 与锁对象的对象头的lock_record
   重量级锁：两个线程发送锁竞争，会有一个线程进入阻塞队列，同时锁升级为重量级锁
```

### cas 你了解吗？
```angular2html
乐观锁的实现。
1. cas 是compare and swap 的简写，是比较并交换，有三个值，a new_a old_a, 通过比较 a 是否等于old_a 等于则替换为new_a 否则替换失败
2. 结合java内存模型是这样的：
   首先java内存模型是：每个线程数据都是私有的，不受其他线程影响，每个线程都有共享数据的副本，共享变量 old_a 读入线程中 a 被线程修改为 new_a 
   cas操作：比较 old_a是否等于 a 等于，则线程将 new_a 写如共享内存中，可以保证多个线程操作时，共享数据只能同时被一个线程修改。
```

### AQS 了解吗？
```angular2html
1. aqs 是实现锁的一种框架，底层使用 state 和 cas 操作，实现， state 从 0 - > 1 是上锁操作， 1 - > 0是解锁操作。
2. 并且内部为何一个阻塞队列，如果cas 失败的线程 会进入阻塞队列中保存。
3. reentrantLock semaphore countDownLatch, 都是用aqs实现的。
```



