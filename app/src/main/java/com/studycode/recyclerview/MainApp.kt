package com.studycode.recyclerview

import android.app.Application
import com.studycode.recyclerview.data.firebase.FirebaseSource
import com.studycode.recyclerview.data.service.Api
import com.studycode.recyclerview.data.service.repositories.CountriesRepository
import com.studycode.recyclerview.data.service.responses.UserRepository
import com.studycode.recyclerview.ui.auth.AuthViewModelFactory
import com.studycode.recyclerview.ui.countries.CountriesViewModelFactory
import com.studycode.recyclerview.ui.global.GlobalViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApp :Application(),KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApp))

        bind() from singleton { Api() }
        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { GlobalViewModelFactory(instance()) }
        bind() from provider { CountriesViewModelFactory(instance()) }
    }
}