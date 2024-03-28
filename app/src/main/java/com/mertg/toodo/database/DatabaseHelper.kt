package com.mertg.toodo.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mertg.toodo.adapter.Tasks

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "TaskDatabase.db"
        private const val DATABASE_VERSION = 1

        // Tablo ve sütun isimleri
        private const val TABLE_NAME = "tasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TASK = "task"
        private const val COLUMN_DETAIL = "detail"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Tablo oluşturma SQL sorgusu
        val createTable = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TASK + " TEXT, "
                + COLUMN_DETAIL + " TEXT)")

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Veritabanı sürümü değiştiğinde yapılacak işlemler (örneğin tabloyu güncelleme)
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertTask(task: String, detail: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TASK, task)
        values.put(COLUMN_DETAIL, detail)

        // Veritabanına veriyi ekleyin
        val newRowId = db.insert(TABLE_NAME, null, values)
        //db.close()
        return newRowId
    }

    @SuppressLint("Range")
    fun getAllTasks(): ArrayList<Tasks> {
        val tasks = ArrayList<Tasks>()
        val db = this.readableDatabase
        val cursor: Cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val taskName = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))
            val fullText = cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL))
            val task = Tasks(taskName, fullText)
            tasks.add(task)
        }

        cursor.close()
        //db.close()
        return tasks
    }

    fun deleteTask(taskName: String): Int {
        val db = this.writableDatabase
        val whereClause = "$COLUMN_TASK = ?"
        val whereArgs = arrayOf(taskName)
        val rowsDeleted = db.delete(TABLE_NAME, whereClause, whereArgs)
        //db.close()
        return rowsDeleted
    }

    fun updateTask(taskName: String, newTask: String, newDetail: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TASK, newTask)
        values.put(COLUMN_DETAIL, newDetail)

        val whereClause = "$COLUMN_TASK = ?"
        val whereArgs = arrayOf(taskName)

        val rowsUpdated = db.update(TABLE_NAME, values, whereClause, whereArgs)
//        db.close()
        return rowsUpdated
    }


    fun deleteAllTasks() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
//        db.close()
    }
}
