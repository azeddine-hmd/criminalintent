package com.innocent.learn.criminalintent.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.innocent.learn.criminalintent.database.CrimeDao
import com.innocent.learn.criminalintent.database.CrimeDatabase
import com.innocent.learn.criminalintent.database.migration_1_2
import com.innocent.learn.criminalintent.model.Crime
import java.io.File
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {
    private val crimeDatabase: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2).build()
    private val crimeDao: CrimeDao = crimeDatabase.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()
    private val filesdir = context.applicationContext.filesDir

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(uuid: UUID): LiveData<Crime?> = crimeDao.getCrime(uuid)

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    fun getPhotoFile(crime: Crime): File = File(filesdir, crime.photoFileName)

    companion object {
        private var instance: CrimeRepository? = null

        fun initialize(context: Context) {
            if (instance == null)
                instance = CrimeRepository(context)
        }

        fun get(): CrimeRepository {
            return instance ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
