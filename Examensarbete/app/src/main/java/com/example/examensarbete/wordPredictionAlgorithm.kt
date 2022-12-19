package com.example.examensarbete

/*class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var isEndOfWord: Boolean = false
}

class Trie {
    val root: TrieNode = TrieNode()

    fun insert(word: String) {
        var current = root
        for (c in word) {
            if (!current.children.containsKey(c)) {
                current.children[c] = TrieNode()
            }
            current = current.children[c]!!
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (c in word) {
            if (!current.children.containsKey(c)) {
                return false
            }
            current = current.children[c]!!
        }
        return current.isEndOfWord
    }


}*/

class SearchTreeNode {
    val children: MutableMap<Char, SearchTreeNode> = mutableMapOf()
    var isWord: Boolean = false

    fun insert(word: String) {
        var current = this
        for (c in word) {
            if (!current.children.containsKey(c)) {
                current.children[c] = SearchTreeNode()
            }
            current = current.children[c]!!
        }
        current.isWord = true
    }

    fun search(query: String): Boolean {
        return search(query, 0, 1)
    }

    private fun search(query: String, index: Int, errorsAllowed: Int): Boolean {
        if (errorsAllowed < 0) {
            return false
        }
        if (index == query.length) {
            return isWord
        }

        val c = query[index]
        if (children.containsKey(c)) {
            if (search(query, index + 1, errorsAllowed)) {
                return true
            }
        }

        // Try inserting a character
        for (i in 'a'..'z') {
            if (i == c) {
                continue
            }
            if (children.containsKey(i)) {
                if (search(query, index, errorsAllowed - 1)) {
                    return true
                }
            }
        }

        // Try deleting a character
        if (search(query, index + 1, errorsAllowed - 1)) {
            return true
        }

        // Try replacing a character
        for (i in 'a'..'z') {
            if (i == c) {
                continue
            }
            if (children.containsKey(i)) {
                if (search(query, index + 1, errorsAllowed - 1)) {
                    return true
                }
            }
        }

        return false
    }
}
