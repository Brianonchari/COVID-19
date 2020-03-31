package com.studycode.recyclerview.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studycode.recyclerview.data.service.repositories.GlobalRepository
import com.studycode.recyclerview.data.service.responses.AllcasesResponse
import com.studycode.recyclerview.utils.Coroutines
import kotlinx.coroutines.Job

class GlobalViewModel(private val repository: GlobalRepository) : ViewModel() {
    private lateinit var job: Job
    private val _global = MutableLiveData<AllcasesResponse>()
    val global: LiveData<AllcasesResponse>
        get() = _global

    fun getGlobalData() {
        job = Coroutines.ioThenMain(
            { repository.getGlobalData() },
            { _global.value = it }
        )
    }
    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
