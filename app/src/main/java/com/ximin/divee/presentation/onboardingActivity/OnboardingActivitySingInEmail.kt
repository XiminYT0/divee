package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class OnboardingActivitySingInEmail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_singin_email)
        val singup = findViewById<Button>(com.ximin.divee.R.id.singin_phone)
        singup.setOnClickListener {
            val intent = Intent(this, OnboardingActivitySingIn::class.java)
            startActivity(intent)
        }
    }
}