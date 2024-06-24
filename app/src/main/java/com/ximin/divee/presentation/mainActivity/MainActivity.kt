package com.ximin.divee.presentation.mainActivity


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ximin.divee.R
import com.ximin.divee.presentation.mainActivity.viewModel.MainActivityViewModel
import com.ximin.divee.presentation.onboardingActivity.Constants
import com.ximin.divee.presentation.onboardingActivity.OnboardingActivity
import com.ximin.divee.presentation.onboardingActivity.OnboardingActivitySingUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val onboarding:Boolean = false
    private lateinit var repository: ReceiptRepository

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val onboardingCompleted = sharedPreferences.getBoolean(Constants.ONBOARDING_COMPLETED, false)

        if (!onboardingCompleted) {

            startActivity(Intent(this, OnboardingActivity::class.java))
        }

        enableEdgeToEdge()                                                                                             
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val people1 = findViewById<Button>(R.id.button3)
        people1.setOnClickListener {
            val intent = Intent(this, MainActivityPeople::class.java)
            startActivity(intent)
        }
        val settings = findViewById<ImageButton>(R.id.imageButton3)
        settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        val addingCheck = findViewById<Button>(R.id.addcheck)
        addingCheck.setOnClickListener {
            val intent = Intent(this, ActivityAddingCheck::class.java)
            startActivity(intent)
        }
        repository = ReceiptRepository(this)

        val linearLayoutReceipts = findViewById<LinearLayout>(R.id.linearLayoutReceipts)
        val receipts = repository.getAllReceipts()

        receipts.forEach { receipt ->
            val receiptHeaderLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(16, 16, 16, 16)
            }

            val receiptHeaderTextView = TextView(this).apply {
                text = "${receipt.name} - ${receipt.date}\n${receipt.description}"
                textSize = 18f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }
            receiptHeaderLayout.addView(receiptHeaderTextView)

            val deleteButton = Button(this).apply {
                text = "Удалить"
                setOnClickListener {
                    repository.deleteReceipt(receipt.id)
                    Toast.makeText(this@MainActivity, "Чек удален", Toast.LENGTH_SHORT).show()
                    recreate()
                }
            }
            receiptHeaderLayout.addView(deleteButton)

            linearLayoutReceipts.addView(receiptHeaderLayout)

            val items = repository.getItemsForReceipt(receipt.id)
            var receiptTotal = 0.0

            items.forEach { item ->
                val itemTextView = TextView(this).apply {
                    text = "${item.name} - ${item.price} x ${item.quantity} - ${item.personName ?: "Неизвестно"}"
                    setPadding(32, 8, 32, 8)
                }
                linearLayoutReceipts.addView(itemTextView)
                receiptTotal += item.price * item.quantity
            }

            val receiptTotalTextView = TextView(this).apply {
                text = "Суммарно: $receiptTotal"
                textSize = 16f
                setPadding(32, 16, 32, 16)
            }
            linearLayoutReceipts.addView(receiptTotalTextView)
        }
    }





}


