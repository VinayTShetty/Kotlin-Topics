Date :- / /2020 
Topic:-  Coroutine Builder
Video Number:- 
Completed:- 
Resources Completed:- 
Github:- 
PDF:- 
Notes:- 
*****************************************************************************************
Example 1
Theorey
We can create a coroutine in 4 different ways.

1) launch
2) async
3) GlobalScope.launch
4) runBlocking


Test Case 1
Theorey(Test case 1)

*******************************End of Example 1***************************************
Example 2 GlobalScope.launch
Theorey
import kotlinx.coroutines.*

fun main() {
    println("Main Method Start Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
    GlobalScope.launch {
        println("Inside Coroutine Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
    }
    Thread.sleep(1000)
    println("Main Method Ends Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
}

C:\Users\vinayts\.jdks\corretto-1.8.0_312\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=50024:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\charsets.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\access-bridge-64.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\cldrdata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\dnsns.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jaccess.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jfxrt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\localedata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\nashorn.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunec.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunjce_provider.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunmscapi.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunpkcs11.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\zipfs.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jce.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfr.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfxswt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jsse.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\management-agent.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\resources.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\rt.jar;D:\Workspace\kotlincode\build\classes\kotlin\main;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-android\1.3.9\df17db5e329363c4e9cc7bf5b661ce3723a3e460\kotlinx-coroutines-android-1.3.9.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.7.10\d2abf9e77736acc4450dc4a3f707fa2c10f5099d\kotlin-stdlib-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.7.10\bac80c520d0a9e3f3673bc2658c6ed02ef45a76a\kotlin-stdlib-common-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-core-jvm\1.3.9\4be434f5e86c1998a273e7f19a7286440894f0b0\kotlinx-coroutines-core-jvm-1.3.9.jar CheckLogicKt
Main Method Start Thread Name main ID 1
Inside Coroutine Thread Name DefaultDispatcher-worker-2 ID 13
Main Method Ends Thread Name main ID 1

Process finished with exit code 0


Test Case 2
Theorey(Test case 2)

GlobalScope.launch is a coroutine builder that launches a new coroutine in the global scope, 
meaning the coroutine's lifecycle is tied to the application itself rather than a specific component
 (like an Activity or ViewModel in Android).

In Android, if you use GlobalScope.launch inside an Activity and the Activity is destroyed,
the coroutine will still continue running, potentially causing memory leaks.


*******************************End of Example 2***************************************
Example 3

Theorey launch {}

import javafx.application.Application.launch
import kotlinx.coroutines.*

fun main() {
   launch{

   }
}

Test Case 3
Theorey(Test case 3)

e: D:\Workspace\kotlincode\src\main\kotlin\CheckLogic.kt: (5, 10): Passing value as a vararg is only allowed inside a parenthesized argument list

The reason why we cannot execute a coroutine is because  corutine should be launched within the coroutine scope.


*******************************End of Example 3***************************************
Example 4

Note:- Before this just give some introduciton on what will happen if we use Dispatchers.IO / Default / Main

Use CoroutineScope Explicitly
Theorey

import kotlinx.coroutines.*

fun main() {
   println("Main Method Start Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   val scope = CoroutineScope(Dispatchers.IO) // Create a scope
   scope.launch {
      println("Inside custom CoroutineScope")
      println("Inside launch  Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   }
   println("Main Method Start Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   Thread.sleep(1000) // Wait for coroutine to complete
}

C:\Users\vinayts\.jdks\corretto-1.8.0_312\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=50185:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\charsets.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\access-bridge-64.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\cldrdata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\dnsns.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jaccess.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jfxrt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\localedata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\nashorn.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunec.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunjce_provider.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunmscapi.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunpkcs11.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\zipfs.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jce.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfr.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfxswt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jsse.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\management-agent.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\resources.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\rt.jar;D:\Workspace\kotlincode\build\classes\kotlin\main;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-android\1.3.9\df17db5e329363c4e9cc7bf5b661ce3723a3e460\kotlinx-coroutines-android-1.3.9.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.7.10\d2abf9e77736acc4450dc4a3f707fa2c10f5099d\kotlin-stdlib-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.7.10\bac80c520d0a9e3f3673bc2658c6ed02ef45a76a\kotlin-stdlib-common-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-core-jvm\1.3.9\4be434f5e86c1998a273e7f19a7286440894f0b0\kotlinx-coroutines-core-jvm-1.3.9.jar CheckLogicKt
Main Method Start Thread Name main ID 1
Main Method Start Thread Name main ID 1
Inside custom CoroutineScope
Inside launch  Thread Name DefaultDispatcher-worker-1 ID 12

Process finished with exit code 0


Test Case 4
Theorey(Test case 4)

*******************************End of Example 4***************************************
Example 5
Theorey

Usage of launch coroutine .

launch Coroutine Builder in Kotlin

The launch coroutine builder is used to start a new coroutine that does not return a result.
It is mainly used for fire-and-forget tasks, meaning it runs asynchronously without blocking the calling thread.


When to Use launch

Use launch when:

You don’t need a return value.
Example: Performing background tasks such as logging, UI updates, or database operations.

You want to run multiple coroutines concurrently.
Example: Fetching data from multiple sources in parallel.

You are inside a CoroutineScope.
Example: launch is typically used inside runBlocking, GlobalScope, or a custom CoroutineScope.


🔹 Important Points About launch
✅ 1. Fire-and-Forget (No Return Value)

launch does not return a value; it just starts a coroutine.
If you need a return value, use async instead.
✅ 2. Runs in the Background

The coroutine runs independently of the calling function.
Execution continues immediately after calling launch.
✅ 3. Structured Concurrency (Avoid GlobalScope)

Prefer using launch inside a structured CoroutineScope to prevent memory leaks.
Using GlobalScope.launch creates a coroutine that lives as long as the app, which can lead to issues.
✅ 4. Can Be Canceled

launch can be canceled using job.cancel() if needed.

🔹 Example Scenarios Where launch is Useful
1️⃣ Background Task Without Return Value

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("Task complete!")
    }
    println("Main function continues...")
}


2️⃣ Running Multiple Coroutines Concurrently

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("Task 1 complete!")
    }

    launch {
        delay(500L)
        println("Task 2 complete!")
    }
}


3️⃣ Cancelling a launch Coroutine

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(5) { i ->
            println("Running Task $i")
            delay(500L)
        }
    }

    delay(1200L)
    job.cancel() // Cancels the coroutine
    println("Task cancelled")
}

The coroutine starts and executes tasks.
After 1200ms, it is cancelled, so remaining iterations do not execute.

Test Case 5
Theorey(Test case 5)
*******************************End of Example 5***************************************
Example 6
Theorey

async Coroutine.

import kotlinx.coroutines.async
fun main() {
        async {

        }

}

e: D:\Workspace\kotlincode\src\main\kotlin\CheckLogic.kt: (3, 9): Unresolved reference: async

Test Case 6

Theorey(Test case 6)

*******************************End of Example 6***************************************
Example 7
Theorey

import kotlinx.coroutines.*

fun main() {
   println("Main Method Start Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   val scope = CoroutineScope(Dispatchers.IO) // Create a scope
   scope.async {
      println("Inside async  Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   }
   println("Main Method Start Thread Name ${Thread.currentThread().name} ID ${Thread.currentThread().id}")
   Thread.sleep(1000) // Wait for coroutine to complete
}

C:\Users\vinayts\.jdks\corretto-1.8.0_312\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=50481:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\charsets.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\access-bridge-64.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\cldrdata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\dnsns.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jaccess.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jfxrt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\localedata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\nashorn.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunec.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunjce_provider.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunmscapi.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunpkcs11.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\zipfs.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jce.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfr.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfxswt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jsse.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\management-agent.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\resources.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\rt.jar;D:\Workspace\kotlincode\build\classes\kotlin\main;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-android\1.3.9\df17db5e329363c4e9cc7bf5b661ce3723a3e460\kotlinx-coroutines-android-1.3.9.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.7.10\d2abf9e77736acc4450dc4a3f707fa2c10f5099d\kotlin-stdlib-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.7.10\bac80c520d0a9e3f3673bc2658c6ed02ef45a76a\kotlin-stdlib-common-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-core-jvm\1.3.9\4be434f5e86c1998a273e7f19a7286440894f0b0\kotlinx-coroutines-core-jvm-1.3.9.jar CheckLogicKt
Main Method Start Thread Name main ID 1
Main Method Start Thread Name main ID 1
Inside async  Thread Name DefaultDispatcher-worker-2 ID 13

Process finished with exit code 0


Test Case 7
Theorey(Test case 7)
*******************************End of Example 7***************************************
Example 8
Theorey


Test Case 8
Theorey(Test case 8)
*******************************End of Example 8***************************************
Example 9
Theorey


Test Case 9
Theorey(Test case 9)
*******************************End of Example 9***************************************
Example 10
Theorey


Test Case 10
Theorey(Test case 10)
*******************************End of Example 10***************************************
Example 11
Theorey


Test Case 11
Theorey(Test case 11)
*******************************End of Example 11***************************************
Example 12
Theorey


Test Case 12
Theorey(Test case 12)
*******************************End of Example 12***************************************
Example 13
Theorey


Test Case 13
Theorey(Test case 13)
*******************************End of Example 13***************************************
Example 14
Theorey


Test Case 14
Theorey(Test case 14)
*******************************End of Example 14***************************************
Example 15
Theorey


Test Case 15
Theorey(Test case 15)
*******************************End of Example 15***************************************
Example 16
Theorey


Test Case 16
Theorey(Test case 16)
*******************************End of Example 16***************************************
Example 17
Theorey


Test Case 17
Theorey(Test case 17)
*******************************End of Example 17***************************************
Example 18
Theorey


Test Case 18
Theorey(Test case 18)
*******************************End of Example 18***************************************
Example 19
Theorey


Test Case 19
Theorey(Test case 19)
*******************************End of Example 19***************************************
Example 20
Theorey


Test Case 20
Theorey(Test case 20)
*******************************End of Example 20***************************************
Example 21
Theorey


Test Case 21
Theorey(Test case 21)
*******************************End of Example 21***************************************
Example 22
Theorey


Test Case 22
Theorey(Test case 22)
*******************************End of Example 22***************************************
Example 23
Theorey


Test Case 23
Theorey(Test case 23)
*******************************End of Example 23***************************************
Example 24
Theorey


Test Case 24
Theorey(Test case 24)
*******************************End of Example 24***************************************
Example 25
Theorey


Test Case 25
Theorey(Test case 25)
*******************************End of Example 25***************************************
Example 26
Theorey


Test Case 26
Theorey(Test case 26)
*******************************End of Example 26***************************************
Example 27
Theorey


Test Case 27
Theorey(Test case 27)
*******************************End of Example 27***************************************
Example 28
Theorey


Test Case 28
Theorey(Test case 28)
*******************************End of Example 28***************************************
Example 29
Theorey


Test Case 29
Theorey(Test case 29)
*******************************End of Example 29***************************************
Example 30
Theorey


Test Case 30
Theorey(Test case 30)
*******************************End of Example 30***************************************
Example 31
Theorey


Test Case 31
Theorey(Test case 31)
*******************************End of Example 31***************************************
Example 32
Theorey


Test Case 32
Theorey(Test case 32)
*******************************End of Example 32***************************************
Example 33
Theorey


Test Case 33
Theorey(Test case 33)
*******************************End of Example 33***************************************
Example 34
Theorey


Test Case 34
Theorey(Test case 34)
*******************************End of Example 34***************************************
Example 35
Theorey


Test Case 35
Theorey(Test case 35)
*******************************End of Example 35***************************************
Example 36
Theorey


Test Case 36
Theorey(Test case 36)
*******************************End of Example 36***************************************
Example 37
Theorey


Test Case 37
Theorey(Test case 37)
*******************************End of Example 37***************************************
Example 38
Theorey


Test Case 38
Theorey(Test case 38)
*******************************End of Example 38***************************************
Example 39
Theorey


Test Case 39
Theorey(Test case 39)
*******************************End of Example 39***************************************
Example 40
Theorey


Test Case 40
Theorey(Test case 40)
*******************************End of Example 40***************************************
Example 41
Theorey


Test Case 41
Theorey(Test case 41)
*******************************End of Example 41***************************************
Example 42
Theorey


Test Case 42
Theorey(Test case 42)
*******************************End of Example 42***************************************
Example 43
Theorey


Test Case 43
Theorey(Test case 43)
*******************************End of Example 43***************************************
Example 44
Theorey


Test Case 44
Theorey(Test case 44)
*******************************End of Example 44***************************************
Example 45
Theorey


Test Case 45
Theorey(Test case 45)
*******************************End of Example 45***************************************
Example 46
Theorey


Test Case 46
Theorey(Test case 46)
*******************************End of Example 46***************************************
Example 47
Theorey


Test Case 47
Theorey(Test case 47)
*******************************End of Example 47***************************************
Example 48
Theorey


Test Case 48
Theorey(Test case 48)
*******************************End of Example 48***************************************
Example 49
Theorey


Test Case 49
Theorey(Test case 49)
*******************************End of Example 49***************************************
Example 50
Theorey


Test Case 50
Theorey(Test case 50)
*******************************End of Example 50***************************************
Example 51
Theorey


Test Case 51
Theorey(Test case 51)
*******************************End of Example 51***************************************
Example 52
Theorey


Test Case 52
Theorey(Test case 52)
*******************************End of Example 52***************************************
Example 53
Theorey


Test Case 53
Theorey(Test case 53)
*******************************End of Example 53***************************************
Example 54
Theorey


Test Case 54
Theorey(Test case 54)
*******************************End of Example 54***************************************
Example 55
Theorey


Test Case 55
Theorey(Test case 55)
*******************************End of Example 55***************************************
Example 56
Theorey


Test Case 56
Theorey(Test case 56)
*******************************End of Example 56***************************************
Example 57
Theorey


Test Case 57
Theorey(Test case 57)
*******************************End of Example 57***************************************
Example 58
Theorey


Test Case 58
Theorey(Test case 58)
*******************************End of Example 58***************************************
Example 59
Theorey


Test Case 59
Theorey(Test case 59)
*******************************End of Example 59***************************************
Example 60
Theorey


Test Case 60
Theorey(Test case 60)
*******************************End of Example 60***************************************
Example 61
Theorey


Test Case 61
Theorey(Test case 61)
*******************************End of Example 61***************************************
Example 62
Theorey


Test Case 62
Theorey(Test case 62)
*******************************End of Example 62***************************************
Example 63
Theorey


Test Case 63
Theorey(Test case 63)
*******************************End of Example 63***************************************
Example 64
Theorey


Test Case 64
Theorey(Test case 64)
*******************************End of Example 64***************************************
Example 65
Theorey


Test Case 65
Theorey(Test case 65)
*******************************End of Example 65***************************************
Example 66
Theorey


Test Case 66
Theorey(Test case 66)
*******************************End of Example 66***************************************
Example 67
Theorey


Test Case 67
Theorey(Test case 67)
*******************************End of Example 67***************************************
Example 68
Theorey


Test Case 68
Theorey(Test case 68)
*******************************End of Example 68***************************************
Example 69
Theorey


Test Case 69
Theorey(Test case 69)
*******************************End of Example 69***************************************
Example 70
Theorey


Test Case 70
Theorey(Test case 70)
*******************************End of Example 70***************************************
Example 71
Theorey


Test Case 71
Theorey(Test case 71)
*******************************End of Example 71***************************************
Example 72
Theorey


Test Case 72
Theorey(Test case 72)
*******************************End of Example 72***************************************
Example 73
Theorey


Test Case 73
Theorey(Test case 73)
*******************************End of Example 73***************************************
Example 74
Theorey


Test Case 74
Theorey(Test case 74)
*******************************End of Example 74***************************************
Example 75
Theorey


Test Case 75
Theorey(Test case 75)
*******************************End of Example 75***************************************
Example 76
Theorey


Test Case 76
Theorey(Test case 76)
*******************************End of Example 76***************************************
Example 77
Theorey


Test Case 77
Theorey(Test case 77)
*******************************End of Example 77***************************************
Example 78
Theorey


Test Case 78
Theorey(Test case 78)
*******************************End of Example 78***************************************
Example 79
Theorey


Test Case 79
Theorey(Test case 79)
*******************************End of Example 79***************************************
Example 80
Theorey


Test Case 80
Theorey(Test case 80)
*******************************End of Example 80***************************************
Example 81
Theorey


Test Case 81
Theorey(Test case 81)
*******************************End of Example 81***************************************
Example 82
Theorey


Test Case 82
Theorey(Test case 82)
*******************************End of Example 82***************************************
Example 83
Theorey


Test Case 83
Theorey(Test case 83)
*******************************End of Example 83***************************************
Example 84
Theorey


Test Case 84
Theorey(Test case 84)
*******************************End of Example 84***************************************
Example 85
Theorey


Test Case 85
Theorey(Test case 85)
*******************************End of Example 85***************************************
Example 86
Theorey


Test Case 86
Theorey(Test case 86)
*******************************End of Example 86***************************************
Example 87
Theorey


Test Case 87
Theorey(Test case 87)
*******************************End of Example 87***************************************
Example 88
Theorey


Test Case 88
Theorey(Test case 88)
*******************************End of Example 88***************************************
Example 89
Theorey


Test Case 89
Theorey(Test case 89)
*******************************End of Example 89***************************************
Example 90
Theorey


Test Case 90
Theorey(Test case 90)
*******************************End of Example 90***************************************
Example 91
Theorey


Test Case 91
Theorey(Test case 91)
*******************************End of Example 91***************************************
Example 92
Theorey


Test Case 92
Theorey(Test case 92)
*******************************End of Example 92***************************************
Example 93
Theorey


Test Case 93
Theorey(Test case 93)
*******************************End of Example 93***************************************
Example 94
Theorey


Test Case 94
Theorey(Test case 94)
*******************************End of Example 94***************************************
Example 95
Theorey


Test Case 95
Theorey(Test case 95)
*******************************End of Example 95***************************************
Example 96
Theorey


Test Case 96
Theorey(Test case 96)
*******************************End of Example 96***************************************
Example 97
Theorey


Test Case 97
Theorey(Test case 97)
*******************************End of Example 97***************************************
Example 98
Theorey


Test Case 98
Theorey(Test case 98)
*******************************End of Example 98***************************************
Example 99
Theorey


Test Case 99
Theorey(Test case 99)
*******************************End of Example 99***************************************
Example 100
Theorey


Test Case 100
Theorey(Test case 100)
*******************************End of Example 100***************************************