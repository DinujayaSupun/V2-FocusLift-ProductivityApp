package com.example.focuslift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.models.FavoriteGoal
import com.google.android.material.card.MaterialCardView

class FavoriteGoalsAdapter(
    private val goals: List<FavoriteGoal>,
    private val onGoalClick: (FavoriteGoal) -> Unit
) : RecyclerView.Adapter<FavoriteGoalsAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_goal, parent, false)
        return GoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(goals[position])
    }

    override fun getItemCount(): Int = goals.size

    inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.goalCard)
        private val goalTitle: TextView = itemView.findViewById(R.id.goalTitle)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        private val progressText: TextView = itemView.findViewById(R.id.progressText)
        private val progressDetails: TextView = itemView.findViewById(R.id.progressDetails)

        fun bind(goal: FavoriteGoal) {
            goalTitle.text = goal.title
            progressText.text = goal.progress
            progressDetails.text = goal.progressDetails
            
            // Set progress bar value (extract percentage from string)
            val progressValue = goal.progress.replace("%", "").toIntOrNull() ?: 0
            progressBar.progress = progressValue
            
            cardView.setOnClickListener {
                onGoalClick(goal)
            }
        }
    }
}
