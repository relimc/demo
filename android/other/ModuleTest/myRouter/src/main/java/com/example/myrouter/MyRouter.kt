package com.example.myrouter

import android.app.Activity

object MyRouter {
    val groupMap = HashMap<String, Map<String, Class<Any>>>()
    val routerMap = HashMap<String, Class<Any>>()

    /**
     * @param path /main/ManiActivity
     * @param clazz
     */
    fun register(path: String, clazz: Class<Any>) {
        val strArray = path.split("/")
        if (strArray.size > 2) {
            val groupName = strArray[1]
            val routerName = path
        }
    }

    fun startActivity(activity: Activity, path: String) {

    }
}