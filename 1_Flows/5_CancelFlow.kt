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
                Log.d(TAG, "onCreate: ${it}")
            }
        }

        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }
    }


    fun producer()= flow<Int> {
        val listofNumbers= listOf<Int>(1,2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,17,18,19,20)
        listofNumbers.forEach {
            delay(1000)
            emit(it)
        }
    }
}


/**
 * Notes:- There is not default method to cancel the flow.
 *         Flow s are cold stream.
 *         If there are no consumer / even 1 consumer it will by default get cancelled.
 *
 *         In above code to cancel the coroutine , using job Object.
 */