package com.ximin.divee.presentation.mainActivity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ReceiptDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "receipts.db"
        private const val DATABASE_VERSION = 3
        const val TABLE_RECEIPTS = "receipts"
        const val TABLE_ITEMS = "items"
        const val TABLE_PEOPLE = "people"
        const val TABLE_ITEM_PEOPLE = "item_people"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createReceiptsTable = """
            CREATE TABLE $TABLE_RECEIPTS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                date TEXT NOT NULL,
                description TEXT
            )
        """
        db.execSQL(createReceiptsTable)

        val createItemsTable = """
            CREATE TABLE $TABLE_ITEMS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                receipt_id INTEGER NOT NULL,
                name TEXT NOT NULL,
                price REAL NOT NULL,
                quantity INTEGER NOT NULL,
                FOREIGN KEY (receipt_id) REFERENCES $TABLE_RECEIPTS (id)
            )
        """
        db.execSQL(createItemsTable)

        val createPeopleTable = """
            CREATE TABLE $TABLE_PEOPLE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            )
        """
        db.execSQL(createPeopleTable)

        val createItemPeopleTable = """
            CREATE TABLE $TABLE_ITEM_PEOPLE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                item_id INTEGER NOT NULL,
                person_id INTEGER NOT NULL,
                FOREIGN KEY (item_id) REFERENCES $TABLE_ITEMS (id),
                FOREIGN KEY (person_id) REFERENCES $TABLE_PEOPLE (id)
            )
        """
        db.execSQL(createItemPeopleTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_ITEM_PEOPLE")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PEOPLE")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_ITEMS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_RECEIPTS")
            onCreate(db)
        }
    }
}
