package com.flashcard.app.data.model

data class Flashcard(
    val id: String,
    val question: String,
    val answer: String,
    val createdAt: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis()
)