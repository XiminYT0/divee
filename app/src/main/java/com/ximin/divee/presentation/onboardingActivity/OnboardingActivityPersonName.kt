package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class OnboardingActivityPersonName : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_person_name)
        val toloading = findViewById<Button>(com.ximin.divee.R.id.ToLoading)
        toloading.setOnClickListener {
            val intent = Intent(this, OnboardingActivityLoadingApp::class.java)
            startActivity(intent)
            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean(Constants.ONBOARDING_COMPLETED, true)
                apply()
            }


        }
    }
}