package com.ximin.divee.presentation.mainActivity

import android.content.ContentValues
import android.content.Context


class ReceiptRepository(context: Context) {
    private val dbHelper = ReceiptDatabaseHelper(context)

    fun addReceipt(name: String, date: String, description: String?): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("date", date)
            put("description", description)
        }
        return db.insert(ReceiptDatabaseHelper.TABLE_RECEIPTS, null, values)
    }

    fun addItem(receiptId: Long, name: String, price: Double, quantity: Int): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("receipt_id", receiptId)
            put("name", name)
            put("price", price)
            put("quantity", quantity)
        }
        return db.insert(ReceiptDatabaseHelper.TABLE_ITEMS, null, values)
    }

    fun addPerson(name: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        return db.insert(ReceiptDatabaseHelper.TABLE_PEOPLE, null, values)
    }

    fun linkItemToPerson(itemId: Long, personId: Long) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("item_id", itemId)
            put("person_id", personId)
        }
        db.insert(ReceiptDatabaseHelper.TABLE_ITEM_PEOPLE, null, values)
    }

    fun getAllPeople(): List<Person> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            ReceiptDatabaseHelper.TABLE_PEOPLE,
            arrayOf("id", "name"),
            null,
            null,
            null,
            null,
            null
        )

        val people = mutableListOf<Person>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                people.add(Person(id, name))
            }
        }
        cursor.close()
        return people
    }
    fun getItemsForPerson(personId: Long): List<Item> {
        val db = dbHelper.readableDatabase
        val query = """
        SELECT i.id, i.name, i.price, i.quantity 
        FROM ${ReceiptDatabaseHelper.TABLE_ITEMS} i
        JOIN ${ReceiptDatabaseHelper.TABLE_ITEM_PEOPLE} ip
        ON i.id = ip.item_id
        WHERE ip.person_id = ?
    """
        val cursor = db.rawQuery(query, arrayOf(personId.toString()))

        val items = mutableListOf<Item>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val price = getDouble(getColumnIndexOrThrow("price"))
                val quantity = getInt(getColumnIndexOrThrow("quantity"))
                items.add(Item(id, name, price, quantity))
            }
        }
        cursor.close()
        return items
    }

    fun deleteItem(itemId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(ReceiptDatabaseHelper.TABLE_ITEMS, "id = ?", arrayOf(itemId.toString()))
        db.delete(ReceiptDatabaseHelper.TABLE_ITEM_PEOPLE, "item_id = ?", arrayOf(itemId.toString()))
    }

    data class Item(val id: Long, val name: String, val price: Double, val quantity: Int)

    data class Person(val id: Long, val name: String)
    fun getAllReceipts(): List<Receipt> {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM ${ReceiptDatabaseHelper.TABLE_RECEIPTS}"
        val cursor = db.rawQuery(query, null)

        val receipts = mutableListOf<Receipt>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val date = getString(getColumnIndexOrThrow("date"))
                val description = getString(getColumnIndexOrThrow("description"))
                receipts.add(Receipt(id, name, date, description))
            }
        }
        cursor.close()
        return receipts
    }

    fun getItemsForReceipt(receiptId: Long): List<ItemWithPeople> {
        val db = dbHelper.readableDatabase
        val query = """
        SELECT i.id, i.name, i.price, i.quantity, p.name as person_name
        FROM ${ReceiptDatabaseHelper.TABLE_ITEMS} i
        LEFT JOIN ${ReceiptDatabaseHelper.TABLE_ITEM_PEOPLE} ip
        ON i.id = ip.item_id
        LEFT JOIN ${ReceiptDatabaseHelper.TABLE_PEOPLE} p
        ON ip.person_id = p.id
        WHERE i.receipt_id = ?
    """
        val cursor = db.rawQuery(query, arrayOf(receiptId.toString()))

        val items = mutableListOf<ItemWithPeople>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow("id"))
                val itemName = getString(getColumnIndexOrThrow("name"))
                val itemPrice = getDouble(getColumnIndexOrThrow("price"))
                val itemQuantity = getInt(getColumnIndexOrThrow("quantity"))
                val personName = getString(getColumnIndexOrThrow("person_name"))
                items.add(ItemWithPeople(itemId, itemName, itemPrice, itemQuantity, personName))
            }
        }
        cursor.close()
        return items
    }


    data class Receipt(val id: Long, val name: String, val date: String, val description: String)
    data class ItemWithPeople(val id: Long, val name: String, val price: Double, val quantity: Int, val personName: String?)
    fun deleteReceipt(receiptId: Long) {
        val db = dbHelper.writableDatabase
        db.beginTransaction()
        try {
            db.delete(ReceiptDatabaseHelper.TABLE_ITEMS, "receipt_id=?", arrayOf(receiptId.toString()))
            db.delete(ReceiptDatabaseHelper.TABLE_RECEIPTS, "id=?", arrayOf(receiptId.toString()))
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
}
