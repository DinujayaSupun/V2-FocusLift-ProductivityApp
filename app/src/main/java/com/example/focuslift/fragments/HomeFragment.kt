package com.example.focuslift.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.focuslift.R
import com.example.focuslift.activities.FocusSessionSetupActivity
import com.example.focuslift.activities.AnalyticsActivity
import com.example.focuslift.activities.GoalsActivity

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
        setupNavigationButtons()
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
        // Analytics button
        view?.findViewById<View>(R.id.btnAnalytics)?.setOnClickListener {
            val intent = Intent(requireContext(), AnalyticsActivity::class.java)
            startActivity(intent)
        }
        
        // Goals button
        view?.findViewById<View>(R.id.btnGoals)?.setOnClickListener {
            val intent = Intent(requireContext(), GoalsActivity::class.java)
            startActivity(intent)
        }
    }
}
