package com.studycode.recyclerview.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studycode.recyclerview.service.repositories.CountriesRepository

@Suppress("UNCHECKED_CAST")
class CountriesViewModelFactory (private val repository: CountriesRepository):ViewModelProvider.NewInstanceFactory(){
     override fun <T:ViewModel?> create(modelClass:Class<T>):T{
         return CountriesViewModel(repository) as T
     }
}