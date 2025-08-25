package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import android.widget.TextView
import android.widget.ImageView

class FocusSessionCompletedActivity : AppCompatActivity() {
    
    private lateinit var btnNewSession: MaterialButton
    private lateinit var btnGoHome: MaterialButton
    private lateinit var btnViewStats: MaterialButton
    
    private lateinit var tvCongratulations: TextView
    private lateinit var tvFocusType: TextView
    private lateinit var tvTotalTime: TextView
    private lateinit var tvSessionsCompleted: TextView
    private lateinit var tvBreaksTaken: TextView
    private lateinit var tvFocusTimeCompleted: TextView
    
    private lateinit var cardAchievement: MaterialCardView
    private lateinit var tvAchievementTitle: TextView
    private lateinit var tvAchievementDescription: TextView
    private lateinit var ivAchievement: ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_session_completed)
        
        loadSessionData()
        initializeViews()
        setupListeners()
        showAchievement()
    }
    
    private fun loadSessionData() {
        intent.extras?.let { extras ->
            val totalFocusTime = extras.getInt("totalFocusTime", 0)
            val sessionsCompleted = extras.getInt("sessionsCompleted", 0)
            val breaksTaken = extras.getInt("breaksTaken", 0)
            val focusType = extras.getString("focusType", "Custom")
            val totalTime = extras.getInt("totalTime", 0)
            
            // Update UI with session data
            updateSessionSummary(totalFocusTime, sessionsCompleted, breaksTaken, focusType, totalTime)
        }
    }
    
    private fun initializeViews() {
        btnNewSession = findViewById(R.id.btnNewSession)
        btnGoHome = findViewById(R.id.btnGoHome)
        btnViewStats = findViewById(R.id.btnViewStats)
        
        tvCongratulations = findViewById(R.id.tvCongratulations)
        tvFocusType = findViewById(R.id.tvFocusType)
        tvTotalTime = findViewById(R.id.tvTotalTime)
        tvSessionsCompleted = findViewById(R.id.tvSessionsCompleted)
        tvBreaksTaken = findViewById(R.id.tvBreaksTaken)
        tvFocusTimeCompleted = findViewById(R.id.tvFocusTimeCompleted)
        
        cardAchievement = findViewById(R.id.cardAchievement)
        tvAchievementTitle = findViewById(R.id.tvAchievementTitle)
        tvAchievementDescription = findViewById(R.id.tvAchievementDescription)
        ivAchievement = findViewById(R.id.ivAchievement)
    }
    
    private fun setupListeners() {
        btnNewSession.setOnClickListener {
            // Start a new focus session
            finish()
        }
        
        btnGoHome.setOnClickListener {
            // Navigate to home
            finish()
        }
        
        btnViewStats.setOnClickListener {
            // Navigate to analytics/stats
            // This could open the analytics screen
        }
    }
    
    private fun updateSessionSummary(
        totalFocusTime: Int,
        sessionsCompleted: Int,
        breaksTaken: Int,
        focusType: String,
        totalTime: Int
    ) {
        tvFocusType.text = focusType
        tvTotalTime.text = "${totalTime} min"
        tvSessionsCompleted.text = "$sessionsCompleted"
        tvBreaksTaken.text = "$breaksTaken"
        tvFocusTimeCompleted.text = "${totalFocusTime} min"
        
        // Set congratulations message based on performance
        when {
            sessionsCompleted >= 4 -> {
                tvCongratulations.text = "🎉 Amazing! You're a Focus Master!"
            }
            sessionsCompleted >= 2 -> {
                tvCongratulations.text = "🌟 Great job! You're building focus habits!"
            }
            else -> {
                tvCongratulations.text = "👍 Well done! Every session counts!"
            }
        }
    }
    
    private fun showAchievement() {
        // Show different achievements based on session performance
        val totalFocusTime = intent.extras?.getInt("totalFocusTime", 0) ?: 0
        val sessionsCompleted = intent.extras?.getInt("sessionsCompleted", 0) ?: 0
        
        when {
            totalFocusTime >= 120 -> {
                // 2+ hours of focus
                tvAchievementTitle.text = "🚀 Focus Warrior"
                tvAchievementDescription.text = "You completed over 2 hours of focused work!"
                ivAchievement.setImageResource(R.drawable.ic_timer)
            }
            sessionsCompleted >= 4 -> {
                // 4+ sessions
                tvAchievementTitle.text = "🎯 Session Master"
                tvAchievementDescription.text = "You completed a full session cycle!"
                ivAchievement.setImageResource(R.drawable.ic_timer)
            }
            totalFocusTime >= 60 -> {
                // 1+ hour of focus
                tvAchievementTitle.text = "⭐ Focus Builder"
                tvAchievementDescription.text = "You completed over 1 hour of focused work!"
                ivAchievement.setImageResource(R.drawable.ic_timer)
            }
            else -> {
                // First session
                tvAchievementTitle.text = "🌱 Focus Beginner"
                tvAchievementDescription.text = "Great start! Keep building your focus muscle!"
                ivAchievement.setImageResource(R.drawable.ic_timer)
            }
        }
    }
}
