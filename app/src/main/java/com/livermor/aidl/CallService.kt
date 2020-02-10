package com.livermor.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.livermor.sharedmem.ICallService
import com.livermor.sharedmem.ICallback

class CallService : Service() {

    override fun onBind(intent: Intent): IBinder = object : ICallService.Stub() {
        override fun getMessage(name: String): String = "hello, result is $name"
        override fun getResult(n: Int, m: Int): Int = n * m
        override fun onMessageBuilt(callback: ICallback, msg: String) = callback.onCallback(msg)
        override fun sort(arr: IntArray) = arr.sort()
    }
}