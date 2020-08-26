package com.innocent.learn.criminalintent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.innocent.learn.criminalintent.model.Crime
import com.innocent.learn.criminalintent.repository.CrimeRepository
import java.io.File
import java.util.*

class CrimeDetailViewModel() : ViewModel() {
    private val _crimeRepository = CrimeRepository.get()
    private val _crimeIdLiveData = MutableLiveData<UUID>()
    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(_crimeIdLiveData) { crimeId ->
        _crimeRepository.getCrime(crimeId)
    }

    fun loadCrime(crimeId: UUID) {
        _crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        _crimeRepository.updateCrime(crime)
    }

    fun getPhotoFile(crime: Crime): File = _crimeRepository.getPhotoFile(crime)
}