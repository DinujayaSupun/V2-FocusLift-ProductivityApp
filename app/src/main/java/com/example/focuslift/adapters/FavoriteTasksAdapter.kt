package com.example.focuslift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.models.FavoriteTask
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip

class FavoriteTasksAdapter(
    private val tasks: List<FavoriteTask>,
    private val onTaskClick: (FavoriteTask) -> Unit
) : RecyclerView.Adapter<FavoriteTasksAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.taskCard)
        private val taskTitle: TextView = itemView.findViewById(R.id.taskTitle)
        private val priorityChip: Chip = itemView.findViewById(R.id.priorityChip)
        private val dueDate: TextView = itemView.findViewById(R.id.dueDate)

        fun bind(task: FavoriteTask) {
            taskTitle.text = task.title
            priorityChip.text = task.priority
            dueDate.text = task.dueDate
            
            // Set priority chip color based on priority
            when (task.priority) {
                "High Priority" -> priorityChip.setChipBackgroundColorResource(R.color.red)
                "Medium Priority" -> priorityChip.setChipBackgroundColorResource(R.color.orange_500)
                "Low Priority" -> priorityChip.setChipBackgroundColorResource(R.color.green_500)
            }
            
            cardView.setOnClickListener {
                onTaskClick(task)
            }
        }
    }
}
