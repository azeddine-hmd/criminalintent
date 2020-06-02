package com.innocent.learn.criminalintent.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innocent.learn.criminalintent.R
import com.innocent.learn.criminalintent.fragment.CrimeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        var currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_container_view, fragment).commit()
        }
    }
}
