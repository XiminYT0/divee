package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class OnboardingActivityVerificationCode : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_verification_code)
        val singup_phone = findViewById<Button>(com.ximin.divee.R.id.NextName)
        singup_phone.setOnClickListener {
            val intent = Intent(this, OnboardingActivityPersonName::class.java)
            startActivity(intent)
        }

    }
}