package com.mukesh.recyclerviewwithdatabinding.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    /**
     * This function is basically used for Calling function from IO Thread and
     * return to the Main Thread
     * ioToMainThread() is a Generic function and it will take 2 parameter
     * A : work -> this is a suspended function
     * B : callback -> which will take return data from
     *                 work function and return the caller
     */
    fun <T : Any> ioToMainThread(work: suspend (() -> T?), callback: (T?) -> Unit) =

        CoroutineScope(Dispatchers.Main).launch {

            val data = CoroutineScope(Dispatchers.IO).async mukesh@{

                return@mukesh work()
            }.await()

            callback(data)
        }


}