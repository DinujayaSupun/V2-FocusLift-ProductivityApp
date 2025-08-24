package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.editEmail)
        val password = findViewById<EditText>(R.id.editPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvForgot = findViewById<TextView>(R.id.tvForgotPassword)

        // Optional: style forgot as link (UI only)
        tvForgot.text = Html.fromHtml("<u>${getString(R.string.login_forgot)}</u>")
        tvForgot.movementMethod = LinkMovementMethod.getInstance()

        btnSignIn.setOnClickListener {
            // No auth – just navigate for the UI lab
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
