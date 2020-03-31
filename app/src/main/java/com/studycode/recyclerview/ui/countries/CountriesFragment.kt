package com.studycode.recyclerview.ui.countries

import android.os.Bundle
import android.view.*
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.studycode.recyclerview.R
import com.studycode.recyclerview.adapters.CountriesAdapter
import com.studycode.recyclerview.data.service.Api
import com.studycode.recyclerview.data.service.repositories.CountriesRepository
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
        viewModel = ViewModelProviders.of(this, factory).get(CountriesViewModel::class.java)
        viewModel.getCountries()
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            recycler_view_data.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CountriesAdapter(countries)
            }
        })

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
        }
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val search_view = searchItem?.actionView as SearchView
        search_view.queryHint = getString(R.string.search)
        search_view.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1
    }
}
