package com.example.focuslift.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.focuslift.R

class ProgressActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

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
    }
}
