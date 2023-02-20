package com.pavillionsearch.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavillionsearch.MainActivity
import com.pavillionsearch.R
import com.pavillionsearch.data.viewmodel.SearchViewModel
import com.pavillionsearch.ui.adapter.HomeAdapter
import com.pavillionsearch.utils.Resource
import kotlinx.coroutines.Job


class HomeSearchFragment : Fragment(R.layout.fragment_home_search) {

    lateinit var viewModel: SearchViewModel
    lateinit var newsAdapter: HomeAdapter
    lateinit var search_rvView:RecyclerView
//    lateinit var search_rvView: RecyclerView
//    lateinit var progressbarMain: ProgressBar
    val TAG = "SearchNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_rvView.findViewById<RecyclerView>(R.id.search_rv)
//        progressbarMain.findViewById<ProgressBar>(R.id.paginationProgressBar)
//        progressbarMain = ProgressBar()
//        viewModel = (activity as MainActivity).viewModel
//        setupRecyclerView()

        var job: Job? = null
//        viewModel.searchNews("TOM")

//        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
//            when(response){
//                is Resource.Success -> {
////                    hideProgressBar()
//                    response.data?.let { searchResponse ->
//                        newsAdapter.differ.submitList(searchResponse.items)
//                    }
//                }
//
//                is Resource.Error -> {
////                    hideProgressBar()
//                    response.message?.let { message ->
//                        Log.e(TAG, "An Error Occured: $message")
//                    }
//                }
//                is Resource.Loading ->{
////                    showProgressBar()
//                }
//            }
//
//        })

    }

//    private fun hideProgressBar() {
//        progressbarMain.visibility = View.INVISIBLE
//    }
//
//    private fun showProgressBar() {
//        progressbarMain.visibility = View.VISIBLE
//    }
//
//    private fun setupRecyclerView() {
//        newsAdapter = HomeAdapter()
//        search_rvView.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }
}