package com.example.focuslift.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.focuslift.R
import com.example.focuslift.models.FavoriteQuote
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip

class FavoriteQuotesAdapter(
    private val quotes: List<FavoriteQuote>,
    private val onQuoteClick: (FavoriteQuote) -> Unit
) : RecyclerView.Adapter<FavoriteQuotesAdapter.QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quotes[position])
    }

    override fun getItemCount(): Int = quotes.size

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.quoteCard)
        private val quoteText: TextView = itemView.findViewById(R.id.quoteText)
        private val quoteAuthor: TextView = itemView.findViewById(R.id.quoteAuthor)
        private val categoryChip: Chip = itemView.findViewById(R.id.categoryChip)

        fun bind(quote: FavoriteQuote) {
            quoteText.text = quote.text
            quoteAuthor.text = "- ${quote.author}"
            categoryChip.text = quote.category
            
            cardView.setOnClickListener {
                onQuoteClick(quote)
            }
        }
    }
}
