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
    private var participants = 0
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.activitiesToolbar)

        participants = sharedPreference.getParticipants()
        category = sharedPreference.getCategory()

        //here we can see the activity
        viewModel.activityLiveData.observe(this, Observer { model ->
            //activity response
            println("activity response $model")
            setView(model)
        })

        setResultModel()

        binding.tryAnotherB.setOnClickListener {
            setResultModel()
        }
    }

    private fun setResultModel() {
        when {
            isRandom && participants > 0 -> viewModel.getActivityByParticipant(participants)
            !isRandom && participants == 0 -> viewModel.getActivityByType(category)
            !isRandom && participants > 0 -> {
                viewModel.getActivityByParticipantAndType(category, participants)
            }
            else -> viewModel.getActivityRandom()
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

    private fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
