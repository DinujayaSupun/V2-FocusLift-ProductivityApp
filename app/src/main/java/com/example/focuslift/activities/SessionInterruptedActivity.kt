package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class SessionInterruptedActivity : AppCompatActivity() {
    
    private lateinit var tvInterruptedTitle: TextView
    private lateinit var tvInterruptedMessage: TextView
    private lateinit var tvFocusTimeCompleted: TextView
    private lateinit var tvSessionsCompleted: TextView
    private lateinit var tvBreaksTaken: TextView
    private lateinit var tvFocusType: TextView
    private lateinit var tvMotivationalMessage: TextView
    
    private lateinit var btnTryAgain: MaterialButton
    private lateinit var btnGoHome: MaterialButton
    private lateinit var btnViewStats: MaterialButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_interrupted)
        
        initializeViews()
        loadSessionData()
        setupListeners()
    }
    
    private fun initializeViews() {
        tvInterruptedTitle = findViewById(R.id.tvInterruptedTitle)
        tvInterruptedMessage = findViewById(R.id.tvInterruptedMessage)
        tvFocusTimeCompleted = findViewById(R.id.tvFocusTimeCompleted)
        tvSessionsCompleted = findViewById(R.id.tvSessionsCompleted)
        tvBreaksTaken = findViewById(R.id.tvBreaksTaken)
        tvFocusType = findViewById(R.id.tvFocusType)
        tvMotivationalMessage = findViewById(R.id.tvMotivationalMessage)
        
        btnTryAgain = findViewById(R.id.btnTryAgain)
        btnGoHome = findViewById(R.id.btnGoHome)
        btnViewStats = findViewById(R.id.btnViewStats)
    }
    
    private fun loadSessionData() {
        intent.extras?.let { extras ->
            val focusTimeCompleted = extras.getInt("focusTimeCompleted", 0)
            val sessionsCompleted = extras.getInt("sessionsCompleted", 0)
            val breaksTaken = extras.getInt("breaksTaken", 0)
            val focusType = extras.getString("focusType", "Custom")
            val totalTime = extras.getInt("totalTime", 0)
            
            // Update UI with session data
            tvFocusTimeCompleted.text = "${focusTimeCompleted} min"
            tvSessionsCompleted.text = "$sessionsCompleted sessions"
            tvBreaksTaken.text = "$breaksTaken breaks"
            tvFocusType.text = focusType
            
            // Calculate completion percentage
            val completionPercentage = if (totalTime > 0) {
                ((focusTimeCompleted.toFloat() / totalTime) * 100).toInt()
            } else 0
            
            // Set motivational message based on completion
            when {
                completionPercentage >= 80 -> {
                    tvMotivationalMessage.text = "You were so close! Don't give up on your goals."
                }
                completionPercentage >= 50 -> {
                    tvMotivationalMessage.text = "You made good progress! Every minute counts."
                }
                completionPercentage >= 25 -> {
                    tvMotivationalMessage.text = "You got started! That's the hardest part."
                }
                else -> {
                    tvMotivationalMessage.text = "Every journey begins with a single step. Try again!"
                }
            }
        }
    }
    
    private fun setupListeners() {
        btnTryAgain.setOnClickListener {
            // Navigate back to focus session setup
            val intent = Intent(this, FocusSessionSetupActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        btnGoHome.setOnClickListener {
            // Navigate back to main activity
            finish()
        }
        
        btnViewStats.setOnClickListener {
            // Navigate to analytics/statistics
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }
    }
}
