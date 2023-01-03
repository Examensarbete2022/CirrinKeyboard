package com.example.examensarbete

import android.app.Activity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.swipe.view.*

open class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomView(this))

        val trie = Trie()
        trie.createTrieFromDictionary()
        val word = "Hello"
        val result = trie.search(word)
        Log.d("Trie", "Word: $word, Result: $result")
    }
}
