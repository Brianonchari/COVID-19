package com.studycode.recyclerview.ui.countries

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.studycode.recyclerview.R
import com.studycode.recyclerview.adapters.CountriesAdapter
import com.studycode.recyclerview.service.Api
import com.studycode.recyclerview.service.repositories.CountriesRepository
import kotlinx.android.synthetic.main.countries_fragment.*

class CountriesFragment : Fragment() {



    private lateinit var factory: CountriesViewModelFactory
    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = Api()
        val repository = CountriesRepository(api)
        factory = CountriesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(CountriesViewModel::class.java)
        viewModel.getCountries()
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            recycler_view_data.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CountriesAdapter(countries)
            }
        })
    }



}
