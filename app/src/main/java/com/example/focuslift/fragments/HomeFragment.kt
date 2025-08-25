package com.example.focuslift.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.focuslift.R

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
    }
    
    private fun setupEmojiSelection() {
        val emojiHappy = view?.findViewById<TextView>(R.id.emoji_happy)
        val emojiNeutral = view?.findViewById<TextView>(R.id.emoji_neutral)
        val emojiSad = view?.findViewById<TextView>(R.id.emoji_sad)
        val emojiCrown = view?.findViewById<TextView>(R.id.emoji_crown)
        val emojiSleep = view?.findViewById<TextView>(R.id.emoji_sleep)
        
        // Set click listeners for emoji selection
        emojiHappy?.setOnClickListener { selectEmoji(it as TextView, "Happy") }
        emojiNeutral?.setOnClickListener { selectEmoji(it as TextView, "Neutral") }
        emojiSad?.setOnClickListener { selectEmoji(it as TextView, "Sad") }
        emojiCrown?.setOnClickListener { selectEmoji(it as TextView, "Crown") }
        emojiSleep?.setOnClickListener { selectEmoji(it as TextView, "Sleep") }
        
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
}
