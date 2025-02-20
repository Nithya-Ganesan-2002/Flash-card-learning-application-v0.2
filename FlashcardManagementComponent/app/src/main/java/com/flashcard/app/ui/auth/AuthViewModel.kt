package com.flashcard.app.ui.auth

import androidx.lifecycle.ViewModel
import com.flashcard.app.data.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    val authState: StateFlow<AuthState> = authRepository.authState

    suspend fun signInWithEmailPassword(email: String, password: String): Result<Unit> {
        return authRepository.signInWithEmailPassword(email, password)
    }

    suspend fun signUpWithEmailPassword(email: String, password: String): Result<Unit> {
        return authRepository.signUpWithEmailPassword(email, password)
    }

    suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<Unit> {
        return authRepository.signInWithGoogle(account)
    }

    fun signOut() {
        authRepository.signOut()
    }
}