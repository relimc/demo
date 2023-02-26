package com.example.android.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel: ViewModel() {
    var userData: MutableLiveData<UserInfo> = MutableLiveData()
    var switchMapData: MutableLiveData<UserInfo> = MutableLiveData()

    fun getUserInfo() {
        val user = UserInfo("李四", (1000..5000).random())
        userData.postValue(user)
    }

    fun getUserName(userInfo: UserInfo): LiveData<UserInfo> {
        userInfo.name = userInfo.name + "switchMap"
        switchMapData.value = userInfo
        return switchMapData
    }
}