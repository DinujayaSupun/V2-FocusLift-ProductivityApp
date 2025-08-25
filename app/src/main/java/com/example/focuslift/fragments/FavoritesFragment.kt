package com.example.focuslift.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.adapters.FavoritesAdapter
import com.example.focuslift.models.FavoriteItem
import com.example.focuslift.models.FavoriteType
import com.example.focuslift.utils.FavoritesManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class FavoritesFragment : Fragment() {
    
    private lateinit var rvFavorites: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var chipGroupFilters: ChipGroup
    private lateinit var emptyStateLayout: LinearLayout
    
    private lateinit var favoritesAdapter: FavoritesAdapter
    private var filteredFavorites = mutableListOf<FavoriteItem>()
    private var currentFilter: FavoriteType? = null // Default filter
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupRecyclerView()
        setupSearchFunctionality()
        setupFilterChips()
        loadSampleFavorites()
        filterFavorites()
        updateUI()
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh favorites when returning to the fragment
        filterFavorites()
    }
    
    private fun initializeViews(view: View) {
        rvFavorites = view.findViewById(R.id.rvFavorites)
        etSearch = view.findViewById(R.id.etSearch)
        chipGroupFilters = view.findViewById(R.id.chipGroupFilters)
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout)
    }
    
    private fun setupRecyclerView() {
        favoritesAdapter = FavoritesAdapter(
            favorites = filteredFavorites,
            onFavoriteClick = { favorite ->
                // Handle favorite item click
                // You can add navigation or show details here
            },
            onRemoveFavorite = { favorite ->
                removeFavorite(favorite)
            }
        )
        
        rvFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }
    }
    
    private fun setupSearchFunctionality() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterFavorites()
            }
        })
    }
    
    private fun setupFilterChips() {
        chipGroupFilters.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = group.findViewById<Chip>(checkedIds[0])
                when (chip.id) {
                    R.id.chipAll -> currentFilter = null
                    R.id.chipQuotes -> currentFilter = FavoriteType.QUOTE
                    R.id.chipTasks -> currentFilter = FavoriteType.TASK
                    R.id.chipTips -> currentFilter = FavoriteType.TIP
                    R.id.chipGoals -> currentFilter = FavoriteType.GOAL
                }
                filterFavorites()
            }
        }
    }
    
    private fun loadSampleFavorites() {
        // Add sample favorites for demonstration
        val sampleFavorites = listOf(
            FavoriteItem(
                id = "1",
                title = "Bruce Lee",
                content = "The successful warrior is the average person with laser-like focus.",
                type = FavoriteType.QUOTE
            ),
            FavoriteItem(
                id = "2",
                title = "Complete Mobile App Development assignment",
                content = "Finish the final project for the semester",
                type = FavoriteType.TASK
            ),
            FavoriteItem(
                id = "3",
                title = "Productivity Boost",
                content = "Cut distractions for 25 mins to maximize your focus and productivity.",
                type = FavoriteType.TIP
            ),
            FavoriteItem(
                id = "4",
                title = "Complete 2000 min of focus time this month",
                content = "Track daily progress and maintain consistency",
                type = FavoriteType.GOAL
            ),
            FavoriteItem(
                id = "5",
                title = "Peter Drucker",
                content = "The best way to predict the future is to create it.",
                type = FavoriteType.QUOTE
            ),
            FavoriteItem(
                id = "6",
                title = "Study for Data Structures exam",
                content = "Review algorithms and practice coding problems",
                type = FavoriteType.TASK
            )
        )
        
        sampleFavorites.forEach { FavoritesManager.addFavorite(it) }
    }
    
    private fun filterFavorites() {
        val searchQuery = etSearch.text.toString().lowercase()
        
        filteredFavorites.clear()
        
        val allFavorites = FavoritesManager.getFavorites()
        allFavorites.forEach { favorite ->
            val matchesSearch = searchQuery.isEmpty() || 
                favorite.title.lowercase().contains(searchQuery) ||
                favorite.content.lowercase().contains(searchQuery)
            
            val matchesFilter = currentFilter == null || favorite.type == currentFilter
            
            if (matchesSearch && matchesFilter) {
                filteredFavorites.add(favorite)
            }
        }
        
        favoritesAdapter.notifyDataSetChanged()
        updateUI()
    }
    
    private fun removeFavorite(favorite: FavoriteItem) {
        FavoritesManager.removeFavorite(favorite.id)
        filterFavorites()
    }
    
    private fun updateUI() {
        if (filteredFavorites.isEmpty()) {
            emptyStateLayout.visibility = View.VISIBLE
            rvFavorites.visibility = View.GONE
        } else {
            emptyStateLayout.visibility = View.GONE
            rvFavorites.visibility = View.VISIBLE
        }
    }
}
