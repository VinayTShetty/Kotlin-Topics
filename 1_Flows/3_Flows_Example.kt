package com.education.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    val channel=Channel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val getDatalist=producer()
            getDatalist.collect{
                Log.d(TAG, "onCreate: received Data ${it}")
            }
        }
    }

    /**
     * Its a top lelvel function.
     * Producer
     */
    fun producer()= flow<Int> {
        /**
         * Its having a coroutine scope,so only we can write delay here.
         * This is a lambda function.
         */
        val list= listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}