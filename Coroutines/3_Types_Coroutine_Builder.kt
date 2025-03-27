There are 4 types of Coroutines builders

1)GlobalScope.launch{}
2)async{}
3)runblocking{}
4)launch{}



How to execute a Coroutine
**************************

GlobalScope.launch{

    // write Logic here
}


async {
    // write Logic here
}


runblocking{
    // write Logic here
}

launch {

    // write Logic here   

}


Dispatchers   
***********
Note:- Dispatchers concept will be Covered later

There are 4 Dispatchers 

1)Dispatchers.Main
2)Dispatchers.IO
3)Dispatchers.Default
4)Dispatchers.Unconfined



Dispatchers.Main() 
Thread Executed: Main/UI Thread
                 Used for UI-related tasks in Android.

Dispatchers.IO() 
Thread Executed: Background Thread (I/O Thread Pool)
                 Used for disk, network, or database operations
                -Database queries (Room, SQLite)
                -Network calls (Retrofit, OkHttp)
                -File operations (Reading/Writing files

Dispatchers.Default() 
Thread Executed: Background Thread (CPU-Optimized Thread Pool) 
                 Used for CPU-intensive operations (e.g., sorting, complex calculations).
                -Heavy computations (Sorting, image processing, encryption)
                -Parallel processing (Data transformations)              

Dispatchers.Unconfined()
                Thread Executed: Caller’s Thread (May Change)
                Starts on the thread where it was called but may switch to another thread when suspended.



Application waiting for Thread and Coroutine.
*********************************************


Thread
******

Explicitly we are not making th Thread sleep.
Application will wait untill all the Thread are executed.

import kotlin.concurrent.thread
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    thread {
        println("Inside Thread 1 Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    thread {
        println("Inside Thread 2 Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Main Method Starts Name = main ID = 1
Inside Thread 1 Name = Thread-0 ID = 12
Main Method Ends Name = main ID = 1
Inside Thread 2 Name = Thread-1 ID = 13

Coroutine
*********

Case 1 :- 
Application will not wait for the coroutine to finish the execution.

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    GlobalScope.launch {
        println("Inside GlobalScope Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Main Method Starts Name = main ID = 1
Main Method Ends Name = main ID = 1



Case 2 :- 

We need to Explicitly tell the application that , please wait untill the coroutine has finished the execution.
For this reason we are using Thread.sleep(1000)


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        GlobalScope.launch {
            println("Inside GlobalScope Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Main Method Starts Name = main ID = 1
Inside GlobalScope Coroutine Name = DefaultDispatcher-worker-1 ID = 12
Main Method Ends Name = main ID = 1


Reason to Use Thread.sleep(1000)

1) The main function executes sequentially and quickly reaches the end.

2) If the main function exits before the coroutine gets a chance to run, the JVM will terminate, and the coroutine will never execute

GlobalScope does not keep the JVM alive
3) GlobalScope.launch launches a coroutine in a background thread (default dispatcher).
4) However, Kotlin coroutines do not block the main thread, and GlobalScope does not tie the coroutine’s lifetime to the main function.
5) If the main function completes before the coroutine runs, the entire program shuts down.


Pre-Requisite to execute a Coroutine.
*************************************
To execute a coroutine builder , It should  have a scope.

1)GlobalScope.launch{}
2)async{}
3)runblocking{}
4)launch{}


GlobalScope.launch {}
*********************
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    GlobalScope.launch {
        println("Inside GlobalScope Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

runblocking
***********
import kotlinx.coroutines.runBlocking
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    runBlocking  {
        println("Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Main Method Starts Name = main ID = 1
Inside runBlocking Coroutine Name = main ID = 1
Main Method Ends Name = main ID = 1


launch
******

Case :- 1
Facing syntax  error when using launch coroutine ?
what s the reason for that ?

Reason :-

1) Facing an error because launch {} is only available inside a coroutine scope, but you're trying to use it directly inside main(),
   which is not a coroutine scope.

fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    launch  {
        println("Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Error : - e: D:\Workspace\kotlincode\src\main\kotlin\CheckLogic.kt: (4, 5): Unresolved reference: launch


Case 2 :- 
We can resolve it by creating a custom coroutine scope using CoroutineScope with a dispatcher like Dispatchers.Default or Dispatchers.IO
We can also resolve it by using runBlocking{} // Which will be discussed later.


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    val scope = CoroutineScope(Dispatchers.Default) // Creating a custom CoroutineScope
    scope.launch  {  // Using that custome coroutine scope.
        println("Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}

Conclusion :- We need to have Coroutine Scope to execute a coroutine builder.

But in Case of GlobalScope.launch{} and runBlocking they have their own scope.


async
*****
async is similar to launch in the above case.


runBlocking
***********
import kotlinx.coroutines.runBlocking
fun main() {
    println("Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
   runBlocking  {
        println("Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
}