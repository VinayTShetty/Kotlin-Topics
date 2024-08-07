package com.education.myapplication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * This producer code <getShippingDetails>  won t be executed, untill there is consumer.
 */
fun getShippingDetails(userlist:List<User>):Flow<ShippingDetails>{
    return flow {
        userlist.forEach {
            val user=getUserid(it.id)
            val shippingAddres=getshippingAddress(user.address)
            val shippingDetails=calculateshippingCharges(shippingAddres)
        }
    }
}

fun main() {
    val userlist=getUserlist();
    /**
     * Here we are not defined collect function.So consumer is not executed/not active.
     */
    val shippeingtdetais= getShippingDetails(userlist)
}


/**
  please note its dummy code.
  Just for understanding.
 */