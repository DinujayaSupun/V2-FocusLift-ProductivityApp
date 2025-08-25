package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.slider.Slider

class FocusSessionSetupActivity : AppCompatActivity() {
    
    private lateinit var btnStartSession: MaterialButton
    private lateinit var btnBack: MaterialButton
    
    // Focus Time Settings
    private lateinit var sliderFocusTime: Slider
    private lateinit var tvFocusTime: TextView
    private lateinit var tvFocusTimeLabel: TextView
    
    // Break Time Settings
    private lateinit var sliderBreakTime: Slider
    private lateinit var tvBreakTime: TextView
    
    // Session Count Settings
    private lateinit var sliderSessionCount: Slider
    private lateinit var tvSessionCount: TextView
    
    // Sound Settings
    private lateinit var switchAmbientSound: SwitchMaterial
    private lateinit var switchTimerSound: SwitchMaterial
    private lateinit var switchBreakReminder: SwitchMaterial
    
    // Focus Mode Settings
    private lateinit var switchStrictMode: SwitchMaterial
    private lateinit var switchAutoStartBreaks: SwitchMaterial
    private lateinit var switchScreenLock: SwitchMaterial
    
    // Focus Session Type
    private lateinit var cardDeepWork: MaterialCardView
    private lateinit var cardPomodoro: MaterialCardView
    private lateinit var cardCustom: MaterialCardView
    
    private var selectedFocusType = "Deep Work"
    private var focusTimeMinutes = 25
    private var breakTimeMinutes = 5
    private var sessionCount = 4
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_session_setup)
        
        initializeViews()
        setupListeners()
        updateDisplay()
    }
    
    private fun initializeViews() {
        btnStartSession = findViewById(R.id.btnStartSession)
        btnBack = findViewById(R.id.btnBack)
        
        // Focus Time
        sliderFocusTime = findViewById(R.id.sliderFocusTime)
        tvFocusTime = findViewById(R.id.tvFocusTime)
        tvFocusTimeLabel = findViewById(R.id.tvFocusTimeLabel)
        
        // Break Time
        sliderBreakTime = findViewById(R.id.sliderBreakTime)
        tvBreakTime = findViewById(R.id.tvBreakTime)
        
        // Session Count
        sliderSessionCount = findViewById(R.id.sliderSessionCount)
        tvSessionCount = findViewById(R.id.tvSessionCount)
        
        // Sound Settings
        switchAmbientSound = findViewById(R.id.switchAmbientSound)
        switchTimerSound = findViewById(R.id.switchTimerSound)
        switchBreakReminder = findViewById(R.id.switchBreakReminder)
        
        // Focus Mode Settings
        switchStrictMode = findViewById(R.id.switchStrictMode)
        switchAutoStartBreaks = findViewById(R.id.switchAutoStartBreaks)
        switchScreenLock = findViewById(R.id.switchScreenLock)
        
        // Focus Type Cards
        cardDeepWork = findViewById(R.id.cardDeepWork)
        cardPomodoro = findViewById(R.id.cardPomodoro)
        cardCustom = findViewById(R.id.cardCustom)
    }
    
    private fun setupListeners() {
        btnStartSession.setOnClickListener {
            startFocusSession()
        }
        
        btnBack.setOnClickListener {
            finish()
        }
        
        // Focus Time Slider
        sliderFocusTime.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                // Round to nearest 5 for focus time
                focusTimeMinutes = ((value + 2.5) / 5).toInt() * 5
                updateDisplay()
            }
        }
        
        // Break Time Slider
        sliderBreakTime.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                // Round to nearest 1 for break time
                breakTimeMinutes = value.toInt()
                updateDisplay()
            }
        }
        
        // Session Count Slider
        sliderSessionCount.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                // Round to nearest 1 for session count
                sessionCount = value.toInt()
                updateDisplay()
            }
        }
        
        // Focus Type Selection
        cardDeepWork.setOnClickListener {
            selectFocusType("Deep Work")
        }
        
        cardPomodoro.setOnClickListener {
            selectFocusType("Pomodoro")
        }
        
        cardCustom.setOnClickListener {
            selectFocusType("Custom")
        }
    }
    
    private fun selectFocusType(type: String) {
        selectedFocusType = type
        
        // Reset card selections
        cardDeepWork.strokeWidth = 0
        cardPomodoro.strokeWidth = 0
        cardCustom.strokeWidth = 0
        
        // Apply selection
        when (type) {
            "Deep Work" -> {
                cardDeepWork.strokeWidth = 4
                focusTimeMinutes = 90
                breakTimeMinutes = 15
                sessionCount = 2
            }
            "Pomodoro" -> {
                cardPomodoro.strokeWidth = 4
                focusTimeMinutes = 25
                breakTimeMinutes = 5
                sessionCount = 4
            }
            "Custom" -> {
                cardCustom.strokeWidth = 4
                // Keep current values
            }
        }
        
        updateDisplay()
        updateSliders()
    }
    
    private fun updateDisplay() {
        tvFocusTime.text = "${focusTimeMinutes} min"
        tvBreakTime.text = "${breakTimeMinutes} min"
        tvSessionCount.text = "$sessionCount sessions"
        
        val totalTime = (focusTimeMinutes * sessionCount) + (breakTimeMinutes * (sessionCount - 1))
        tvFocusTimeLabel.text = "Focus Time (Total: ${totalTime} min)"
    }
    
    private fun updateSliders() {
        sliderFocusTime.value = focusTimeMinutes.toFloat()
        sliderBreakTime.value = breakTimeMinutes.toFloat()
        sliderSessionCount.value = sessionCount.toFloat()
    }
    
    private fun startFocusSession() {
        val intent = Intent(this, FocusSessionActivity::class.java).apply {
            putExtra("focusTimeMinutes", focusTimeMinutes)
            putExtra("breakTimeMinutes", breakTimeMinutes)
            putExtra("sessionCount", sessionCount)
            putExtra("focusType", selectedFocusType)
            putExtra("ambientSound", switchAmbientSound.isChecked)
            putExtra("timerSound", switchTimerSound.isChecked)
            putExtra("breakReminder", switchBreakReminder.isChecked)
            putExtra("strictMode", switchStrictMode.isChecked)
            putExtra("autoStartBreaks", switchAutoStartBreaks.isChecked)
            putExtra("screenLock", switchScreenLock.isChecked)
        }
        startActivity(intent)
        finish()
    }
}
