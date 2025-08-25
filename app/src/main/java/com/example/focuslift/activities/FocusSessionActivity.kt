package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.example.focuslift.activities.SessionInterruptedActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator

class FocusSessionActivity : AppCompatActivity() {
    
    private lateinit var tvTimer: TextView
    private lateinit var tvSessionInfo: TextView
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var btnStartPause: MaterialButton
    private lateinit var btnGiveUp: MaterialButton
    private lateinit var btnSettings: MaterialButton
    
    // Session Settings
    private var focusTimeMinutes = 25
    private var breakTimeMinutes = 5
    private var sessionCount = 4
    private var focusType = "Pomodoro"
    private var ambientSound = true
    private var timerSound = true
    private var breakReminder = true
    private var strictMode = false
    private var autoStartBreaks = true
    private var screenLock = false
    
    // Session State
    private var currentSession = 1
    private var isFocusTime = true
    private var isRunning = false
    private var timeLeftInSeconds = 0
    private var totalTimeInSeconds = 0
    private var timer: CountDownTimer? = null
    
    // Statistics
    private var totalFocusTimeCompleted = 0
    private var sessionsCompleted = 0
    private var breaksTaken = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_session)
        
        loadSessionSettings()
        initializeViews()
        setupListeners()
        startFocusSession()
    }
    
    private fun loadSessionSettings() {
        intent.extras?.let { extras ->
            focusTimeMinutes = extras.getInt("focusTimeMinutes", 25)
            breakTimeMinutes = extras.getInt("breakTimeMinutes", 5)
            sessionCount = extras.getInt("sessionCount", 4)
            focusType = extras.getString("focusType", "Pomodoro") ?: "Pomodoro"
            ambientSound = extras.getBoolean("ambientSound", true)
            timerSound = extras.getBoolean("timerSound", true)
            breakReminder = extras.getBoolean("breakReminder", true)
            strictMode = extras.getBoolean("strictMode", false)
            autoStartBreaks = extras.getBoolean("autoStartBreaks", true)
            screenLock = extras.getBoolean("screenLock", false)
        }
        
        timeLeftInSeconds = focusTimeMinutes * 60
        totalTimeInSeconds = timeLeftInSeconds
    }
    
    private fun initializeViews() {
        tvTimer = findViewById(R.id.tvTimer)
        tvSessionInfo = findViewById(R.id.tvSessionInfo)
        progressIndicator = findViewById(R.id.progressIndicator)
        btnStartPause = findViewById(R.id.btnStartPause)
        btnGiveUp = findViewById(R.id.btnGiveUp)
        btnSettings = findViewById(R.id.btnSettings)
        
        updateSessionInfo()
        updateTimerDisplay()
        updateProgressIndicator()
    }
    
    private fun setupListeners() {
        btnStartPause.setOnClickListener {
            if (isRunning) {
                pauseSession()
            } else {
                resumeSession()
            }
        }
        
        btnGiveUp.setOnClickListener {
            showGiveUpDialog()
        }
        
        btnSettings.setOnClickListener {
            // Could open a quick settings panel
            showQuickSettingsDialog()
        }
    }
    
    private fun startFocusSession() {
        isRunning = true
        btnStartPause.text = "PAUSE"
        startTimer()
    }
    
    private fun pauseSession() {
        isRunning = false
        btnStartPause.text = "RESUME"
        timer?.cancel()
    }
    
    private fun resumeSession() {
        isRunning = true
        btnStartPause.text = "PAUSE"
        startTimer()
    }
    
    private fun startTimer() {
        timer?.cancel()
        
        timer = object : CountDownTimer(timeLeftInSeconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInSeconds = (millisUntilFinished / 1000).toInt()
                updateTimerDisplay()
                updateProgressIndicator()
            }
            
            override fun onFinish() {
                onSessionComplete()
            }
        }.start()
    }
    
    private fun onSessionComplete() {
        if (isFocusTime) {
            // Focus session completed
            totalFocusTimeCompleted += focusTimeMinutes
            sessionsCompleted++
            
            if (currentSession < sessionCount) {
                // Start break
                startBreak()
            } else {
                // All sessions completed
                onAllSessionsComplete()
            }
        } else {
            // Break completed
            breaksTaken++
            currentSession++
            
            if (currentSession <= sessionCount) {
                // Start next focus session
                startNextFocusSession()
            }
        }
    }
    
    private fun startBreak() {
        isFocusTime = false
        timeLeftInSeconds = breakTimeMinutes * 60
        totalTimeInSeconds = timeLeftInSeconds
        updateSessionInfo()
        updateTimerDisplay()
        updateProgressIndicator()
        
        if (autoStartBreaks) {
            startTimer()
        } else {
            isRunning = false
            btnStartPause.text = "START BREAK"
        }
    }
    
    private fun startNextFocusSession() {
        isFocusTime = true
        timeLeftInSeconds = focusTimeMinutes * 60
        totalTimeInSeconds = timeLeftInSeconds
        updateSessionInfo()
        updateTimerDisplay()
        updateProgressIndicator()
        
        startTimer()
    }
    
    private fun onAllSessionsComplete() {
        // Navigate to completion screen
        val intent = Intent(this, FocusSessionCompletedActivity::class.java).apply {
            putExtra("totalFocusTime", totalFocusTimeCompleted)
            putExtra("sessionsCompleted", sessionsCompleted)
            putExtra("breaksTaken", breaksTaken)
            putExtra("focusType", focusType)
            putExtra("totalTime", (focusTimeMinutes * sessionCount) + (breakTimeMinutes * (sessionCount - 1)))
        }
        startActivity(intent)
        finish()
    }
    
    private fun updateTimerDisplay() {
        val minutes = timeLeftInSeconds / 60
        val seconds = timeLeftInSeconds % 60
        tvTimer.text = String.format("%02d:%02d", minutes, seconds)
    }
    
    private fun updateProgressIndicator() {
        val progress = ((totalTimeInSeconds - timeLeftInSeconds) * 100 / totalTimeInSeconds).toInt()
        progressIndicator.progress = progress
    }
    
    private fun updateSessionInfo() {
        val sessionText = if (isFocusTime) "Focus Session" else "Break Time"
        val timeText = if (isFocusTime) "${focusTimeMinutes} min" else "${breakTimeMinutes} min"
        tvSessionInfo.text = "$sessionText $currentSession/$sessionCount • $timeText"
    }
    
    private fun showGiveUpDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_give_up_confirmation, null)
        
        val dialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        
        // Set up button listeners
        dialogView.findViewById<MaterialButton>(R.id.btnContinueSession).setOnClickListener {
            dialog.dismiss()
        }
        
        dialogView.findViewById<MaterialButton>(R.id.btnGiveUp).setOnClickListener {
            dialog.dismiss()
            giveUpSession()
        }
        
        dialog.show()
    }
    
    private fun giveUpSession() {
        timer?.cancel()
        
        // Calculate completed time
        val completedTime = totalTimeInSeconds - timeLeftInSeconds
        val focusTimeCompleted = if (isFocusTime) {
            (completedTime / 60) + (if (completedTime % 60 > 0) 1 else 0)
        } else {
            totalFocusTimeCompleted
        }
        
        // Navigate to session interrupted screen
        val intent = Intent(this, SessionInterruptedActivity::class.java).apply {
            putExtra("focusTimeCompleted", focusTimeCompleted)
            putExtra("sessionsCompleted", sessionsCompleted)
            putExtra("breaksTaken", breaksTaken)
            putExtra("focusType", focusType)
            putExtra("totalTime", (focusTimeMinutes * sessionCount) + (breakTimeMinutes * (sessionCount - 1)))
        }
        startActivity(intent)
        finish()
    }
    
    private fun showQuickSettingsDialog() {
        // Show quick settings panel
        // This could include toggling sounds, strict mode, etc.
    }
    
    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
