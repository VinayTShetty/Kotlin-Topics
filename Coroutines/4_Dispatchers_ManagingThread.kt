Introduction to Thread Pool
***************************


What is a Thread Pool?

A thread pool is a collection of worker threads that efficiently execute multiple tasks without creating a new thread for each task. 
Instead of creating and destroying threads frequently (which is costly), the pool reuses threads,
reducing overhead and improving performance.



How a Thread Pool Works:

A task is submitted to the thread pool.
If there is an idle thread, it picks up the task.
If all threads are busy, the task waits in a queue.
Once a thread completes its task, it takes the next task from the queue.
This cycle continues, improving resource efficiency.

ScreenShots
Thread Pool Comparisons for Dispatchers


Kotlin Coroutines use thread pools internally to manage concurrency efficiently. 

For example:
Dispatchers.Default → Uses a shared thread pool with a limited number of threads.
Dispatchers.IO → Uses an optimized thread pool for I/O operations.




What is a Dispatcher?

A Dispatcher in Kotlin Coroutines is responsible for determining which thread(s) a coroutine runs on.

It provides thread management and ensures that coroutines execute efficiently without blocking the main thread, 
thus preventing performance issues like UI freezes or slow execution.


Dispatchers are required to:

Optimize resource utilization by executing tasks on appropriate threads.
Prevent UI blocking by offloading heavy operations to background threads.
Ensure concurrency while maintaining thread safety.
Improve performance by executing different tasks in parallel.

Types of Dispatchers

Dispatchers.Default: 
Dispatchers.IO: 
Dispatchers.Main: 
Dispatchers.Unconfined: 



Dispatcher.Default
******************

package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("DISPATCHER Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        val scope = CoroutineScope(Dispatchers.Default) // Creating a custom CoroutineScope
        scope.launch  {  // Using that custome coroutine scope.
            println("DISPATCHER Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        }
        Thread.sleep(1000)
        println("DISPATCHER Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
  }
}

12:54:17.136 System.out               I  DISPATCHER Main Method Starts Name = main ID = 2
12:54:17.177 System.out               I  DISPATCHER Inside runBlocking Coroutine Name = DefaultDispatcher-worker-1 ID = 56
12:54:18.176 System.out               I  DISPATCHER Main Method Ends Name = main ID = 2


Dispatcher.IO
*************
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("DISPATCHER Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        val scope = CoroutineScope(Dispatchers.IO) // Creating a custom CoroutineScope
        scope.launch  {  // Using that custome coroutine scope.
            println("DISPATCHER Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        }
        Thread.sleep(1000)
        println("DISPATCHER Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
  }
}

12:55:57.345 System.out               I  DISPATCHER Main Method Starts Name = main ID = 2
12:55:57.385 System.out               I  DISPATCHER Inside runBlocking Coroutine Name = DefaultDispatcher-worker-2 ID = 58
12:55:58.386 System.out               I  DISPATCHER Main Method Ends Name = main ID = 2


Dispatcher.Main
***************
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("DISPATCHER Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        val scope = CoroutineScope(Dispatchers.Main) // Creating a custom CoroutineScope
        scope.launch  {  // Using that custome coroutine scope.
            println("DISPATCHER Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        }
        Thread.sleep(1000)
        println("DISPATCHER Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
  }
}

12:57:13.760 System.out               I  DISPATCHER Main Method Starts Name = main ID = 2
12:57:14.765 System.out               I  DISPATCHER Main Method Ends Name = main ID = 2
12:57:14.801 System.out               I  DISPATCHER Inside runBlocking Coroutine Name = main ID = 2


Dispatcher.Unconfined
*********************
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("DISPATCHER Main Method Starts Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        val scope = CoroutineScope(Dispatchers.Unconfined) // Creating a custom CoroutineScope
        scope.launch  {  // Using that custome coroutine scope.
            println("DISPATCHER Inside runBlocking Coroutine Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
        }
        Thread.sleep(1000)
        println("DISPATCHER Main Method Ends Name = ${Thread.currentThread().name} ID = ${Thread.currentThread().id}")
  }
}

13:00:13.591 System.out               I  DISPATCHER Main Method Starts Name = main ID = 2
13:00:13.593 System.out               I  DISPATCHER Inside runBlocking Coroutine Name = main ID = 2
13:00:14.595 System.out               I  DISPATCHER Main Method Ends Name = main ID = 2


Quiz Question :- 
****************
why we are not using Dispatcher concept in GlobalScope.launch{}
Why Doesn't GlobalScope.launch Require a Dispatcher?

By default, GlobalScope.launch { } uses Dispatchers.Default, which is optimized for CPU-intensive work

Programe
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        println("COROUTINE Main Method Starts Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
        GlobalScope.launch {
            println("COROUTINE Inside Coroutine Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
        }
        Thread.sleep(1000)
        println("COROUTINE Main Method Ends Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
  }
}

12:39:38.087 System.out               I  COROUTINE Main Method Starts Name main ID 2
12:39:38.146 System.out               I  COROUTINE Inside Coroutine Name DefaultDispatcher-worker-1 ID 57
12:39:39.145 System.out               I  COROUTINE Main Method Ends Name main ID 2

