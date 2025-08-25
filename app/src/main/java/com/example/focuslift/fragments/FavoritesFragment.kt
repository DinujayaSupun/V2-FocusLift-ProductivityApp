package com.example.focuslift.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.adapters.FavoriteQuotesAdapter
import com.example.focuslift.adapters.FavoriteTasksAdapter
import com.example.focuslift.adapters.FavoriteGoalsAdapter
import com.example.focuslift.models.FavoriteQuote
import com.example.focuslift.models.FavoriteTask
import com.example.focuslift.models.FavoriteGoal
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout

class FavoritesFragment : Fragment() {
    
    private lateinit var tabLayout: TabLayout
    private lateinit var quotesContainer: View
    private lateinit var tasksContainer: View
    private lateinit var goalsContainer: View
    
    private lateinit var quotesRecyclerView: RecyclerView
    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var goalsRecyclerView: RecyclerView
    
    private lateinit var emptyQuotesView: View
    private lateinit var emptyTasksView: View
    private lateinit var emptyGoalsView: View
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupTabs()
        setupRecyclerViews()
        loadFavorites()
    }
    
    private fun initializeViews(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout)
        quotesContainer = view.findViewById(R.id.quotesContainer)
        tasksContainer = view.findViewById(R.id.tasksContainer)
        goalsContainer = view.findViewById(R.id.goalsContainer)
        
        quotesRecyclerView = view.findViewById(R.id.quotesRecyclerView)
        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView)
        goalsRecyclerView = view.findViewById(R.id.goalsRecyclerView)
        
        emptyQuotesView = view.findViewById(R.id.emptyQuotesView)
        emptyTasksView = view.findViewById(R.id.emptyTasksView)
        emptyGoalsView = view.findViewById(R.id.emptyGoalsView)
    }
    
    private fun setupTabs() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showQuotesTab()
                    1 -> showTasksTab()
                    2 -> showGoalsTab()
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    
    private fun setupRecyclerViews() {
        // Setup Quotes RecyclerView
        quotesRecyclerView.layoutManager = LinearLayoutManager(context)
        quotesRecyclerView.adapter = FavoriteQuotesAdapter(getFavoriteQuotes()) { quote ->
            Toast.makeText(context, "Quote: ${quote.text}", Toast.LENGTH_SHORT).show()
        }
        
        // Setup Tasks RecyclerView
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = FavoriteTasksAdapter(getFavoriteTasks()) { task ->
            Toast.makeText(context, "Task: ${task.title}", Toast.LENGTH_SHORT).show()
        }
        
        // Setup Goals RecyclerView
        goalsRecyclerView.layoutManager = LinearLayoutManager(context)
        goalsRecyclerView.adapter = FavoriteGoalsAdapter(getFavoriteGoals()) { goal ->
            Toast.makeText(context, "Goal: ${goal.title}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showQuotesTab() {
        quotesContainer.visibility = View.VISIBLE
        tasksContainer.visibility = View.GONE
        goalsContainer.visibility = View.GONE
    }
    
    private fun showTasksTab() {
        quotesContainer.visibility = View.GONE
        tasksContainer.visibility = View.VISIBLE
        goalsContainer.visibility = View.GONE
    }
    
    private fun showGoalsTab() {
        quotesContainer.visibility = View.GONE
        tasksContainer.visibility = View.GONE
        goalsContainer.visibility = View.VISIBLE
    }
    
    private fun loadFavorites() {
        // Show initial tab
        showQuotesTab()
        
        // Update empty states
        updateEmptyStates()
    }
    
    private fun updateEmptyStates() {
        val hasQuotes = getFavoriteQuotes().isNotEmpty()
        val hasTasks = getFavoriteTasks().isNotEmpty()
        val hasGoals = getFavoriteGoals().isNotEmpty()
        
        emptyQuotesView.visibility = if (hasQuotes) View.GONE else View.VISIBLE
        emptyTasksView.visibility = if (hasTasks) View.GONE else View.VISIBLE
        emptyGoalsView.visibility = if (hasGoals) View.GONE else View.VISIBLE
        
        quotesRecyclerView.visibility = if (hasQuotes) View.VISIBLE else View.GONE
        tasksRecyclerView.visibility = if (hasTasks) View.VISIBLE else View.GONE
        goalsRecyclerView.visibility = if (hasGoals) View.VISIBLE else View.GONE
    }
    
    private fun getFavoriteQuotes(): List<FavoriteQuote> {
        return listOf(
            FavoriteQuote(
                "The only way to do great work is to love what you do.",
                "Steve Jobs",
                "Focus & Productivity"
            ),
            FavoriteQuote(
                "Success is not final, failure is not fatal: it is the courage to continue that counts.",
                "Winston Churchill",
                "Motivation"
            ),
            FavoriteQuote(
                "The future depends on what you do today.",
                "Mahatma Gandhi",
                "Daily Goals"
            ),
            FavoriteQuote(
                "Don't watch the clock; do what it does. Keep going.",
                "Sam Levenson",
                "Persistence"
            ),
            FavoriteQuote(
                "The only limit to our realization of tomorrow is our doubts of today.",
                "Franklin D. Roosevelt",
                "Belief"
            )
        )
    }
    
    private fun getFavoriteTasks(): List<FavoriteTask> {
        return listOf(
            FavoriteTask("Complete Mobile App Development assignment", "High Priority", "Due: Tomorrow"),
            FavoriteTask("Study for Data Structures exam", "Medium Priority", "Due: Next Week"),
            FavoriteTask("Prepare presentation for Software Engineering", "High Priority", "Due: Friday"),
            FavoriteTask("Review code for team project", "Low Priority", "Due: Weekend"),
            FavoriteTask("Update portfolio with new projects", "Medium Priority", "Due: End of Month")
        )
    }
    
    private fun getFavoriteGoals(): List<FavoriteGoal> {
        return listOf(
            FavoriteGoal("Complete 2000 min of focus time this month", "75%", "1500/2000 min"),
            FavoriteGoal("Finish mobile app project", "60%", "3/5 modules"),
            FavoriteGoal("Achieve 30 consecutive days of focus", "80%", "24/30 days"),
            FavoriteGoal("Learn 3 new programming languages", "33%", "1/3 languages"),
            FavoriteGoal("Read 12 productivity books this year", "50%", "6/12 books")
        )
    }
}
