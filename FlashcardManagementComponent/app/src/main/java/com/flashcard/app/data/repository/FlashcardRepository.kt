package com.flashcard.app.data.repository

import com.flashcard.app.data.local.FlashcardDao
import com.flashcard.app.data.model.Flashcard
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class FlashcardRepository(private val flashcardDao: FlashcardDao) {

    fun getAllFlashcards(): Flow<List<Flashcard>> = flashcardDao.getAllFlashcards()

    suspend fun getFlashcardById(id: String): Flashcard? = flashcardDao.getFlashcardById(id)

    suspend fun insertFlashcard(question: String, answer: String) {
        val flashcard = Flashcard(
            id = UUID.randomUUID().toString(),
            question = question,
            answer = answer
        )
        flashcardDao.insertFlashcard(flashcard)
    }

    suspend fun updateFlashcard(flashcard: Flashcard) {
        val updatedFlashcard = flashcard.copy(
            lastModified = System.currentTimeMillis()
        )
        flashcardDao.insertFlashcard(updatedFlashcard)
    }

    suspend fun deleteFlashcard(flashcard: Flashcard) = flashcardDao.deleteFlashcard(flashcard)

    suspend fun deleteAllFlashcards() = flashcardDao.deleteAllFlashcards()
}