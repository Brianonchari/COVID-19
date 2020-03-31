package com.studycode.recyclerview.ui.global

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studycode.recyclerview.data.service.repositories.GlobalRepository

@Suppress("UNCHECKED_CAST")
class GlobalViewModelFactory (private val repository: GlobalRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T: ViewModel?> create(modelClass:Class<T>):T{
        return GlobalViewModel(repository) as T
    }
}