package com.example.focuslift.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.focuslift.R
import com.example.focuslift.databinding.ActivityMainBinding
import com.example.focuslift.fragments.FavoritesFragment
import com.example.focuslift.fragments.HomeFragment
import com.example.focuslift.fragments.ProfileFragment
import com.example.focuslift.fragments.QuotesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge-to-edge padding (optional)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // set default fragment
        replaceFragment(HomeFragment())

        // Bottom navigation listener
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> { replaceFragment(HomeFragment()); true }
                R.id.quotesFragment -> { replaceFragment(QuotesFragment()); true }
                R.id.favoritesFragment -> { replaceFragment(FavoritesFragment()); true }
                R.id.profileFragment -> { replaceFragment(ProfileFragment()); true }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
