package com.ximin.divee.presentation.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class SelectPeopleActivity : AppCompatActivity() {
    private lateinit var repository: ReceiptRepository
    private var itemId: Long = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_people)

        repository = ReceiptRepository(this)
        itemId = intent.getLongExtra("ITEM_ID", -1)

        val linearLayoutPeople = findViewById<LinearLayout>(R.id.linearLayoutPeople)
        val buttonSaveSelection = findViewById<Button>(R.id.buttonSaveSelection)

        val people = repository.getAllPeople()
        val checkboxes = mutableListOf<CheckBox>()

        people.forEach { person ->
            val checkBox = CheckBox(this).apply {
                text = person.name
                tag = person.id
            }
            linearLayoutPeople.addView(checkBox)
            checkboxes.add(checkBox)
        }

        buttonSaveSelection.setOnClickListener {
            checkboxes.forEach { checkBox ->
                if (checkBox.isChecked) {
                    val personId = checkBox.tag as Long
                    repository.linkItemToPerson(itemId, personId)
                }
            }
            Toast.makeText(this, "Selection saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
