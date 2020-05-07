package com.studycode.recyclerview.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.studycode.recyclerview.R
import com.studycode.recyclerview.data.service.Api
import com.studycode.recyclerview.data.service.repositories.GlobalRepository
import kotlinx.android.synthetic.main.global_fragment.*

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
        val repository = GlobalRepository(api)
        factory = GlobalViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(GlobalViewModel::class.java)
        viewModel.getGlobalData()
        viewModel.global.observe(viewLifecycleOwner, Observer { cases ->
            global_cases.text = cases.cases.toString()
        })

        viewModel.global.observe(viewLifecycleOwner, Observer { deaths ->
            global_deaths.text = deaths.deaths.toString()
        })

        viewModel.global.observe(viewLifecycleOwner, Observer { recovered ->
            global_recovered.text = recovered.recovered.toString()
        })
    }

}
