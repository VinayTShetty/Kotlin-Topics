package com.education.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Disadvantage is this function will be in suspended that untill list gets all the result.
         * This code is called consumer
         */
        CoroutineScope(Dispatchers.Main).launch {
            getUserList().forEach {
                Log.d(TAG, "onCreate: ${it}")
            }
        }
    }

    /**
     * This is called producer
     */
  suspend  fun getUserList():List<String>{
        val userlist= mutableListOf<String>()
        userlist.add(getuser(1))
        userlist.add(getuser(2))
        userlist.add(getuser(3))
        userlist.add(getuser(4))
        userlist.add(getuser(5))
        return userlist
    }

   suspend fun getuser(userId:Int):String{
        delay(5000)
        return "User ${userId}"
    }
}