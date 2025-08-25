package com.example.focuslift.activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.focuslift.R
import com.example.focuslift.models.Goal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class GoalsActivity : AppCompatActivity() {
    
    private lateinit var goalsContainer: LinearLayout
    private lateinit var emptyStateContainer: LinearLayout
    private lateinit var fabAddGoal: FloatingActionButton
    private lateinit var btnBack: ImageButton
    
    private val goals = mutableListOf<Goal>()
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)
        
        initializeViews()
        setupClickListeners()
        loadSampleGoals()
        updateUI()
    }
    
    private fun initializeViews() {
        goalsContainer = findViewById(R.id.goalsContainer)
        emptyStateContainer = findViewById(R.id.emptyStateContainer)
        fabAddGoal = findViewById(R.id.fabAddGoal)
        btnBack = findViewById(R.id.btnBack)
    }
    
    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish()
        }
        
        fabAddGoal.setOnClickListener {
            showAddGoalDialog()
        }
    }
    
    private fun loadSampleGoals() {
        // Add sample goals if none exist
        if (goals.isEmpty()) {
            goals.add(Goal(
                title = getString(R.string.goal_sample_1),
                description = "Complete 2000 minutes of focus time this month",
                target = 2000,
                currentProgress = 1300,
                deadline = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 15) }.time
            ))
            
            goals.add(Goal(
                title = getString(R.string.goal_sample_2),
                description = "Finish the mobile app project",
                target = 100,
                currentProgress = 30,
                deadline = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 22) }.time
            ))
            
            goals.add(Goal(
                title = getString(R.string.goal_sample_3),
                description = "Achieve 30 consecutive days of focus",
                target = 30,
                currentProgress = 24,
                deadline = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 6) }.time
            ))
        }
    }
    
    private fun updateUI() {
        if (goals.isEmpty()) {
            goalsContainer.visibility = View.GONE
            emptyStateContainer.visibility = View.VISIBLE
        } else {
            goalsContainer.visibility = View.VISIBLE
            emptyStateContainer.visibility = View.GONE
            displayGoals()
        }
    }
    
    private fun displayGoals() {
        // Remove existing goal views (except the first 3 sample ones)
        val childCount = goalsContainer.childCount
        for (i in 3 until childCount) {
            goalsContainer.removeViewAt(3)
        }
        
        // Add dynamic goals
        for (i in 3 until goals.size) {
            val goal = goals[i]
            val goalView = createGoalView(goal)
            goalsContainer.addView(goalView)
        }
    }
    
    private fun createGoalView(goal: Goal): CardView {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 64)
            }
            radius = resources.getDimensionPixelSize(R.dimen.card_corner_radius).toFloat()
            elevation = resources.getDimensionPixelSize(R.dimen.card_elevation).toFloat()
            setCardBackgroundColor(resources.getColor(R.color.white, theme))
        }
        
        val progress = if (goal.target > 0) (goal.currentProgress * 100 / goal.target) else 0
        val daysLeft = goal.deadline?.let { deadline ->
            val diff = deadline.time - Date().time
            val days = diff / (24 * 60 * 60 * 1000)
            if (days > 0) "$days days left" else "Overdue"
        } ?: "No deadline"
        
        val progressColor = when {
            progress >= 80 -> R.color.stat_box_green
            progress >= 50 -> R.color.primary_60
            else -> R.color.stat_box_purple
        }
        
        val statusText = when {
            progress >= 100 -> "Completed"
            progress > 0 -> "In Progress"
            else -> "Not Started"
        }
        
        cardView.addView(LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(80, 80, 80, 80)
            
            // Title and Progress Row
            addView(LinearLayout(this@GoalsActivity).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                setPadding(0, 0, 0, 48)
                
                addView(TextView(this@GoalsActivity).apply {
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    text = goal.title
                    textSize = 64f
                    setTextColor(resources.getColor(R.color.text_primary, theme))
                    setTypeface(null, android.graphics.Typeface.BOLD)
                })
                
                addView(TextView(this@GoalsActivity).apply {
                    layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    text = "$progress%"
                    textSize = 72f
                    setTextColor(resources.getColor(progressColor, theme))
                    setTypeface(null, android.graphics.Typeface.BOLD)
                })
            })
            
            // Progress Bar
            addView(ProgressBar(this@GoalsActivity, null, android.R.attr.progressBarStyleHorizontal).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0, 0, 0, 48)
                }
                max = 100
                setProgress(progress)
                progressTintList = android.content.res.ColorStateList.valueOf(resources.getColor(progressColor, theme))
                progressBackgroundTintList = android.content.res.ColorStateList.valueOf(resources.getColor(R.color.light_gray, theme))
            })
            
            // Days Left and Status Row
            addView(LinearLayout(this@GoalsActivity).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                
                addView(TextView(this@GoalsActivity).apply {
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    text = daysLeft
                    textSize = 48f
                    setTextColor(resources.getColor(R.color.text_secondary, theme))
                })
                
                addView(LinearLayout(this@GoalsActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = android.view.Gravity.CENTER_VERTICAL
                    
                    addView(View(this@GoalsActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(32, 32).apply {
                            setMargins(0, 0, 32, 0)
                        }
                        background = resources.getDrawable(R.drawable.bg_circle_timer, theme)
                    })
                    
                    addView(TextView(this@GoalsActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        text = statusText
                        textSize = 48f
                        setTextColor(resources.getColor(progressColor, theme))
                    })
                })
            })
        })
        
        return cardView
    }
    
    private fun showAddGoalDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_goal)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        
        val etTitle = dialog.findViewById<EditText>(R.id.etGoalTitle)
        val etDescription = dialog.findViewById<EditText>(R.id.etGoalDescription)
        val etTarget = dialog.findViewById<EditText>(R.id.etGoalTarget)
        val etDeadline = dialog.findViewById<EditText>(R.id.etGoalDeadline)
        val btnDatePicker = dialog.findViewById<ImageButton>(R.id.btnDatePicker)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val btnAdd = dialog.findViewById<Button>(R.id.btnAddGoal)
        
        var selectedDate: Date? = null
        
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.time
                    etDeadline.setText(dateFormat.format(selectedDate!!))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        
        btnAdd.setOnClickListener {
            val title = etTitle.text.toString().trim()
            if (title.isEmpty()) {
                etTitle.error = "Goal title is required"
                return@setOnClickListener
            }
            
            val description = etDescription.text.toString().trim()
            val target = etTarget.text.toString().toIntOrNull() ?: 100
            
            val newGoal = Goal(
                title = title,
                description = description,
                target = target,
                deadline = selectedDate
            )
            
            goals.add(newGoal)
            updateUI()
            dialog.dismiss()
            
            Toast.makeText(this, "Goal added successfully!", Toast.LENGTH_SHORT).show()
        }
        
        dialog.show()
    }
}
