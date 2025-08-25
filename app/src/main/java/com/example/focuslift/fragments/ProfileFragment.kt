package com.example.focuslift.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.focuslift.R

class ProfileFragment : Fragment() {
    
    private lateinit var switchNotifications: Switch
    private lateinit var switchDarkTheme: Switch
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize views
        switchNotifications = view.findViewById(R.id.switchNotifications)
        switchDarkTheme = view.findViewById(R.id.switchDarkTheme)
        
        // Set up click listeners
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        // Notifications switch
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "Notifications enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Notifications disabled", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Dark theme switch
        switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "Dark theme enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Dark theme disabled", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Reset Progress button
        view?.findViewById<View>(R.id.btnResetProgress)?.setOnClickListener {
            Toast.makeText(context, "Reset Progress clicked", Toast.LENGTH_SHORT).show()
        }
        
        // View Info button
        view?.findViewById<View>(R.id.btnViewInfo)?.setOnClickListener {
            Toast.makeText(context, "View Info clicked", Toast.LENGTH_SHORT).show()
        }
        
        // Logout button
        view?.findViewById<View>(R.id.btnLogout)?.setOnClickListener {
            Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show()
        }
        
        // Edit Profile button
        view?.findViewById<View>(R.id.btnEditProfile)?.setOnClickListener {
            Toast.makeText(context, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
