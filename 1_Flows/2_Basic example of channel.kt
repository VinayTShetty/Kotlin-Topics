package com.education.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    val channel=Channel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            producer()
        }
        CoroutineScope(Dispatchers.Main).launch {
            consumer()
        }
    }

   suspend fun producer(){
        /**
         * send is a suspen function.
         *  It should be written inside a suspend function or coroutine.
         */
      channel.send(1)
      channel.send(2)
    }

   suspend fun consumer(){
        /**
         * receive is a suspen function.
         *  It should be written inside a suspend function or coroutine.
         */
       Log.d(TAG, "consumer: Item 1 : ${channel.receive()}")
       Log.d(TAG, "consumer: Item 2 : ${channel.receive()}")
   }
}