package com.innocent.learn.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import com.innocent.learn.criminalintent.model.Crime
import com.innocent.learn.criminalintent.repository.CrimeRepository

class CrimeListViewModel : ViewModel() {
    private val _crimeRepository = CrimeRepository.get()
    val crimeListLiveData = _crimeRepository.getCrimes()

    fun addCrime(crime: Crime) {
        _crimeRepository.addCrime(crime)
    }
}
