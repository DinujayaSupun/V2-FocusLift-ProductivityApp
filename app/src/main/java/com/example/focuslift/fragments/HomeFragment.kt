package com.example.focuslift.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import com.example.focuslift.R
import com.example.focuslift.activities.FocusSessionSetupActivity
import com.example.focuslift.activities.AnalyticsActivity
import com.example.focuslift.activities.GoalsActivity
import com.example.focuslift.activities.SettingsActivity
import com.example.focuslift.activities.ProgressActivity
import android.widget.LinearLayout

class HomeFragment : Fragment() {
    
    private var selectedEmoji: TextView? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupEmojiSelection()
        setupTipButton()
        setupFocusSessionButton()
        
        // Add a small delay to ensure the view is fully inflated
        view.post {
            setupNavigationButtons()
        }
    }
    
    private fun setupEmojiSelection() {
        val emojiHappy = view?.findViewById<TextView>(R.id.emoji_happy)
        val emojiNeutral = view?.findViewById<TextView>(R.id.emoji_excited)
        val emojiSad = view?.findViewById<TextView>(R.id.emoji_neutral)
        val emojiCrown = view?.findViewById<TextView>(R.id.emoji_sad)
        val emojiSleep = view?.findViewById<TextView>(R.id.emoji_sleep)
        
        // Set click listeners for emoji selection
        emojiHappy?.setOnClickListener { selectEmoji(it as TextView, "happy") }
        emojiNeutral?.setOnClickListener { selectEmoji(it as TextView, "excited") }
        emojiSad?.setOnClickListener { selectEmoji(it as TextView, "neutral") }
        emojiCrown?.setOnClickListener { selectEmoji(it as TextView, "sad") }
        emojiSleep?.setOnClickListener { selectEmoji(it as TextView, "Sleepy") }
        
        // Set default selection
        emojiHappy?.let { selectEmoji(it, "Happy") }
    }
    
    private fun selectEmoji(emojiView: TextView, mood: String) {
        // Reset previous selection
        selectedEmoji?.background = null
        
        // Set new selection
        selectedEmoji = emojiView
        emojiView.setBackgroundResource(R.drawable.bg_circle_timer)
        
        // Show feedback
        Toast.makeText(context, "Mood selected: $mood", Toast.LENGTH_SHORT).show()
    }
    
    private fun setupTipButton() {
        view?.findViewById<View>(R.id.btnSeeMoreTips)?.setOnClickListener {
            Toast.makeText(context, "More tips coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun setupFocusSessionButton() {
        view?.findViewById<View>(R.id.btnStartFocus)?.setOnClickListener {
            val intent = Intent(requireContext(), FocusSessionSetupActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun setupNavigationButtons() {
        // Debug: Check if view is available
        if (view == null) {
            Toast.makeText(context, "View is null in setupNavigationButtons!", Toast.LENGTH_LONG).show()
            return
        }
        
        Toast.makeText(context, "Setting up navigation buttons...", Toast.LENGTH_SHORT).show()
        
        // Test: Try to find the goals button directly first
        val testButton = view?.findViewById<View>(R.id.btnGoals)
        if (testButton != null) {
            Toast.makeText(context, "Goals button found directly!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Goals button NOT found directly!", Toast.LENGTH_LONG).show()
        }
        
        // Analytics button
        val analyticsButton = view?.findViewById<View>(R.id.btnAnalytics)
        if (analyticsButton != null) {
            analyticsButton.setOnClickListener {
                Toast.makeText(context, "Analytics button clicked!", Toast.LENGTH_SHORT).show()
                try {
                    val intent = Intent(requireContext(), AnalyticsActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        } else {
            Toast.makeText(context, "Analytics button NOT found!", Toast.LENGTH_LONG).show()
        }

        // Settings button
        val settingsButton = view?.findViewById<View>(R.id.btnSettings)
        if (settingsButton != null) {
            settingsButton.setOnClickListener {
                Toast.makeText(context, "Settings button clicked!", Toast.LENGTH_SHORT).show()
                try {
                    val intent = Intent(requireContext(), SettingsActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        } else {
            Toast.makeText(context, "Settings button NOT found!", Toast.LENGTH_LONG).show()
        }

        // Progress button
        val progressButton = view?.findViewById<View>(R.id.btnProgress)
        if (progressButton != null) {
            progressButton.setOnClickListener {
                Toast.makeText(context, "Progress button clicked!", Toast.LENGTH_SHORT).show()
                try {
                    val intent = Intent(requireContext(), ProgressActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        } else {
            Toast.makeText(context, "Progress button NOT found!", Toast.LENGTH_LONG).show()
        }

        // Goals button - Simplified approach
        val goalsButton = view?.findViewById<View>(R.id.btnGoals)
        if (goalsButton != null) {
            Toast.makeText(context, "Goals button found! Setting up click listener...", Toast.LENGTH_SHORT).show()
            
            // Set click listener on the main button
            goalsButton.setOnClickListener {
                Toast.makeText(context, "Goals button clicked! Navigating...", Toast.LENGTH_SHORT).show()
                
                try {
                    val intent = Intent(requireContext(), GoalsActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(context, "Navigation to Goals successful!", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Navigation failed: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
            
        } else {
            Toast.makeText(context, "Goals button NOT found!", Toast.LENGTH_LONG).show()
        }
    }
}
