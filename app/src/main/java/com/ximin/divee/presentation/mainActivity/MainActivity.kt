package com.ximin.divee.presentation.mainActivity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ximin.divee.R
import com.ximin.divee.presentation.mainActivity.viewModel.MainActivityViewModel
import com.ximin.divee.presentation.onboardingActivity.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val onboarding:Boolean = false

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIfOnboardingPassed()

        enableEdgeToEdge()                                                                                             
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun checkIfOnboardingPassed(){
        if (!onboarding){
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }

    }



}


