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
            producer()
                .map {
                    delay(1000)
                    it*2
                    Log.d(TAG, "Map Function ${Thread.currentThread().name}")
                }.filter {
                    delay(500)
                    Log.d(TAG, "Filter Function ${Thread.currentThread().name}")
                    it <8
                }.flowOn(Dispatchers.IO)
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
Using flowOn at the end,
 makes the previous implementation use the same context which is mentioned in flowon
 */
