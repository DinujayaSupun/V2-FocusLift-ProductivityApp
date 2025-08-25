package com.example.focuslift.activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.focuslift.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class TasksActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var tasksContainer: LinearLayout
    private lateinit var emptyStateContainer: LinearLayout

    private val tasks = mutableListOf<Task>()
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        initializeViews()
        setupClickListeners()
        loadSampleTasks()
        updateUI()
    }

    private fun initializeViews() {
        btnBack = findViewById(R.id.btnBack)
        fabAddTask = findViewById(R.id.fabAddTask)
        tasksContainer = findViewById(R.id.tasksContainer)
        emptyStateContainer = findViewById(R.id.emptyStateContainer)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish()
        }

        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun loadSampleTasks() {
        if (tasks.isEmpty()) {
            tasks.add(Task(
                title = "Complete mobile app project",
                description = "Finish the FocusLift productivity app",
                dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 7) }.time,
                priority = TaskPriority.HIGH,
                isCompleted = false
            ))

            tasks.add(Task(
                title = "Study for Data Science exam",
                description = "Review machine learning concepts",
                dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) }.time,
                priority = TaskPriority.MEDIUM,
                isCompleted = false
            ))

            tasks.add(Task(
                title = "Exercise routine",
                description = "30 minutes of cardio and strength training",
                dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) }.time,
                priority = TaskPriority.LOW,
                isCompleted = true
            ))
        }
    }

    private fun updateUI() {
        if (tasks.isEmpty()) {
            tasksContainer.visibility = View.GONE
            emptyStateContainer.visibility = View.VISIBLE
        } else {
            tasksContainer.visibility = View.VISIBLE
            emptyStateContainer.visibility = View.GONE
            displayTasks()
        }
    }

    private fun displayTasks() {
        // Clear existing task views
        tasksContainer.removeAllViews()

        // Add all tasks
        tasks.forEach { task ->
            val taskView = createTaskView(task)
            tasksContainer.addView(taskView)
        }
    }

    private fun createTaskView(task: Task): CardView {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            radius = resources.getDimensionPixelSize(R.dimen.card_corner_radius).toFloat()
            elevation = resources.getDimensionPixelSize(R.dimen.card_elevation).toFloat()
            setCardBackgroundColor(resources.getColor(R.color.white, theme))
        }

        val priorityColor = when (task.priority) {
            TaskPriority.HIGH -> R.color.stat_box_purple
            TaskPriority.MEDIUM -> R.color.primary_60
            TaskPriority.LOW -> R.color.stat_box_green
        }

        val daysLeft = task.dueDate?.let { dueDate ->
            val diff = dueDate.time - Date().time
            val days = diff / (24 * 60 * 60 * 1000)
            when {
                days < 0 -> "Overdue"
                days == 0L -> "Due today"
                days == 1L -> "Due tomorrow"
                else -> "Due in $days days"
            }
        } ?: "No due date"

        cardView.addView(LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(20, 20, 20, 20)
            gravity = android.view.Gravity.CENTER_VERTICAL

            // Checkbox
            addView(CheckBox(this@TasksActivity).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 16, 0)
                }
                isChecked = task.isCompleted
                setOnCheckedChangeListener { _, isChecked ->
                    task.isCompleted = isChecked
                    // Refresh the UI when task completion changes
                    updateUI()
                }
            })

            // Task content
            addView(LinearLayout(this@TasksActivity).apply {
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                orientation = LinearLayout.VERTICAL

                // Title
                addView(TextView(this@TasksActivity).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    text = task.title
                    textSize = 16f
                    setTextColor(resources.getColor(R.color.text_primary, theme))
                    setTypeface(null, android.graphics.Typeface.BOLD)
                    if (task.isCompleted) {
                        paintFlags = paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                    }
                })

                // Description
                if (task.description.isNotEmpty()) {
                    addView(TextView(this@TasksActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0, 4, 0, 0)
                        }
                        text = task.description
                        textSize = 14f
                        setTextColor(resources.getColor(R.color.text_secondary, theme))
                        if (task.isCompleted) {
                            paintFlags = paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                        }
                    })
                }

                // Due date and priority
                addView(LinearLayout(this@TasksActivity).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 8, 0, 0)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = android.view.Gravity.CENTER_VERTICAL

                    addView(TextView(this@TasksActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        text = daysLeft
                        textSize = 12f
                        setTextColor(resources.getColor(R.color.text_secondary, theme))
                    })

                    addView(View(this@TasksActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            8,
                            8
                        ).apply {
                            setMargins(16, 0, 16, 0)
                        }
                        background = resources.getDrawable(R.drawable.bg_circle_timer, theme)
                    })

                    addView(TextView(this@TasksActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        text = task.priority.name
                        textSize = 12f
                        setTextColor(resources.getColor(priorityColor, theme))
                        setTypeface(null, android.graphics.Typeface.BOLD)
                    })
                })
            })

            // Edit button
            addView(ImageButton(this@TasksActivity).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16, 0, 0, 0)
                }
                setImageResource(R.drawable.ic_edit)
                background = resources.getDrawable(android.R.drawable.btn_default_small, theme)
                setOnClickListener {
                    showEditTaskDialog(task)
                }
            })
        })

        return cardView
    }

    private fun showAddTaskDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_task)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val etTitle = dialog.findViewById<EditText>(R.id.etTaskTitle)
        val etDescription = dialog.findViewById<EditText>(R.id.etTaskDescription)
        val etDueDate = dialog.findViewById<EditText>(R.id.etTaskDueDate)
        val btnDatePicker = dialog.findViewById<ImageButton>(R.id.btnDatePicker)
        val spinnerPriority = dialog.findViewById<Spinner>(R.id.spinnerPriority)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val btnAdd = dialog.findViewById<Button>(R.id.btnAddTask)

        // Setup priority spinner
        val priorities = arrayOf("LOW", "MEDIUM", "HIGH")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPriority.adapter = adapter

        var selectedDate: Date? = null

        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.time
                    etDueDate.setText(dateFormat.format(selectedDate!!))
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
                etTitle.error = "Task title is required"
                return@setOnClickListener
            }

            val description = etDescription.text.toString().trim()
            val priority = TaskPriority.valueOf(spinnerPriority.selectedItem.toString())

            val newTask = Task(
                title = title,
                description = description,
                dueDate = selectedDate,
                priority = priority,
                isCompleted = false
            )

            tasks.add(newTask)
            updateUI()
            dialog.dismiss()

            Toast.makeText(this, "Task added successfully!", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

    private fun showEditTaskDialog(task: Task) {
        // Similar to add dialog but pre-populate with existing task data
        Toast.makeText(this, "Edit task functionality coming soon!", Toast.LENGTH_SHORT).show()
    }

    data class Task(
        val id: String = System.currentTimeMillis().toString(),
        val title: String,
        val description: String = "",
        val dueDate: Date? = null,
        val priority: TaskPriority = TaskPriority.MEDIUM,
        var isCompleted: Boolean = false,
        val createdAt: Date = Date()
    )

    enum class TaskPriority {
        HIGH, MEDIUM, LOW
    }
}
