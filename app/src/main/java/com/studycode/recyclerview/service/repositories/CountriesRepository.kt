package com.studycode.recyclerview.service.repositories

import com.studycode.recyclerview.service.Api
import com.studycode.recyclerview.service.SafeApiRequest

class CountriesRepository (private val api: Api):SafeApiRequest(){

    suspend fun getCountriesCases() = apiRequest { api.allcountries() }


}