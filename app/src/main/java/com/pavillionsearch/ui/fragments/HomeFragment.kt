package com.pavillionsearch.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavillionsearch.MainActivity
import com.pavillionsearch.R
import com.pavillionsearch.data.repository.Repository
import com.pavillionsearch.data.viewmodel.SearchViewModel
import com.pavillionsearch.data.viewmodel.SearchViewModelFactory
import com.pavillionsearch.ui.adapter.HomeAdapter
import com.pavillionsearch.utils.Resource

class HomeFragment : Fragment() {

    lateinit var viewModel: SearchViewModel
    lateinit var newsAdapter: HomeAdapter
    lateinit var search_rvView: RecyclerView
    lateinit var inputText:EditText
    lateinit var searchbtn:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchRepository = Repository()
        val viewModelProviderFactory = SearchViewModelFactory(searchRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel::class.java)

        inputText = view.findViewById(R.id.searchTextView)
        searchbtn = view.findViewById(R.id.btn_search)

        inputText
//        viewModel = (activity as MainActivity).viewModelProviderFactory
        search_rvView = view.findViewById(R.id.search_rv)
        //mRecyclerView = findViewById(R.id.recyclerView);


        viewModel.searchNews("TOM")
        setupRecyclerView()

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
//                    hideProgressBar()
                    response.data?.let { searchResponse ->
                        newsAdapter.differ.submitList(searchResponse.items)
                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
//                        Log.e(TAG, "An Error Occured: $message")
                    }
                }
                is Resource.Loading ->{
//                    showProgressBar()
                }
            }

        })
    }

    private fun setupRecyclerView() {
        newsAdapter = HomeAdapter()
        search_rvView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}