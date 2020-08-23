package com.innocent.learn.criminalintent

import android.app.Application
import com.innocent.learn.criminalintent.repository.CrimeRepository

class CriminalIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}