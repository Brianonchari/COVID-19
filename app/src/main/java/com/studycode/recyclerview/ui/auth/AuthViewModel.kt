package com.studycode.recyclerview.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.studycode.recyclerview.data.service.responses.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository:UserRepository):ViewModel() {
    var email: String? = null
    var password: String? = null
    var passwordconfirm:String? = null
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()
    val user by lazy { repository.currentUser() }

    fun login(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()
        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ authListener?.onSuccess() },
                { authListener?.onFailure(it.message!!) })
        disposables.add(disposable)
    }

    fun signup(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        if(password!=passwordconfirm){
            authListener?.onFailure("Passwords did not match")
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun goToSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}