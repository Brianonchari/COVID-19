package com.studycode.recyclerview.data.service.repositories

import com.studycode.recyclerview.data.service.Api
import com.studycode.recyclerview.data.service.SafeApiRequest

class CountriesRepository (private val api: Api):SafeApiRequest(){
    suspend fun getCountriesCases() = apiRequest { api.allcountries() }
}