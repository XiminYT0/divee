package com.ximin.divee.presentation.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ximin.divee.R


class MainActivityPeople : AppCompatActivity() {
    private lateinit var repository: ReceiptRepository
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_people)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val settings = findViewById<ImageButton>(R.id.imageButton3)
        settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        val people1 = findViewById<Button>(R.id.button2)
        people1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val addpeople = findViewById<Button>(R.id.Singup)
        addpeople.setOnClickListener {
            val intent = Intent(this, AddPersonActivity::class.java)
            startActivity(intent)
        }
        repository = ReceiptRepository(this)

        val linearLayoutPeopleItems = findViewById<LinearLayout>(R.id.linearLayoutPeopleItems)
        val people = repository.getAllPeople()

        var totalSum = 0.0
        people.forEach { person ->
            val personNameTextView = TextView(this).apply {
                text = person.name
                textSize = 18f
                setPadding(16, 16, 16, 16)
            }
            linearLayoutPeopleItems.addView(personNameTextView)

            val items = repository.getItemsForPerson(person.id)
            var personTotal = 0.0

            items.forEach { item ->
                val itemTextView = TextView(this).apply {
                    text = "${item.name} - ${item.price} x ${item.quantity}"
                    setPadding(32, 8, 32, 8)
                }
                linearLayoutPeopleItems.addView(itemTextView)

                val deleteButton = Button(this).apply {
                    text = "Удалить"
                    setOnClickListener {
                        repository.deleteItem(item.id)
                        Toast.makeText(this@MainActivityPeople, "Позиция удалена", Toast.LENGTH_SHORT).show()
                        recreate()
                    }
                }
                linearLayoutPeopleItems.addView(deleteButton)

                personTotal += item.price * item.quantity
            }

            val personTotalTextView = TextView(this).apply {
                text = "Суммарно ${person.name}: $personTotal"
                textSize = 16f
                setPadding(32, 16, 32, 16)
            }
            linearLayoutPeopleItems.addView(personTotalTextView)

            totalSum += personTotal
        }



    }
}