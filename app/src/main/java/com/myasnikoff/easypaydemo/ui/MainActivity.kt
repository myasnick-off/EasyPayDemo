package com.myasnikoff.easypaydemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.myasnikoff.easypaydemo.R

class MainActivity : AppCompatActivity(), NavigationProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }
}