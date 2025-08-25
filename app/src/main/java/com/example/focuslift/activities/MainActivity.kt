package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.focuslift.R
import com.example.focuslift.activities.AnalyticsActivity
import com.example.focuslift.activities.FocusSessionActivity
import com.example.focuslift.activities.GoalsActivity
import com.example.focuslift.activities.TasksActivity
import com.example.focuslift.fragments.FavoritesFragment
import com.example.focuslift.fragments.HomeFragment
import com.example.focuslift.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Set up bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.tasksFragment -> {
                    val intent = Intent(this, TasksActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favoritesFragment -> {
                    loadFragment(FavoritesFragment())
                    true
                }
                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
        
        // Load home fragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
        
        // Check if we need to show a specific fragment (coming from Tasks/Goals)
        handleIntentFragment(intent)
    }
    
    private fun loadFragment(fragment: Fragment) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        } catch (e: Exception) {
            // Handle fragment loading error
            e.printStackTrace()
        }
    }

    // Navigation methods for home fragment buttons
    fun navigateToTimer(view: View) {
        val intent = Intent(this, FocusSessionActivity::class.java)
        startActivity(intent)
    }



    fun navigateToAnalytics(view: View) {
        val intent = Intent(this, AnalyticsActivity::class.java)
        startActivity(intent)
    }

    fun navigateToGoals(view: View) {
        val intent = Intent(this, GoalsActivity::class.java)
        startActivity(intent)
    }
    

    
    private fun handleIntentFragment(intent: Intent?) {
        intent?.let {
            val showFragment = it.getStringExtra("showFragment")
            when (showFragment) {
                "favorites" -> {
                    loadFragment(FavoritesFragment())
                    // Update bottom navigation selection
                    findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.favoritesFragment
                }
                "profile" -> {
                    loadFragment(ProfileFragment())
                    // Update bottom navigation selection
                    findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.profileFragment
                }
            }
        }
    }
}