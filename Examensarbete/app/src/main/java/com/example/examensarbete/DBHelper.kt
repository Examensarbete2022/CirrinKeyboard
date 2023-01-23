package com.example.examensarbete

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.*

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db:SQLiteDatabase, p1: Int, p2: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addName(word: String){
        val values = ContentValues()
        values.put(WORD_COL, word)
        val db = this@DBHelper.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getName(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getWord(word: String): Cursor? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $WORD_COL = ?"
        return db.rawQuery(query, arrayOf(word))
    }

    fun removeAll(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    fun getWordCount(): Int {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        return cursor.count
    }

    @SuppressLint("Range")
    fun getSimilarNames(word: String): List<String>{
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $WORD_COL LIKE '$word%'"
        val cursor = db.rawQuery(query, null)
        val list = mutableListOf<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            list.add(cursor.getString(cursor.getColumnIndex(WORD_COL)))
            cursor.moveToNext()
        }
        return list
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mydatabase.db"
        val TABLE_NAME = "mytable"
        val ID_COL = "id_col"
        val WORD_COL = "word_col"
    }

}