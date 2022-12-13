package com.example.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


// SQLiteOpenHelper 用于创建 SQLite 数据库，这里构造器包含 4 个参数，重点关注 name 和 version 参数
// name 表示你要创建的数据库文件，version 表示数据库版本
// SQLiteOpenHelper 是个抽象类，无法直接只用此类来创建和管理数据库，我们必须创建一个其子类。
// 在这里，MyDatabaseHelper 继承了 SQLiteOpenHelper，并实现了 SQLiteOpenHelper 的两个抽象方法
// onCreate 方法在实例化 MyDatabaseHelper 对象时执行，其接收一个 SQLiteDatabase 对象作为参数
// SQLiteDatabase 是个管理 SQlite 数据库的工具类，可用于新建数据库表和对表数据的增删改查
// 在 onCreate 方法中，我们使用 SQLiteDatabase 对象的 execSQL 命令，执行了两行建表命令
// onUpgrade 方法同样会在创建 MyDatabaseHelper 对象时执行，不过其执行条件还包含一个，
class MyDatabaseHelper(val contenxt: Context, name: String, version: Int): SQLiteOpenHelper(contenxt, name, null, version) {

    private val createBook = "create table Book (" +
            "id integer primary key autoincrement, " +
            "author text, price real, pages integer, name text)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement, " +
            "category_name text, category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        Toast.makeText(contenxt, "Create succeed", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

}