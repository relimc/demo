package com.example.CriminalIntent

import androidx.lifecycle.ViewModel

class CrimeListViewModel: ViewModel() {
    /*
    val crimes = mutableListOf<Crime>()
    init {
        for (i in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
     */
    private val repository = CrimeRepository.get()
    val crimeListLiveData = repository.getCrimes()
    fun addCrime(crime: Crime) = repository.addCrime(crime)
}