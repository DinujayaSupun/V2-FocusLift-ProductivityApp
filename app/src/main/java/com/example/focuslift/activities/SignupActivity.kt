package com.example.focuslift.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.focuslift.R

class SignupActivity : AppCompatActivity() {
    
    private lateinit var etUserName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var etReferralCode: EditText
    private lateinit var cbTerms: CheckBox
    private lateinit var ivPasswordToggle: ImageView
    
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        
        initializeViews()
        setupPasswordToggle()
    }
    
    private fun initializeViews() {
        etUserName = findViewById(R.id.etUserName)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        etReferralCode = findViewById(R.id.etReferralCode)
        cbTerms = findViewById(R.id.cbTerms)
        ivPasswordToggle = findViewById(R.id.ivPasswordToggle)
    }
    
    private fun setupPasswordToggle() {
        ivPasswordToggle.setOnClickListener {
            if (isPasswordVisible) {
                // Hide password
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ivPasswordToggle.setImageResource(R.drawable.ic_eye_off)
                isPasswordVisible = false
            } else {
                // Show password
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivPasswordToggle.setImageResource(R.drawable.ic_eye)
                isPasswordVisible = true
            }
            // Move cursor to end
            etPassword.setSelection(etPassword.text.length)
        }
    }
    
    fun proceedToLogin(view: View) {
        //if (validateInputs()) {
            // Here you would typically save the user data to your backend/database
            // For now, we'll just show a success message and navigate to login
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_LONG).show()
            
            // Navigate to login screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close signup activity so user can't go back
        //}
    }
    
    fun navigateToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close signup activity
    }
    
    private fun validateInputs(): Boolean {
        val userName = etUserName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phoneNumber = etPhoneNumber.text.toString().trim()
        val password = etPassword.text.toString().trim()
        
        // Check if fields are empty
        if (userName.isEmpty()) {
            etUserName.error = "User name is required"
            etUserName.requestFocus()
            return false
        }
        
        if (email.isEmpty()) {
            etEmail.error = "Email is required"
            etEmail.requestFocus()
            return false
        }
        
        // Basic email validation
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Please enter a valid email address"
            etEmail.requestFocus()
            return false
        }
        
        if (phoneNumber.isEmpty()) {
            etPhoneNumber.error = "Phone number is required"
            etPhoneNumber.requestFocus()
            return false
        }
        
        // Basic phone validation (at least 10 digits)
        if (phoneNumber.length < 10) {
            etPhoneNumber.error = "Please enter a valid phone number"
            etPhoneNumber.requestFocus()
            return false
        }
        
        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            etPassword.requestFocus()
            return false
        }
        
        // Password strength validation (at least 6 characters)
        if (password.length < 6) {
            etPassword.error = "Password must be at least 6 characters"
            etPassword.requestFocus()
            return false
        }
        
        // Check terms and conditions
        if (!cbTerms.isChecked) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show()
            return false
        }
        
        return true
    }
}
