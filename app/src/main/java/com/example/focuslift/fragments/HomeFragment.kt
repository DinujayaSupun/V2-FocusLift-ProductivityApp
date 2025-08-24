package com.example.focuslift.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.focuslift.R
import com.example.focuslift.activities.AnalyticsActivity
import com.example.focuslift.activities.FocusSessionActivity
import com.example.focuslift.activities.GoalsActivity
import com.example.focuslift.activities.TasksActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun navigateToTimer(view: View) {
        val intent = Intent(requireContext(), FocusSessionActivity::class.java)
        startActivity(intent)
    }

    fun navigateToTasks(view: View) {
        val intent = Intent(requireContext(), TasksActivity::class.java)
        startActivity(intent)
    }

    fun navigateToAnalytics(view: View) {
        val intent = Intent(requireContext(), AnalyticsActivity::class.java)
        startActivity(intent)
    }

    fun navigateToGoals(view: View) {
        val intent = Intent(requireContext(), GoalsActivity::class.java)
        startActivity(intent)
    }
}
