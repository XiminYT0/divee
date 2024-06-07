package com.ximin.divee.presentation.onboardingActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class OnboardingActivitySingIn : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_singin)
        val singup = findViewById<Button>(com.ximin.divee.R.id.singin_email)
        singup.setOnClickListener {
            val intent = Intent(this, OnboardingActivitySingInEmail::class.java)
            startActivity(intent)
        }
        val singincode = findViewById<Button>(com.ximin.divee.R.id.nexn_singin)
        singincode.setOnClickListener {
            val intent = Intent(this, OnboardingActivityVerificationCode::class.java)
            startActivity(intent)
        }
    }
}