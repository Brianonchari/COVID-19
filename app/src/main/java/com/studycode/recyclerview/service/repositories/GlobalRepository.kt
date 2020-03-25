package com.studycode.recyclerview.service.repositories

import androidx.lifecycle.MutableLiveData
import com.studycode.recyclerview.service.Api
import com.studycode.recyclerview.service.SafeApiRequest
import com.studycode.recyclerview.service.responses.AllcasesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalRepository(private val api: Api) : SafeApiRequest() {
    private val global = MutableLiveData<AllcasesResponse>()
    fun getGlobaldata() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = apiRequest { api.getAll() }
            global.postValue(response)
        }
    }
}