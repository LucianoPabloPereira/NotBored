package com.example.notbored.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notbored.R
import com.example.notbored.databinding.ActivityActivitiesBinding
import com.example.notbored.presentation.view.adapter.ActivitiesAdapter

private lateinit var binding : ActivityActivitiesBinding
private lateinit var adapter: ActivitiesAdapter

class ActivitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.activitiesToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = ActivitiesAdapter(getCategoryList())
        binding.activitiesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activitiesRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.randomButton) {
            Toast.makeText(this, "get random activity", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCategoryList() = listOf(
            resources.getString(R.string.education),
            resources.getString(R.string.recreational),
            resources.getString(R.string.social),
            resources.getString(R.string.diy),
            resources.getString(R.string.charity),
            resources.getString(R.string.cooking),
            resources.getString(R.string.relaxation),
            resources.getString(R.string.music),
            resources.getString(R.string.busywork),
        )
}