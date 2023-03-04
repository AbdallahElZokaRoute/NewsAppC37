package com.route.newsappc37.api.coroutines

import android.util.Log
import kotlinx.coroutines.delay


class MyThreadWorker : Thread("Abdallah Thread") {


    override fun run() {
        Log.e("TAG", "onViewCreated2:${Thread.currentThread().name} ")
        for (i in 0..10) {

            Log.e("TAG", "run: $i")
        }
    }
}

suspend fun printTextAfterDelay(name: String) {
    delay(3000)
    Log.e("TAG", "printTextAfterDelay: $name")
}

fun printTextAfterThreadDelay(name: String) {
    Thread.sleep(3000)
    Log.e("TAG", "printTextAfterDelay: $name")
}
/***
 *      Coroutines ->
 *           Retrofit -> Callbacks
 *
 *                              Callbacks                   RxJava(Beyond The Course Scope)            Coroutines
 *      Stop/Resume              callback                   Observer(Observable/Pattern Pattern)        suspend
 *      Background Threads       Create New Thread          .subscribeOn(Thread.IO)                    Context
 *                                                                                                     (Dispatchers)
 *                                                          .ObserveOn(Thread.Main)
 *                              / Handled in Libraries
 *
 *      Control(Cancel Job)     Hard                        dispose -> Verify                           Scopes
 *
 *
 */