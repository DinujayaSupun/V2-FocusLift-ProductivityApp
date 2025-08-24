package com.example.focuslift.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.focuslift.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the Home Page layout
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // 🔹 Called from android:onClick in fragment_home.xml
    fun openDailyMotivation(view: View) {
        // For exam UI purposes → simple Toast
        Toast.makeText(requireContext(), "Opening Daily Motivation…", Toast.LENGTH_SHORT).show()

        // 👉 If you want to really navigate to another fragment, uncomment below:

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuotesFragment())
            .addToBackStack(null)
            .commit()

    }

    fun openExploreQuotes(view: View) {
        Toast.makeText(requireContext(), "Opening All Quotes…", Toast.LENGTH_SHORT).show()

        // 👉 If you want navigation:

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, QuotesFragment())
            .addToBackStack(null)
            .commit()

    }
}
