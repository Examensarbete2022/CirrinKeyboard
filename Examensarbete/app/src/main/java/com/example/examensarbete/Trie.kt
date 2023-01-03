package com.example.examensarbete

import java.io.BufferedReader
import java.io.FileReader

class Trie {

    //create code that reads all lines from Dictionary.txt and adds them to the trie
    data class Node(var word: String? = null, val childNodes: MutableMap<Char, Node> = mutableMapOf())

    private val root = Node()

    /*fun createTrieFromDictionary(): Trie {
        val root = Trie()

        BufferedReader(FileReader("dictionary.txt")).use { reader ->
            var line: String? = reader.readLine()
            while (line != null) {
                root.insert(line) // insert the word into the trie
                line = reader.readLine()
            }
        }

        return root
    }*/

    fun createTrieFromDictionary(): Trie {
        val root = Trie()

        try {
            val reader = BufferedReader(FileReader("Dictionary.txt"))
            val lines = reader.readLines()
            lines.forEach { root.insert(it) } // insert each word into the trie
        } catch (e: Exception) {
            // handle the exception here, for example by logging it
            println("An error occurred while reading the dictionary file: $e")
        }
        return root
    }


/*fun createTrieFromDictionary(): Trie {
    val root = Trie()

    try {
        val reader = BufferedReader(FileReader("dictionary.txt"))
        var line: String? = reader.readLine()
        while (line != null) {
            root.insert(line) // insert the word into the trie
            line = reader.readLine()
        }
    } finally {
        reader.close()
    }

    return root
}

*/


    fun insert(word: String) {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.word = word
    }

    fun search(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.word != null
    }
}