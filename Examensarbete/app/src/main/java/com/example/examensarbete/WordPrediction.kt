package com.example.examensarbete

import android.annotation.SuppressLint
import java.io.File
import java.io.InputStream

class WordPrediction() {

    val inputStream: InputStream = File("Dictionary").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }

    private class Trie {
        private val root = Node('.')
        private class Node(val value: Char, val children: Array<Node?> = Array<Node?>(26) { null }, var isWord: Boolean = false)

        fun insert(word: String) {
            var currNode = root
            for (char in word) {
                // If child node doesn't yet exist, create one
                if (currNode.children[char - 'a'] == null) currNode.children[char - 'a'] = Node(char)
                // Move to child node
                currNode = currNode.children[char - 'a']!!
            }

            // When reached the end of the word, mark the node as a word
            currNode.isWord = true
        }

        // Recursive search
        private fun _contains(node: Node, word: String, index: Int): Boolean {
            if (index == word.length - 1) {
                if (word[index] == '.') return node.children.any { it != null && it.isWord }
                else return node.children[word[index] - 'a']?.isWord ?: false
            }

            if (word[index] == '.') {
                for (n in node.children) {
                    if (n == null) continue
                    // Here we recursively split between subtrees
                    if (_contains(n!!, word, index + 1)) return true
                }
                return false
            } else {
                if (node.children[word[index] - 'a'] == null) return false
                return _contains(node.children[word[index] - 'a']!!, word, index + 1)
            }

            return false
        }

        fun contains(word: String): Boolean {
            return _contains(root, word, 0)
        }
    }

    private val trie = Trie()

    fun addWord(word: String) {
        trie.insert(word)
    }

    fun search(word: String): Boolean {
        return trie.contains(word)
    }

}
