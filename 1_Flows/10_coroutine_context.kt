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
        listofNumbers.forEach {
            emit(it)
            Log.d(TAG, "Producer: Producer Thread ${Thread.currentThread().name}")
        }
    }
}

/**
 * General Points
 *
 * 1) On which Thred the consumer is exected on the same thread the Producer will be executed by default.
 *    Here if we observe by default Producer is executed on the Main Thread.
 */


