package com.example.examensarbete

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*


open class MainActivity : Activity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomView(this))
        val dbHelper = DBHelper(this, null)
        Log.d("wordCount", dbHelper.getWordCount().toString())


        /*val cursor = dbHelper.getName()

        cursor?.moveToFirst()
        while (!cursor?.isAfterLast!!) {
            Log.d("TAG", cursor.getString(cursor.getColumnIndex("word")))
            cursor.moveToNext()
        }

        val cursor2 = dbHelper.getWord("name")

        cursor2?.moveToFirst()
        while (!cursor2?.isAfterLast!!) {
            Log.d("Found", cursor2.getString(cursor2.getColumnIndex("word")))
            cursor2.moveToNext()
        }*/

        //data/data/<Your-Application-Package-Name>/databases/<your-database-name>

        /*
        val trie = Trie()
        trie.fileNotFoundExceptionExample("Dictionary.txt")
        val word = "Hello"
        val result = trie.search(word)
        Log.d("Trie", "Word: $word, Result: $result")*/
    }

    open fun writeFileOnInternalStorage(mcoContext: Context, sFileName: String?, sBody: String?) {
        val dir = File(mcoContext.filesDir, "mydir")
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val gpxfile = File(dir, sFileName)
            val writer = FileWriter(gpxfile)
            writer.append(sBody)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var string = {}
}
