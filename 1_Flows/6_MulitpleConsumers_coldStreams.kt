package com.education.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity_"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val job= GlobalScope.launch {
            val data=producer()
            data.collect{
                Log.d(TAG, "P1: ${it}")
            }
        }

        GlobalScope.launch {
            delay(3500)
            val data=producer()
            data.collect{
                Log.d(TAG, "P2: ${it}")
            }
        }
    }


    fun producer()= flow<Int> {
        val listofNumbers= listOf<Int>(1,2,3,4,5,6)
        listofNumbers.forEach {
            delay(1000)
            emit(it)
        }
    }
}


/**
 * Topic :- Mulitple Consumers
 *   All consumers will get data from the begining.
 *   Data will not be lost to any consumers.
 *   In case of cold streams all consumers are independent, everyone will receive data
 *
 */