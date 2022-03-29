package com.example.test_vnpay

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
//import vn.zalopay.sdk.Environment
//import vn.zalopay.sdk.ZaloPayError
//import vn.zalopay.sdk.ZaloPaySDK
//import vn.zalopay.sdk.listeners.PayOrderListener
import com.hlsolutions.flutter_hl_vnpay.flutter_hl_vnpay.FlutterHlVnpayPlugin

class MainActivity: FlutterActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        ZaloPaySDK.init(1254, Environment.SANDBOX); // Merchant AppID
//    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.data != null && intent.data.toString().contains("paymentback"))
            FlutterHlVnpayPlugin.channel.invokeMethod("paymentback", intent.data.toString())
//        if (intent.data != null && intent.data.toString().contains("demozpdk"))
//            ZaloPaySDK.getInstance().onResult(intent)
    }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val channelPayOrder = "flutter.native/channelPayOrder"
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelPayOrder)
                .setMethodCallHandler { call, result ->
                     if (call.method == "paymentback") {
                        //response from VnPay
                        Log.d("ZaloPay: ", "response from VnPay")
                        result.success(call.method)
                    } else {
                        result.success("Payment failed")
                    }
                }
    }
}
