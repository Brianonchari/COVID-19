package com.studycode.recyclerview.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studycode.recyclerview.data.service.responses.UserRepository
@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory (private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}