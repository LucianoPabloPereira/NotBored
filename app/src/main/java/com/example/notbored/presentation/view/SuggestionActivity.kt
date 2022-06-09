package com.example.notbored.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.notbored.data.model.ActivityModel
import com.example.notbored.data.preferences.IPreferenceHelper
import com.example.notbored.data.preferences.PreferenceManager
import com.example.notbored.databinding.ActivitySuggestionBinding
import com.example.notbored.presentation.viewmodel.ViewModelActivity


class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private val viewModel: ViewModelActivity by viewModels(factoryProducer = { ViewModelActivity.Factory() })
    private val sharedPreference: IPreferenceHelper by lazy { PreferenceManager(applicationContext) }
    private var isRandom: Boolean = true
    private lateinit var toolbar: Toolbar

    private var participants = 0
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.activitiesToolbar
        toolbar.title = sharedPreference.getCategory()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        participants = if (sharedPreference.getParticipants().isEmpty()) {
            0
        } else {
            sharedPreference.getParticipants().toInt()
        }

        category = sharedPreference.getCategory()



        //here we can see the activity
        viewModel.activityLiveData.observe(this, Observer {model ->
            //activity response
            println("activity response $model")
            showResults(model)

            binding.tryAnotherB.setOnClickListener {
                showResults(model)
            }
        })

        //example get activity random
        viewModel.getActivityRandom()

        //example get activity by participant
        viewModel.getActivityByParticipant(participants)

        //example get activity
        viewModel.getActivityByParticipantAndType(category, participants)


    }

    private fun showResults(model: ActivityModel) {

        when {
            isRandom && model.participants > 0 -> {
                viewModel.getActivityByParticipant(participants)
                setView(model)
            }
            isRandom && model.participants == 0 -> {
                viewModel.getActivityRandom()
                setView(model)
            }
            !isRandom && model.participants == 0 -> {
                viewModel.getActivityByType(category)
                setView(model)
            }
            !isRandom && model.participants > 0 -> {
                viewModel.getActivityByParticipantAndType(category, participants)
                setView(model)
            }
        }
    }

    private fun setView(model: ActivityModel) {
        with (binding){
            activitiesToolbar.title = model.type
            suggestedActivityTV.text = model.activity
            amountOfParticipants.text = model.participants.toString()
            priceShowedTV.text = model.price.toString()

            if (isRandom) {
                randomActivityIV.visibility = View.VISIBLE
                randomActivityTV.visibility = View.VISIBLE
                randomActivityShowedTV.apply {
                    text = model.type
                    visibility = View.VISIBLE
                }
            }else {
                randomActivityShowedTV.visibility = View.GONE
            }
        }
    }




}