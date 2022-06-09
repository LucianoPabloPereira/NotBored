package com.example.notbored.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notbored.R
import com.example.notbored.data.preferences.IPreferenceHelper
import com.example.notbored.data.preferences.PreferenceManager
import com.example.notbored.databinding.ActivityActivitiesBinding
import com.example.notbored.presentation.view.adapter.ActivitiesAdapter
import com.example.notbored.presentation.view.adapter.OnItemClickListener

private lateinit var binding : ActivityActivitiesBinding
private lateinit var adapter: ActivitiesAdapter

class ActivitiesActivity : AppCompatActivity(), OnItemClickListener {

    private val sharedPreference: IPreferenceHelper by lazy { PreferenceManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.activitiesToolbar)

        adapter = ActivitiesAdapter(getCategoryList(), this)
        binding.activitiesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activitiesRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.randomButton) {
            sharedPreference.setCategory("")
            goToSuggestion()
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

    override fun onItemClick(category: String) {
        sharedPreference.setCategory(category)
        goToSuggestion()
    }

    private fun goToSuggestion() {
        val intent = Intent(this, SuggestionActivity::class.java)
        startActivity(intent)
    }

    private fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}