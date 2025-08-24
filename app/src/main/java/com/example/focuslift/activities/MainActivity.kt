package com.example.focuslift.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.example.focuslift.fragments.FavoritesFragment
import com.example.focuslift.fragments.HomeFragment
import com.example.focuslift.fragments.ProfileFragment
import com.example.focuslift.fragments.QuotesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // default: show Home
        replaceFragment(HomeFragment())

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    replaceFragment(HomeFragment()); true
                }
                R.id.quotesFragment -> {
                    replaceFragment(QuotesFragment()); true
                }
                R.id.favoritesFragment -> {
                    replaceFragment(FavoritesFragment()); true
                }
                R.id.profileFragment -> {
                    replaceFragment(ProfileFragment()); true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
