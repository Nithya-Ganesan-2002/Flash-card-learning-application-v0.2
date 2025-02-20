package com.flashcard.app.ui.flashcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flashcard.app.data.model.Flashcard
import java.util.UUID

class FlashcardEditViewModel : ViewModel() {
    private val _question = MutableLiveData<String>()
    val question: LiveData<String> = _question

    private val _answer = MutableLiveData<String>()
    val answer: LiveData<String> = _answer

    private val _questionError = MutableLiveData<String?>()
    val questionError: LiveData<String?> = _questionError

    private val _answerError = MutableLiveData<String?>()
    val answerError: LiveData<String?> = _answerError

    private val _saveEnabled = MutableLiveData(false)
    val saveEnabled: LiveData<Boolean> = _saveEnabled

    private var editingFlashcard: Flashcard? = null

    fun setFlashcard(flashcard: Flashcard) {
        editingFlashcard = flashcard
        _question.value = flashcard.question
        _answer.value = flashcard.answer
        validateInputs()
    }

    fun setQuestion(text: String) {
        _question.value = text
        validateInputs()
    }

    fun setAnswer(text: String) {
        _answer.value = text
        validateInputs()
    }

    private fun validateInputs() {
        val questionText = _question.value ?: ""
        val answerText = _answer.value ?: ""

        _questionError.value = when {
            questionText.isBlank() -> "Question cannot be empty"
            questionText.length < 3 -> "Question must be at least 3 characters"
            else -> null
        }

        _answerError.value = when {
            answerText.isBlank() -> "Answer cannot be empty"
            answerText.length < 2 -> "Answer must be at least 2 characters"
            else -> null
        }

        _saveEnabled.value = _questionError.value == null && _answerError.value == null &&
                questionText.isNotBlank() && answerText.isNotBlank()
    }

    fun createOrUpdateFlashcard(): Flashcard {
        val currentTime = System.currentTimeMillis()
        return editingFlashcard?.copy(
            question = _question.value ?: "",
            answer = _answer.value ?: "",
            lastModified = currentTime
        ) ?: Flashcard(
            id = UUID.randomUUID().toString(),
            question = _question.value ?: "",
            answer = _answer.value ?: "",
            createdAt = currentTime,
            lastModified = currentTime
        )
    }
}