package com.moataz.afternoonhadeeth.utils.helper

object Cachef {

    private val cache: MutableMap<String, String> = mutableMapOf()

    fun add(key: String, value: String): String {
        if(cache.containsKey(key)) {
            cache[key] = value
            return "overwritten"
        }
        cache[key] = value
        return "added"
    }

    fun get(key: String): String {
        if(cache.containsKey(key)) {
            return cache.getValue(key)
        }
        return "miss"
    }

    fun size(): Int {
        return cache.size
    }
}


fun main() {
    val cache = Cachef

    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.add("article-456", "https://coderbyte.com/article-456"))
    println(cache.add("how-to-code-444", "https://coderbyte.com/how-to-code-444"))
    println(cache.get("first-article"))
    println(cache.get("second-article"))
    println(cache.get("article-456"))
    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.size())
}