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
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity_"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val data = producer()
            data.onStart {
                emit(-200) // this function is used to emit values custom values , which are not avaliable in Flows , use case is used to fetch API response show loading.OnCompletion hide loading
                Log.d(TAG, "onCreate: Onstart ")
            }.onCompletion {
                emit(-100)
                Log.d(TAG, "onCreate: onCompletion")
            }.onEach {
                Log.d(TAG, "onCreate: onEach ")
            }.collect {
                Log.d(TAG, "Consumer : ${it}")
            }
        }
    }


    fun producer() = flow<Int> {
        val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
        listofNumbers.forEach {
            delay(1000)
            emit(it)
        }
    }
}

/**
 * Operators in Kotlin
 *  In Flow there are 2 types of operator
 *  Terminal :-Terminal operator start the flow . i.e consumption of the Flow.
 *              ex:- collect {} is a terminal operator.
 *              Terminal operator are suspend operator.
 *
 *            ex:- frist()
 *             get the Frist item of the flow
 *
 *             ex:- toList()
 *             get the items in the Flows as a list.
 *
 * Non-Terminal :-
 *             map{}
 *             Converts the object of one type to another.
 *              ex:- get somthing from api and convert to UI.
 *
 *   Chaining of the operator is also valid.
 *   .map{}
 *   .filter{}
 *
 *    Non-Terminal methods don t have suspend keyword.
 *    It will return a new Flow.
 *
 */