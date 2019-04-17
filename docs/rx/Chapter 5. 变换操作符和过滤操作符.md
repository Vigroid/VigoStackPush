#### 操作符列表

* 变换
    * map
    * flatMap, concatMap, flatMapIterable
    * switchMap
    * scan
    * groupBy
    * buffer
    * window
    * cast
* 过滤
    * filter
    * takeLast
    * last/lastOrDefault
    * takeLastBuffer
    * skip
    * take
    * first
    * elementAt
    * sample/throttle
    * timeOut
    * distinct
    * ofType
    * ignoreElements
***
#### map和flatMap
* 都是转换数据的类型，flatMap生成新Observable。
* **flatMap**
```kotlin
Observable                                                    
        .fromIterable(userList)                               
        .flatMap { usr -> Observable.fromIterable(usr.addrs) }
        .subscribe { addr ->                                  
            logInfo(addr.street)                              
        }                                                     
```
* **map**
```kotlin
Observable                          
        .fromIterable(userList)     
        .map { usr -> usr.addrs }   
        .subscribe { addrs ->       
            for (addr in addrs) {   
                logInfo(addr.street)
            }                       
        }                           
```
***
#### groupBy
* 将一个Observable分成一个或多个GroupedObservable
* example: 
```kotlin
Observable                                                 
        .range(1, 8)                                       
        .groupBy { return@groupBy if (it % 2 == 0) "奇数" else "偶数" }
        .subscribe { logInfo(it.key) }                             
```
上面的代码将1到8数这个数据源分组成奇数和偶数两个数据源，我们还可接着subscribe生成的新数据源
```kotlin
Observable                                                 
        .range(1, 8)                                       
        .groupBy { return@groupBy if (it % 2 == 0) "奇数" else "偶数"}
        .subscribe { newSource ->                           
            if (newSource.key == "奇数"){                     
                newSource.subscribe { logInfo("奇数$it") }     
            } else{                                       
                newSource.subscribe { logInfo("偶数$it", "xyz") }
            }                                             
        }                                                 
```
***
#### buffer && window
* **Buffer**
    1. 将数据源根据buffer(count)中的count转化。`T->List<T>`, List的count就是定义的count
    2. 还可以定义skip
```kotlin
Observable                                   
        .range(1, 11)                        
        .buffer(5, 6)                        
        .subscribe { logInfo(it.toString()) }
```
* **Window**
    1. window和buffer相似但是他是输出新数据源而不是转换数据源。e.g. `T->Observalbe<T>`
```kotlin
Observable                                           
        .range(1, 11)                                
        .window(2)                                   
        .subscribe {                                  
            logInfo("new observable")                
            it.subscribe { logInfo(it.toString()) } 
        }
```
***
#### first && last
* 将**Observable**转成**Single**，只发射最头或最尾的数据。first()和last()中可以填默认数据。
* example
``` kotlin
button.setOnClickListener {                         
    Observable                                     
            .range(1, 11)                          
            .first(1)                              
            .subscribe({logInfo(it.toString())},{})
}                                                  
                                                   
button2.setOnClickListener {                        
    Observable                                     
            .range(1, 11)                          
            .last(11)                              
            .subscribe({logInfo(it.toString())},{})
}                                                  
```
***
#### take && takeLast
* 取开头几个值或结尾几个值。只是数据源的转化。
```kotlin
button.setOnClickListener {                            
    Observable                                        
            .intervalRange(0,10,1,1, TimeUnit.SECONDS)
            .take(5)                                  
            .subscribe {logInfo("$it")}               
}                                                     
                                                      
button2.setOnClickListener {                           
    Observable                                        
            .intervalRange(0,10,1,1, TimeUnit.SECONDS)
            .takeLast(3, TimeUnit.SECONDS)            
            .subscribe {logInfo("$it")}               
}                                                     
```
***
#### skip && skipLast
* 与take相反，skip和skipLast是抑制头n个或者尾n个数据的发射。
```kotlin
button.setOnClickListener {                            
    Observable                                        
            .intervalRange(0,10,1,1, TimeUnit.SECONDS)
            .skip(5)                                  
            .subscribe {logInfo("$it")}               
}                                                     
                                                      
button2.setOnClickListener {                           
    Observable                                        
            .intervalRange(0,10,1,1, TimeUnit.SECONDS)
            .skipLast(3, TimeUnit.SECONDS)            
            .subscribe {logInfo("$it")}               
}                                                     
```
***
#### elementAt && ignoreElements
* elementAt(index, default)取出数据流中第n个数据。default定义了默认值。转化成**Maybe**
* ignoreElements抑制所有的数据。只让onComplete()通过。转化为**Completable**
```kotlin

```
***
#### distinct && filter
* distinct 只允许未发射的数据通过
```kotlin
Observable                                 
        .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
        .distinct()                        
        .subscribe { logInfo("$it") }      
```
输出⬆️：
```
1,2,3,4,5,9
```
* distinctUntilChange 只允许和上一个不同的数据通过
```kotlin
Observable                                 
        .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
        .distinctUntilChanged()            
        .subscribe { logInfo("$it") }      
```
输出⬆️：
```
1,2,3,4,5,1,9
```
* filter 定义一个过滤规则，满足条件才放行发射
```kotlin
Observable                                 
        .just(1, 2, 2, 3, 3, 4, 5, 1, 1, 9)
        .filter { num -> num >= 3 }        
        .subscribe { logInfo("$it") }      
```
输出⬆️：
```
3,3,4,5,9
```
***
#### debounce
* debounce(time, timeUnit), 数据发射的间隔如果在time时间内就过滤掉不放行。
```kotlin
Observable                                   
        .create<Int> {                        
            for (i in 1..10) {               
                Thread.sleep(i * 100L)       
                it.onNext(i)                 
            }                                
            it.onComplete()                  
        }                                    
        .debounce(500, TimeUnit.MILLISECONDS)
        .subscribe { logInfo("$it") }        
```
***