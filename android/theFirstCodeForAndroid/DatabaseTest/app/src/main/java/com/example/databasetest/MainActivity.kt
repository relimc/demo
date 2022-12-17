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

        // 实例化一个 SQLiteOpenHelper 对象，这一行代码执行完毕后，并不会创建 BookStore.db 数据库文件
        // 当执行该对象的 getWritableDatabase 或者 getReadableDatabase 方法时，才会创建数据库文件
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val createDatabase: Button = findViewById(R.id.createDatabase)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        // 向 Book 表新增两条数据
        val addData: Button = findViewById(R.id.addData)
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

        // 更新 Book 表的数据
        val updateData: Button = findViewById(R.id.updateData)
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        // 删除 Book 表的数据
        val deleteData: Button = findViewById(R.id.deleteData)
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        // 查询 Book 表的数据
        val queryData: Button = findViewById(R.id.queryData)
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 这里相当于 select * from Book，查询表中的所有数据，返回一个 Cursor 游标对象
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {  // 将指针移至第一条数据，若存在第一条数据，返回 true
                do {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val author = cursor.getString(cursor.getColumnIndexOrThrow("author"))
                    val pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())  // 当还有下一条数据时，继续执行循环
            }
            cursor.close()
        }

        val replaceData: Button = findViewById(R.id.replaceData)
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()  // 开启事务，保证原子操作
            try {
                db.delete("Book", null, null)  // 删除表中的所有数据
                // 准备一条数据
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                // 向 Book 表中插入一条数据
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()  // 结束事务
            }
        }
    }
}





















