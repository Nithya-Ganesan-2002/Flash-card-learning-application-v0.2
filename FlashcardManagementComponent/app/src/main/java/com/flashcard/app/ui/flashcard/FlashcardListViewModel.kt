package com.flashcard.app.ui.flashcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flashcard.app.data.model.Flashcard

class FlashcardListViewModel : ViewModel() {
    private val _flashcards = MutableLiveData<List<Flashcard>>(emptyList())
    val flashcards: LiveData<List<Flashcard>> = _flashcards

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadFlashcards()
    }

    private fun loadFlashcards() {
        _isLoading.value = true
        // TODO: Implement flashcard loading from repository
        // For now, just simulate loading
        _isLoading.value = false
    }

    fun refreshFlashcards() {
        loadFlashcards()
    }
}