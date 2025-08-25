package com.example.focuslift.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.focuslift.R
import com.example.focuslift.activities.MainActivity
import com.example.focuslift.activities.SignupActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun navigateToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()  // ✅ closes login screen so back button won't return here
    }
    
    fun navigateToSignup(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()  // Close login screen
    }
}
