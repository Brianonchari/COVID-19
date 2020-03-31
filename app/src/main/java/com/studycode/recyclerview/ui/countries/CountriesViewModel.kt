package com.studycode.recyclerview.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studycode.recyclerview.data.service.repositories.CountriesRepository
import com.studycode.recyclerview.data.service.responses.CountriesItem
import com.studycode.recyclerview.utils.Coroutines
import kotlinx.coroutines.Job

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    private lateinit var job: Job
    private val _countries = MutableLiveData<List<CountriesItem>>()
    val countries: LiveData<List<CountriesItem>>
        get() = _countries

    fun getCountries() {
        job = Coroutines.ioThenMain(
            { repository.getCountriesCases() },
            { _countries.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
