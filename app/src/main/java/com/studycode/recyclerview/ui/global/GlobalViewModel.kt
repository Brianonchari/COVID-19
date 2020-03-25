package com.studycode.recyclerview.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studycode.recyclerview.service.repositories.GlobalRepository
import com.studycode.recyclerview.service.responses.AllcasesResponse
import com.studycode.recyclerview.service.responses.CountriesItem
import com.studycode.recyclerview.utils.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalViewModel(private val repository: GlobalRepository) : ViewModel() {
    val global by lazyDeferred {repository.getGlobaldata()}
}
