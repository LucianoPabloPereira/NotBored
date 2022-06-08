package com.example.notbored.presentation.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.notbored.R
import android.widget.TextView
import android.content.Intent
import com.example.notbored.presentation.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModelActivity by viewModels(factoryProducer = { ViewModelActivity.Factory() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.startB).setOnClickListener {
            val intent = Intent(this, ActivitiesActivity::class.java)
            startActivity(intent)
        }

        //here we can see the activity
        viewModel.activityLiveData.observe(this, Observer {
            //activity response
            println("activity response $it")
        })

        //example get activity random
        viewModel.getActivityRandom()

        //example get activity by participant
        viewModel.getActivityByParticipant(3)



    }

}