package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R
import com.example.focuslift.activities.LoginActivity
import com.example.focuslift.activities.SignupActivity

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        // Navigate to SignupActivity after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish() // Close splash activity so user can't go back
        }, 3000) // 3 seconds delay
    }
}
