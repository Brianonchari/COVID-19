package com.studycode.recyclerview.data.service.repositories

import com.studycode.recyclerview.data.service.Api
import com.studycode.recyclerview.data.service.SafeApiRequest

class GlobalRepository(private val api: Api) : SafeApiRequest() {
    suspend fun getGlobalData() = apiRequest { api.getAll() }

}