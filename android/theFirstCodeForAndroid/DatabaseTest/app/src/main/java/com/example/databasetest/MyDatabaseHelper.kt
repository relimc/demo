package com.example.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


// SQLiteOpenHelper 用于创建 SQLite 数据库，这里构造器包含 4 个参数，重点关注 name 和 version 参数
// name 表示你要创建的数据库文件，version 表示数据库版本
// SQLiteOpenHelper 是个抽象类，无法直接只用此类来创建和管理数据库，我们必须创建一个其子类。
// 在这里，MyDatabaseHelper 继承了 SQLiteOpenHelper，并实现了 SQLiteOpenHelper 的两个抽象方法
// onCreate 方法在 SQLiteOpenHelper 的 getReadableDatabase() 或者 getWritableDatabase() 被调用时执行，其接收一个 SQLiteDatabase 对象作为参数
// 同时，getReadableDatabase() 或者 getWritableDatabase() 的返回值也是个 SQLiteDatabase 对象
// SQLiteDatabase 是个管理 SQLite 数据库的工具类，可用于新建数据库表，可用于对数据的增删改查
// 在 onCreate 方法中，我们使用 SQLiteDatabase 对象的 execSQL 命令，执行了两行建表命令

// onUpgrade 方法同样会在 SQLiteOpenHelper 的 getReadableDatabase() 或者 getWritableDatabase() 被调用时执行，不过 onUpgrade 方法的执行还受其他条件的约束
// 在初始化一个 MyDatabaseHelper 对象时，只有指定的数据库（MyDatabaseHelper 构造器的第二个参数）不存在，才会触发这个方法
// 而当指定的数据库存在时，需要传递与之前不一样 version 参数，才会触发这个方法
// 当新 version 比旧 version 大时，会触发 onUpgrade 方法
// 当新 version 比旧 version 小时，会触发 onDowngrade 方法
// onUpgrade 方法的使用场景是，当我们安装了版本号为 1 的应用时，创建了数据库 A，数据库版本为 1。现在我们对数据库做了些改动，比如新增一张表，准备升级应用为版本 2。
// 如果此时数据库版本仍然是1，我们安装了版本号为 2 的应用时，将无法完成数据库的升级操作，onUpgrade 方法将无法执行。
// 只有将数据库版本改为2，才会触发 onUpgrade 方法。
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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

}