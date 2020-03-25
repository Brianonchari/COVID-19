package com.studycode.recyclerview.ui.global

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.get

import com.studycode.recyclerview.R
import com.studycode.recyclerview.databinding.GlobalFragmentBinding
import com.studycode.recyclerview.service.Api
import com.studycode.recyclerview.service.repositories.GlobalRepository
import kotlinx.android.synthetic.main.global_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlobalFragment : Fragment() {

    private lateinit var factory: GlobalViewModelFactory
    private lateinit var viewModel: GlobalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.global_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = Api()
        val repository =GlobalRepository(api)
        factory = GlobalViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(GlobalViewModel::class.java)
//        viewModel.getGlobaldata().toString(
        GlobalScope.launch(Dispatchers.Main){
            cases.text = api.getAll().body().toString()

            if(api.getAll().isSuccessful){
            }

        }


//        viewModel.global.observe(viewLifecycleOwner, Observer { cases->
//
//        })

    }

}
