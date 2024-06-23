package com.ximin.divee.presentation.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ximin.divee.R

class ActivityAddingCheck : AppCompatActivity() {
    private lateinit var repository: ReceiptRepository
    private var receiptId: Long = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_check)
        val next = findViewById<Button>(R.id.button4)
        next.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        repository = ReceiptRepository(this)

        val editTextReceiptName = findViewById<EditText>(R.id.Name)
        val editTextReceiptDate = findViewById<EditText>(R.id.Date)
        val editTextReceiptDescription = findViewById<EditText>(R.id.Description)
        val editTextItemName = findViewById<EditText>(R.id.NamePosition)
        val editTextItemPrice = findViewById<EditText>(R.id.Price)
        val editTextItemQuantity = findViewById<EditText>(R.id.quantity)
        val buttonSaveReceipt = findViewById<Button>(R.id.saveCheck)
        val buttonSaveItem = findViewById<Button>(R.id.button6)

        buttonSaveReceipt.setOnClickListener {
            try {
                val receiptName = editTextReceiptName.text.toString()
                val receiptDate = editTextReceiptDate.text.toString()
                val receiptDescription = editTextReceiptDescription.text.toString()

                if (receiptName.isEmpty() || receiptDate.isEmpty()) {
                    Toast.makeText(this, "Заполни все поля", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                receiptId = repository.addReceipt(receiptName, receiptDate, receiptDescription)
                if (receiptId != -1L) {
                    Toast.makeText(this, "Чек сохранен", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ошибка сохранения чека", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Ошибка сохранения чека", e)
                Toast.makeText(this, "Ошибка сохранения чека", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSaveItem.setOnClickListener {
            try {
                if (receiptId == -1L) {
                    Toast.makeText(this, "Сначала сохрани чек", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val itemName = editTextItemName.text.toString()
                val itemPrice = editTextItemPrice.text.toString().toDoubleOrNull() ?: 0.0
                val itemQuantity = editTextItemQuantity.text.toString().toIntOrNull() ?: 0

                if (itemName.isEmpty() || itemPrice <= 0 || itemQuantity <= 0) {
                    Toast.makeText(
                        this,
                        "Заполните все поля",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val itemId = repository.addItem(receiptId, itemName, itemPrice, itemQuantity)
                if (itemId != -1L) {
                    Toast.makeText(this, "Позиция сохранена", Toast.LENGTH_SHORT).show()
                    // Переходим к выбору людей для этой позиции
                    val intent = Intent(this, SelectPeopleActivity::class.java)
                    intent.putExtra("ITEM_ID", itemId)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Ошибка сохранения позиции", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Ошибка сохранения позиции", e)
                Toast.makeText(this, "Ошибка сохранения позиции", Toast.LENGTH_SHORT).show()
            }
        }
    }
}