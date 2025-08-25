package com.example.focuslift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.models.FavoriteItem
import com.example.focuslift.models.FavoriteType
import java.text.SimpleDateFormat
import java.util.*

class FavoritesAdapter(
    private val favorites: List<FavoriteItem>,
    private val onFavoriteClick: (FavoriteItem) -> Unit,
    private val onRemoveFavorite: (FavoriteItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_QUOTE = 0
        private const val VIEW_TYPE_TASK = 1
        private const val VIEW_TYPE_TIP = 2
        private const val VIEW_TYPE_GOAL = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (favorites[position].type) {
            FavoriteType.QUOTE -> VIEW_TYPE_QUOTE
            FavoriteType.TASK -> VIEW_TYPE_TASK
            FavoriteType.TIP -> VIEW_TYPE_TIP
            FavoriteType.GOAL -> VIEW_TYPE_GOAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_QUOTE -> QuoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_quote, parent, false)
            )
            VIEW_TYPE_TASK -> TaskViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_task, parent, false)
            )
            VIEW_TYPE_TIP -> TipViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_tip, parent, false)
            )
            VIEW_TYPE_GOAL -> GoalViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_goal, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val favorite = favorites[position]
        when (holder) {
            is QuoteViewHolder -> holder.bind(favorite)
            is TaskViewHolder -> holder.bind(favorite)
            is TipViewHolder -> holder.bind(favorite)
            is GoalViewHolder -> holder.bind(favorite)
        }
    }

    override fun getItemCount(): Int = favorites.size

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuote: TextView = itemView.findViewById(R.id.tvQuote)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val btnRemove: ImageButton = itemView.findViewById(R.id.btnRemove)

        fun bind(favorite: FavoriteItem) {
            tvQuote.text = favorite.content
            tvAuthor.text = favorite.title
            tvTimestamp.text = formatTimestamp(favorite.timestamp)
            
            itemView.setOnClickListener { onFavoriteClick(favorite) }
            btnRemove.setOnClickListener { onRemoveFavorite(favorite) }
        }
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTaskTitle: TextView = itemView.findViewById(R.id.tvTaskTitle)
        private val tvTaskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val btnRemove: ImageButton = itemView.findViewById(R.id.btnRemove)

        fun bind(favorite: FavoriteItem) {
            tvTaskTitle.text = favorite.title
            tvTaskDescription.text = favorite.content
            tvTimestamp.text = formatTimestamp(favorite.timestamp)
            
            itemView.setOnClickListener { onFavoriteClick(favorite) }
            btnRemove.setOnClickListener { onRemoveFavorite(favorite) }
        }
    }

    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTipTitle: TextView = itemView.findViewById(R.id.tvTipTitle)
        private val tvTipContent: TextView = itemView.findViewById(R.id.tvTipContent)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val btnRemove: ImageButton = itemView.findViewById(R.id.btnRemove)

        fun bind(favorite: FavoriteItem) {
            tvTipTitle.text = favorite.title
            tvTipContent.text = favorite.content
            tvTimestamp.text = formatTimestamp(favorite.timestamp)
            
            itemView.setOnClickListener { onFavoriteClick(favorite) }
            btnRemove.setOnClickListener { onRemoveFavorite(favorite) }
        }
    }

    inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvGoalTitle: TextView = itemView.findViewById(R.id.tvGoalTitle)
        private val tvGoalDescription: TextView = itemView.findViewById(R.id.tvGoalDescription)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val btnRemove: ImageButton = itemView.findViewById(R.id.btnRemove)

        fun bind(favorite: FavoriteItem) {
            tvGoalTitle.text = favorite.title
            tvGoalDescription.text = favorite.content
            tvTimestamp.text = formatTimestamp(favorite.timestamp)
            
            itemView.setOnClickListener { onFavoriteClick(favorite) }
            btnRemove.setOnClickListener { onRemoveFavorite(favorite) }
        }
    }

    private fun formatTimestamp(date: Date): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return sdf.format(date)
    }
}
