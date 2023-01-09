package com.example.networktest

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun request(address: String): String {

    // suspendCoroutine 函数必须在挂起函数或者协程作用域中才能调用
    // request 函数是个挂起函数，所以这里可以使用 suspendCoroutine 函数
    return suspendCoroutine {
        // suspendCoroutine 的 lambda 表达式内的代码会运行在普通线程中
        HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
            override fun onFinish(response: String) {
                it.resume(response)  // 恢复被挂起的协程，并将请求返回的数据当作 suspendCoroutine 函数的返回值
            }
            override fun onError(e: Exception) {
                it.resumeWithException(e)  // 恢复被挂起的协程，并将请求的异常信息抛出
            }
        })
    }
}

fun String.letterCount(): Int {
    var count = 0

    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}