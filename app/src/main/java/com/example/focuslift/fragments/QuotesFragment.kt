package com.example.focuslift.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.focuslift.R

class QuotesFragment : Fragment() {
    
    private lateinit var featuredQuoteCard: CardView
    private lateinit var tvFeaturedQuote: TextView
    private lateinit var tvFeaturedAuthor: TextView
    private lateinit var tvRefreshButton: TextView
    
    // Category buttons
    private lateinit var btnCategoryAll: TextView
    private lateinit var btnCategorySuccess: TextView
    private lateinit var btnCategoryMotivation: TextView
    private lateinit var btnCategoryFocus: TextView
    
    // Favorite buttons
    private lateinit var btnFavorite1: ImageView
    private lateinit var btnFavorite2: ImageView
    private lateinit var btnFavorite3: ImageView
    private lateinit var btnFavorite4: ImageView
    private lateinit var btnFavorite5: ImageView
    
    // Quote data
    private val quotes = listOf(
        Quote("The only way to do great work is to love what you do.", "Steve Jobs", "focus"),
        Quote("Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill", "success"),
        Quote("The future depends on what you do today.", "Mahatma Gandhi", "motivation"),
        Quote("Don't watch the clock; do what it does. Keep going.", "Sam Levenson", "focus"),
        Quote("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt", "motivation"),
        Quote("Focus is not about saying yes to the things you've got to focus on, but saying no to the hundred other good ideas.", "Steve Jobs", "focus"),
        Quote("The harder you work for something, the greater you'll feel when you achieve it.", "Unknown", "success"),
        Quote("Success doesn't just find you. You have to go out and get it.", "Unknown", "success"),
        Quote("Believe you can and you're halfway there.", "Theodore Roosevelt", "motivation"),
        Quote("It always seems impossible until it's done.", "Nelson Mandela", "motivation")
    )
    
    private var currentCategory = "all"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupClickListeners()
        updateFeaturedQuote()
    }
    
    private fun initializeViews(view: View) {
        featuredQuoteCard = view.findViewById(R.id.cardFeaturedQuote)
        tvFeaturedQuote = view.findViewById(R.id.tvFeaturedQuote)
        tvFeaturedAuthor = view.findViewById(R.id.tvFeaturedAuthor)
        tvRefreshButton = view.findViewById(R.id.tvRefreshButton)
        
        btnCategoryAll = view.findViewById(R.id.btnCategoryAll)
        btnCategorySuccess = view.findViewById(R.id.btnCategorySuccess)
        btnCategoryMotivation = view.findViewById(R.id.btnCategoryMotivation)
        btnCategoryFocus = view.findViewById(R.id.btnCategoryFocus)
        
        btnFavorite1 = view.findViewById(R.id.btnFavorite1)
        btnFavorite2 = view.findViewById(R.id.btnFavorite2)
        btnFavorite3 = view.findViewById(R.id.btnFavorite3)
        btnFavorite4 = view.findViewById(R.id.btnFavorite4)
        btnFavorite5 = view.findViewById(R.id.btnFavorite5)
    }
    
    private fun setupClickListeners() {
        // Refresh button
        tvRefreshButton.setOnClickListener {
            updateFeaturedQuote()
            Toast.makeText(context, "Quote refreshed!", Toast.LENGTH_SHORT).show()
        }
        
        // Category buttons
        btnCategoryAll.setOnClickListener { filterQuotes("all") }
        btnCategorySuccess.setOnClickListener { filterQuotes("success") }
        btnCategoryMotivation.setOnClickListener { filterQuotes("motivation") }
        btnCategoryFocus.setOnClickListener { filterQuotes("focus") }
        
        // Favorite buttons
        btnFavorite1.setOnClickListener { toggleFavorite(btnFavorite1) }
        btnFavorite2.setOnClickListener { toggleFavorite(btnFavorite2) }
        btnFavorite3.setOnClickListener { toggleFavorite(btnFavorite3) }
        btnFavorite4.setOnClickListener { toggleFavorite(btnFavorite4) }
        btnFavorite5.setOnClickListener { toggleFavorite(btnFavorite5) }
    }
    
    private fun updateFeaturedQuote() {
        val randomQuote = quotes.random()
        tvFeaturedQuote.text = randomQuote.text
        tvFeaturedAuthor.text = "- ${randomQuote.author}"
    }
    
    private fun filterQuotes(category: String) {
        currentCategory = category
        
        // Update button styles
        updateCategoryButtonStyles(category)
        
        // Show toast message
        val categoryName = when(category) {
            "all" -> "All Quotes"
            "success" -> "Success Quotes"
            "motivation" -> "Motivation Quotes"
            "focus" -> "Focus Quotes"
            else -> "All Quotes"
        }
        Toast.makeText(context, "Showing $categoryName", Toast.LENGTH_SHORT).show()
    }
    
    private fun updateCategoryButtonStyles(selectedCategory: String) {
        // Reset all buttons to default style
        btnCategoryAll.background = resources.getDrawable(R.drawable.bg_card, null)
        btnCategoryAll.setTextColor(resources.getColor(R.color.black, null))
        btnCategorySuccess.background = resources.getDrawable(R.drawable.bg_card, null)
        btnCategorySuccess.setTextColor(resources.getColor(R.color.black, null))
        btnCategoryMotivation.background = resources.getDrawable(R.drawable.bg_card, null)
        btnCategoryMotivation.setTextColor(resources.getColor(R.color.black, null))
        btnCategoryFocus.background = resources.getDrawable(R.drawable.bg_card, null)
        btnCategoryFocus.setTextColor(resources.getColor(R.color.black, null))
        
        // Set selected button style
        when(selectedCategory) {
            "all" -> {
                btnCategoryAll.background = resources.getDrawable(R.drawable.bg_button, null)
                btnCategoryAll.setTextColor(resources.getColor(R.color.white, null))
            }
            "success" -> {
                btnCategorySuccess.background = resources.getDrawable(R.drawable.bg_button, null)
                btnCategorySuccess.setTextColor(resources.getColor(R.color.white, null))
            }
            "motivation" -> {
                btnCategoryMotivation.background = resources.getDrawable(R.drawable.bg_button, null)
                btnCategoryMotivation.setTextColor(resources.getColor(R.color.white, null))
            }
            "focus" -> {
                btnCategoryFocus.background = resources.getDrawable(R.drawable.bg_button, null)
                btnCategoryFocus.setTextColor(resources.getColor(R.color.white, null))
            }
        }
    }
    
    private fun toggleFavorite(button: ImageView) {
        val isFavorited = button.tag as? Boolean ?: false
        
        if (isFavorited) {
            button.setImageResource(R.drawable.ic_favorite)
            button.tag = false
            Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
        } else {
            button.setImageResource(R.drawable.ic_favorite)
            button.setColorFilter(resources.getColor(R.color.purple_500, null))
            button.tag = true
            Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show()
        }
    }
    
    data class Quote(
        val text: String,
        val author: String,
        val category: String
    )
}
