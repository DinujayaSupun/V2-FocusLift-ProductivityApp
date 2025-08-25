package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.focuslift.R
import com.example.focuslift.adapters.OnboardingAdapter
import com.example.focuslift.models.OnboardingItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {
    
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnNext: MaterialButton
    private lateinit var btnGetStarted: MaterialButton
    
    private val onboardingItems = listOf(
        OnboardingItem(
            R.drawable._efed4a2c81cd67ed01b5a45d9f2959b,
            "Fuel Your Focus",
            "Quick focus boosters and daily tools to help you stay sharp.",
            R.color.light_blue_bg
        ),
        OnboardingItem(
            R.drawable._b456ea6a1ade3ea1992fa85cc5152cf,
            "Set Daily Goals",
            "Create small goals and track your progress every day.",
            R.color.light_blue_bg
        ),
        OnboardingItem(
            R.drawable._55ab0d9f56c0052f7ccccc02e92c6e1,
            "Stay Uplifted",
            "Motivational quotes and mood check-ins to keep your energy high.",
            R.color.light_blue_bg
        )
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        
        initializeViews()
        setupViewPager()
        setupButtons()
    }
    
    private fun initializeViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        btnNext = findViewById(R.id.btnNext)
        btnGetStarted = findViewById(R.id.btnGetStarted)
    }
    
    private fun setupViewPager() {
        val adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter
        
        // Connect ViewPager with TabLayout for indicators
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
        
        // Update button visibility based on current page
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonVisibility(position)
            }
        })
    }
    
    private fun setupButtons() {
        btnNext.setOnClickListener {
            if (viewPager.currentItem < onboardingItems.size - 1) {
                viewPager.currentItem += 1
            }
        }
        
        btnGetStarted.setOnClickListener {
            navigateToSignup()
        }
    }
    
    private fun updateButtonVisibility(position: Int) {
        if (position == onboardingItems.size - 1) {
            // Last page
            btnNext.visibility = View.GONE
            btnGetStarted.visibility = View.VISIBLE
        } else {
            // Not last page
            btnNext.visibility = View.VISIBLE
            btnGetStarted.visibility = View.GONE
        }
    }
    
    private fun navigateToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
}
