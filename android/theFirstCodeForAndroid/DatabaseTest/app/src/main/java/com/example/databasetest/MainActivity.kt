package com.example.databasetest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.contentValuesOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val createDatabase: Button = findViewById(R.id.createDatabase)
        val addData: Button = findViewById(R.id.addData)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
//            val value1 = ContentValues().apply {
//                put("name", "The Da Vinci Code")
//                put("author", "Dan Brown")
//                put("pages", 54)
//                put("price", 16.96)
//            }
            val value1 = cvOf(
                "name" to "Game of Thrones", "author" to "George Martin",
                "pages" to 720, "price" to 20.85
            )
            db.insert("Book", null, value1)

//            val value2 = ContentValues().apply {
//                put("name", "The Lost Symbol")
//                put("author", "Dan Brown")
//                put("pages", 510)
//                put("price", 19.95)
//            }
            val value2 = contentValuesOf(
                "name" to "The Lost Symbol",
                "author" to "Dan Brown",
                "pages" to 510,
                "price" to 19.95
            )
            db.insert("Book", null, value2)
        }

        val updateData: Button = findViewById(R.id.updateData)
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        val deleteData: Button = findViewById(R.id.deleteData)
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        val queryData: Button = findViewById(R.id.queryData)
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        val replaceData: Button = findViewById(R.id.replaceData)
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }
}





















