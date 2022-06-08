package com.example.notbored.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    private var isRandom : Boolean = true

    private var participants = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participants = sharedPreference.getParticipants().toInt()
        val category = sharedPreference.getCategory()

        //here we can see the activity
        viewModel.activityLiveData.observe(this, Observer {
            //activity response
            println("activity response $it")
            showResulsts(it)
        })

        //example get activity random - by type
        viewModel.getActivityRandom()

        //example get activity by participant
        viewModel.getActivityByParticipant(participants)

        //example get activity
        viewModel.getActivityByParticipantAndType(category, participants)


    }

    private fun showResulsts(model: ActivityModel?) {

        //isRandom -> actividad

        when {
            isRandom && model.participants > 0() -> { viewModel.getActivityByParticipant(participants) }
            isRandom && model.participants == 0 -> {viewModel.getActivityRandom()}
            model.participants == 0 -> { }
            model.participants >0 -> {}
        }
    }
}