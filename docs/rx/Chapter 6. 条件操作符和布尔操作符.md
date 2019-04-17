#### 操作符列表
* 条件
    * amb
    * defaultEmpty
    * skipUntil
    * skipWhile
    * takeUntil
    * takeWhile
* 布尔
    * all
    * contains
    * exists/isEmpty
    * sequenceEqual
***
#### all, contains和amb
* all 布尔类型，T转为Boolean。看数据源的数据是否都满足条件。
```kotlin
Observable                              
        .just(1,2,4,6,7,8)              
        .all { it < 10 }                
        .subscribe ({logInfo("$it")},{})
```
* contains 布尔类型，看数据源中是否包含特定的值
```kotlin
Observable                             
        .just(1,2,4,6,7,8)             
        .contains(7)                   
        .subscribe({logInfo("$it")},{})
```
* amb 给定多个数据源，然后只发射先到的数据源
```kotlin
Observable                                          
        .ambArray(                                  
                Observable                          
                        .just(1, 2, 3)              
                        .delay(1, TimeUnit.SECONDS),
                Observable                          
                        .just(100, 200, 900))       
        .subscribe { logInfo("$it") }               
```
***
#### defaultIfEmpty和switchIfEmpty
* 对空数据源的处理，给一个默认数据或者默认数据源
```kotlin
button.setOnClickListener {                          
    Observable                                      
            .empty<Int>()                           
            .defaultIfEmpty(8)                      
            .subscribe { logInfo("$it") }           
}                                                   
                                                    
button2.setOnClickListener {                         
    Observable                                      
            .empty<Int>()     
            //不需要lambda而是直接对象
            .switchIfEmpty (Observable.just(1,2,3)) 
            .subscribe { logInfo("$it") }           
}                                                   
```
***
#### sequenceEqual
* 看两个序列发射的数据是否相等，还可以自己定义相等判断条件
```kotlin
Observable                                                 
        .sequenceEqual(                                    
                Observable.just(1,2,3),                    
                Observable.just("1","2","3"),              
                //自定义条件                                    
                BiPredicate { integer, string ->           
                    return@BiPredicate "$integer" == string
                }                                          
        )                                                  
        .subscribe ({ logInfo("$it") }, {})                          
```
***
#### skipUntil和skipWhile
* skipUntil第一个数据源开始发射的数据都忽略直到另一个数据源发射出数据才开始接收第一个的数据。
* skipWhile，如果发射的值满足条件就skip，如果不满足条件就把之后所有的数据都发射出去(之后不再判断了)
```kotlin
button.setOnClickListener {                              
    Observable                                          
            .intervalRange(1, 9, 0, 1, TimeUnit.SECONDS)
            .skipUntil(Observable.timer(4, TimeUnit.SECONDS))
            .subscribe { logInfo("$it") }               
}                                                       
                                                        
button2.setOnClickListener {                             
    Observable                                          
            .just(1, 2, 3, 4, 5, 6)                     
            .skipWhile { it <= 2 }                      
            .subscribe { logInfo("$it") }               
}                                                       
```
***
#### takeUntil和takeWhile
* takeUntil直到某个条件发射之前数据一直发送
* takeWhile如果满足某个条件，数据就一直发送
```kotlin
button.setOnClickListener {                                   
    Observable                                               
            .intervalRange(1, 9, 0, 1, TimeUnit.SECONDS)     
            .takeUntil(Observable.timer(4, TimeUnit.SECONDS))
            .subscribe { logInfo("$it") }                    
}                                                            
                                                             
button2.setOnClickListener {                                  
    Observable                                               
            .just(1, 2, 3, 4, 5, 6)                          
            .takeWhile { it <= 2 }                           
            .subscribe { logInfo("$it") }                    
}                                                            
```
***