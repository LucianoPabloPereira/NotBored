package com.example.notbored.presentation.view


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.notbored.R
import com.example.notbored.data.preferences.IPreferenceHelper
import com.example.notbored.data.preferences.PreferenceManager
import com.example.notbored.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val sharedPreference: IPreferenceHelper by lazy { PreferenceManager(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            //listener edittext amount of participants
            numberOfParticipantsET.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,count: Int, after: Int) {}

                override fun onTextChanged(
                    query: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    if (!query.isEmpty()) setStartButtonEnabled(Integer.parseInt(query.toString()))
                }
            })

            //button start listener
            startB.setOnClickListener {
                saveParticipant()
                navigateToActivities()
            }

            termsAndConditionsTV.setOnClickListener { showTermsAndConditions()}
        }

    }

    private fun navigateToActivities() {
        val intent = Intent(applicationContext, ActivitiesActivity::class.java)
        startActivity(intent)
    }

    private fun saveParticipant() {
        val inputParticipants = binding.numberOfParticipantsET.text.toString()
        val intValue = if (inputParticipants.isEmpty()) 0 else inputParticipants.toInt()
        sharedPreference.setParticipants(intValue)
    }

    /**
     * show terms and conditions in Custom AlertDialog
     */
    fun showTermsAndConditions(){
        val dialog = AlertDialog.Builder(this)
        val layout = this.layoutInflater.inflate( R.layout.terms_and_conditions, null)
        val buttonClose = layout.findViewById(R.id.closeIV) as ImageView
        dialog.setView(layout)
        val alertDialog = dialog.create()
        //listener image close layout
        buttonClose.setOnClickListener{
            alertDialog.dismiss() //close alertdialog
        }
        alertDialog.show()
        //view full screen
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }


    fun setStartButtonEnabled(amount: Int) {
        binding.startB.isEnabled = amount < 0
    }



}