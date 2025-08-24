package com.example.focuslift.fragments

import android.os.Bundle
import android.text.Spanned
import android.text.style.StyleSpan
import android.graphics.Typeface
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.example.focuslift.R

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Render rich welcome text from strings.xml (HTML)
        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = HtmlCompat.fromHtml(getString(R.string.welcome_rich), HtmlCompat.FROM_HTML_MODE_LEGACY)

        val seeMore = view.findViewById<Button>(R.id.btnSeeMore)
        seeMore.setOnClickListener {
            // Optional: switch to Quotes tab visually for demo
            activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
                ?.selectedItemId = R.id.quotesFragment
        }
    }
}
