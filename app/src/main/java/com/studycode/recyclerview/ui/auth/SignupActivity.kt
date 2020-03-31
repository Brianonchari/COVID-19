package com.studycode.recyclerview.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.studycode.recyclerview.R
import com.studycode.recyclerview.databinding.ActivitySignupBinding
import com.studycode.recyclerview.utils.snackBar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListener,KodeinAware {

    override val kodein by kodein()
    private val factory:AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val binding:ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        root_layout.snackBar(message)
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
