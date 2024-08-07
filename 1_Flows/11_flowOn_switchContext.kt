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
import kotlinx.coroutines.flow.flowOn
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
            producer().flowOn(Dispatchers.IO)
                .collect(){
                Log.d(TAG, "onCreate: Collector Thread ${Thread.currentThread().name}")
            }
        }
    }

    fun producer() = flow<Int> {
        val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
            listofNumbers.forEach {
                emit(it)
                Log.d(TAG, "Producer: Producer Thread ${Thread.currentThread().name}")
            }

    }
}

/**
 Using flowOn we can switch the context of the Coroutine.

 example :-
 producer().flowOn(Dispatcher.IO)    ---> what ever we have written before  .flowOn will be executed on the IO Thread.
           .collect()     ---> This part will be executed on the Main Thread

 */
