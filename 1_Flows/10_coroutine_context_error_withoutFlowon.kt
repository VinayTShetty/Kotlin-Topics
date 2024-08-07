package com.education.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity_"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main) {
            producer().collect(){
                Log.d(TAG, "onCreate: Collector Thread ${Thread.currentThread().name}")
            }
        }
    }

    fun producer() = flow<Int> {
        val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
        withContext(Dispatchers.IO){
            listofNumbers.forEach {
                emit(it)
                Log.d(TAG, "Producer: Producer Thread ${Thread.currentThread().name}")
            }
        }
    }
}

/**
 * General Points
 *
 * Scenario :- Suppose developer wants to do a network call.
 *             Call API in IO thread and UI change in Main Thread then in kotlin we can use withContext() to change the Thread.
 *             This code will crash bcoz we explicitly need to use flowon when switching context()
 *
 * 1) On which Thred the consumer is exected on the same thread the Producer will be executed by default.
 *    Here if we observe by default Producer is executed on the Main Thread.
 *
 * 2)Flows will preserve the coroutine context.,Flows will assumen that on which context u have collected it.
 *   on the same context you will be prodcing it.
 *   To chnage the context we need to use flowon
 *
 */


/**
 * Error with flowOn

FATAL EXCEPTION: main
Process: com.education.myapplication, PID: 16717
java.lang.IllegalStateException: Flow invariant is violated:
Flow was collected in [StandaloneCoroutine{Active}@d4f3917, Dispatchers.Main],
but emission happened in [DispatchedCoroutine{Active}@4056ed, Dispatchers.IO].
Please refer to 'flow' documentation or use 'flowOn' instead
at kotlinx.coroutines.flow.internal.SafeCollector_commonKt.checkContext(SafeCollector.common.kt:85)
at kotlinx.coroutines.flow.internal.SafeCollector.checkContext(SafeCollector.kt:106)
at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:83)
at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
at com.education.myapplication.MainActivity$producer$1$1.invokeSuspend(MainActivity.kt:33)
at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
at kotlinx.coroutines.internal.LimitedDispatcher.run(LimitedDispatcher.kt:42)
at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:95)
at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:570)
at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:750)
at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:677)
at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:664)
Suppressed: kotlinx.coroutines.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@d4f3917, Dispatchers.Main]
UAH_JNI                  E  uah_event_acquire ! cmdId: 0 ,strPkg: com.education.myapplication, strIdent: com.android.server.wm, timeout: 600


 */