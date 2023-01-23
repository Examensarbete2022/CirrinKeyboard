package com.example.examensarbete

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Job
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

var job: Job? = null

open class MainActivity : Activity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomView(this))
        val dbHelper = DBHelper(this, null)
        Log.d("wordCount", dbHelper.getWordCount().toString())
    }
}
