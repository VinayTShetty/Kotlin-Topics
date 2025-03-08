package com.education.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
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
           val result= producer().collect(){
               delay(500)
                    Log.d(TAG, "Consumer 1 onCreate: Values ${it}")
                }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result= producer().collect(){
                delay(1000)
                Log.d(TAG, "Consumer 2 onCreate: Values ${it}")
            }
        }
    }

    fun producer(): Flow<Int> {
     return flow<Int>  { val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
            listofNumbers.forEach {
                delay(1000)
                emit(it)
            }
     }
    }
}


/**
  If we comment consumer the producer code won t be executed.

  Each flow will have an independent Object.
 For each consumer will get a different Flow Object.
 And it will always  start from begining.

 */

