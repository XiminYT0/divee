package com.ximin.divee.presentation.mainActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class AddPersonActivity : AppCompatActivity() {
    private lateinit var repository: ReceiptRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        repository = ReceiptRepository(this)

        val editTextPersonName = findViewById<EditText>(R.id.editTextPersonName)
        val buttonSavePerson = findViewById<Button>(R.id.buttonSavePerson)

        buttonSavePerson.setOnClickListener {
            val personName = editTextPersonName.text.toString()
            if (personName.isNotEmpty()) {
                repository.addPerson(personName)
                Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
