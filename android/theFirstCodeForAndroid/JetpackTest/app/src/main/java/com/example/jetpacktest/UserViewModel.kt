package com.example.jetpacktest

import androidx.annotation.RequiresPermission.Read
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    private val userIdLiveData = MutableLiveData<String>()

    // map 是对原始数据（）的再加工
    // switchMap 是对外部数据（比如数据库）的再加工

    // 这个方法的作用是将实际包含数据的LiveData(userLiveData)和仅用于观察数据的LiveData(userName)进行转换。
    // 当userLiveData的数据发生变化时，map()方法会监听到变化并执行转换函数中的逻辑，然后再将转换之后的数据通知给userName的观察者。
    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.firstName} ${it.lastName}"
    }

    // 一旦userIdLiveData的数据发生变化，那么观察userIdLiveData的switchMap()方法就会执行，并且调用我们编写的转换函数。
    // 然后在转换函数中调用Repository.getUser()方法获取真正的用户数据。
    // 同时，switchMap()方法会将Repository.getUser()方法返回的LiveData对象转换成一个可观察的LiveData对象，
    // 对于Activity而言，只要去观察这个LiveData对象就可以了。
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it)
    }

    fun changeUser(user: User) {
        userLiveData.value = user
    }

//    fun getUser(userId: String): LiveData<User> {
//        return Repository.getUser(userId)
//    }
    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

}