package com.education.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        GlobalScope.launch {
            getNotes().map {
                FormattedNote(it.id, it.isActive,it.title.toString().toUpperCase(),it.description)   // Used to convert one type of Object to another.
            }.filter {
                it.isActive
            }.collect{
                Log.d(TAG, "onCreate: ${it.toString()}")
            }
        }
    }

    fun producer() = flow<Int> {
        val listofNumbers = listOf<Int>(1, 2, 3, 4, 5, 6)
        listofNumbers.forEach {
            emit(it)
        }
    }
}


/**
 *   With Buffer Concept.
 *
 * Output
Time Taken 10061
 */

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


// Create a another kotlin file with DataRepo and add these code. 


package com.education.myapplication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

 fun getNotes():Flow<Notes>{
    val list= listOf<Notes>(
        Notes(1,true,"Frist","Frist Description"),
        Notes(2,false,"Second","Second Description"),
        Notes(3,true,"Third","Third Description"),
        Notes(4,false,"Fourth","Fourth Description"),
    )
    return list.asFlow() // This fucntion converts list to flow.
}


data class Notes(val id:Int,val isActive:Boolean,val title:String,val description:String)
data class FormattedNote(val id:Int,val isActive:Boolean,val title:String,val description:String)




/**

MainActivity_            D  onCreate: FormattedNote(id=1, isActive=true, title=FRIST, description=Frist Description)
MainActivity_            D  onCreate: FormattedNote(id=3, isActive=true, title=THIRD, description=Third Description)

 */