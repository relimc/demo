package com.bignerdranch.android.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.UUID

// 为详情页提供数据
class CrimeDetailViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>()
    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeIdLiveData) {
        crimeRepository.getCrime(it)
    }

    // 将 crimeId 存进 LiveData 容器中，LiveData 数据源发生变化时，观察者会感知到数据变化，然后做出反应
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    // 在后台线程更新数据库
    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }
}