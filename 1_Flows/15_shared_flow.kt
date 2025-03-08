package com.education.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
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
            producer().collect(){
                    Log.d(TAG, "Consumer 1 onCreate: Values ${it}")
                }
        }

        GlobalScope.launch(Dispatchers.Main) {
           producer().collect(){
                delay(2500)
                Log.d(TAG, "Consumer 2 onCreate: Values ${it}")
            }
        }
    }

    fun producer(): Flow<Int> {
    val mutableSharedFlow= MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list= listOf<Int>(1,2,3,4,5,)
            list.forEach{
                mutableSharedFlow.emit(it)
              //  delay(1000)
            }
        }
        return mutableSharedFlow
    }
}


/**
Shared Flow.
 Suppose if we want out flow Object not to independent.It should be shared between all consumers. Then we can go for Shared Flow.
 Shared Flow is Hot in nature.

 Hot flow :- If someone is receiving the data.Everyone will get the same data, irrespective of joining.
 Example is Movie show.

 Mutable shared flow is something we can change.


 The return type here is flow, because its usually not recomended to expose mutable flow Object.


 Note:- In this example, both consumer are getting values from the begining, as its wrong.

 */

