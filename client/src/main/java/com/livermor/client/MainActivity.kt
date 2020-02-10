package com.livermor.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.livermor.sharedmem.ICallService
import com.livermor.sharedmem.ICallback
import com.livermor.sharedmem.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {

    private var callService: ICallService? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            callService = ICallService.Stub.asInterface(service)
            toast("Service connected")
        }

        override fun onServiceDisconnected(name: ComponentName) {
            callService = null
            toast("Service disconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMultiply.setOnClickListener {
            val n = parseInt(etNum1.text.toString())
            val m = parseInt(etNum2.text.toString())
            callService?.let { service ->
                val result = service.getResult(n, m)
                val str = service.getMessage(result.toString())
                service.onMessageBuilt(object : ICallback.Stub() {
                    override fun onCallback(msg: String) {
                        toast(str)
                    }
                }, str)
            }
        }
        btnSort.setOnClickListener {
            val unsortedArr = intArrayOf(4,2,1,5,1,9,7,3)
            callService?.sort(unsortedArr)
            toast(unsortedArr.contentToString())
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent("com.livermor.aidl.CallService").setPackage("com.livermor.aidl")
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}
