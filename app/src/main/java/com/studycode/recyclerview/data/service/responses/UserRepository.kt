package com.studycode.recyclerview.data.service.responses

import com.studycode.recyclerview.data.firebase.FirebaseSource

class UserRepository(private val firebaseSource: FirebaseSource) {
    fun login(email:String,password:String) = firebaseSource.login(email, password)
    fun register(email: String,password: String)=firebaseSource.register(email, password)
    fun currentUser() = firebaseSource.currentUser()
    fun logout() = firebaseSource.logout()
}