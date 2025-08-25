package com.example.focuslift.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.focuslift.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish()
        }

        // Settings item click listeners
        setupSettingsItem(R.id.settingsNotifications, "Notifications") { 
            Toast.makeText(this, "Notifications settings coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        setupSettingsItem(R.id.settingsTheme, "Theme") { 
            Toast.makeText(this, "Theme settings coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        setupSettingsItem(R.id.settingsSound, "Sound & Vibration") { 
            Toast.makeText(this, "Sound settings coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        setupSettingsItem(R.id.settingsPrivacy, "Privacy & Security") { 
            Toast.makeText(this, "Privacy settings coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        setupSettingsItem(R.id.settingsAbout, "About FocusLift") { 
            Toast.makeText(this, "About section coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        setupSettingsItem(R.id.settingsHelp, "Help & Support") { 
            Toast.makeText(this, "Help section coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSettingsItem(itemId: Int, title: String, onClick: () -> Unit) {
        val item = findViewById<CardView>(itemId)
        item?.setOnClickListener {
            onClick()
        }
    }
}
