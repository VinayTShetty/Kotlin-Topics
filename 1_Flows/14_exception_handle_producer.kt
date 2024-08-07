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
            try {
                producer()
                    .collect(){
                        Log.d(TAG, "onCreate: ${it}")
                    }
            }catch (e:java.lang.Exception){
                Log.d(TAG, "onCreate:  ${e.printStackTrace()}  StackTrace=  ${e.stackTrace}")
            }

        }
    }

    fun producer(): Flow<Int> {
     return flow<Int>  { val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
            listofNumbers.forEach {
                Log.d(TAG, "producer: ")
                emit(it)
                throw Exception("Producer Exception")
            }
     }.catch {
             emit(-1)
         Log.d(TAG, "producer: Catch Block ${it.printStackTrace()}")
         /**
          * This is used to handle the exception in catch block only.i.e in producer.
          * Consumer code should not be executed in that scenario. We can go for this approach.
          *
          *
          * If there is a exception happened emit a different value i.e -1
          */
     }
    }
}


/**
 Exception Handling.
 Throwing an exception in Producer
 */