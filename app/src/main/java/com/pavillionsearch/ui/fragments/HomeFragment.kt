package com.pavillionsearch.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavillionsearch.R
import com.pavillionsearch.data.repository.Repository
import com.pavillionsearch.data.viewmodel.SearchViewModel
import com.pavillionsearch.data.viewmodel.SearchViewModelFactory
import com.pavillionsearch.ui.adapter.HomeAdapter
import com.pavillionsearch.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.pavillionsearch.utils.Resource

class HomeFragment : Fragment() {

    lateinit var viewModel: SearchViewModel
    lateinit var homeAdapter: HomeAdapter
    lateinit var search_rvView: RecyclerView
    lateinit var inputText:EditText
    lateinit var searchbtn:Button
    lateinit var progressbarMain:ProgressBar

    companion object {
        const val TAG1:String = "HomeFragment_TAG"
    }

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


        progressbarMain = view.findViewById(R.id.paginationProgressBar1)
        inputText = view.findViewById(R.id.searchTextView)
        searchbtn = view.findViewById(R.id.btn_search)

        searchbtn.setOnClickListener {
            var textt:Editable = inputText.text
        if (textt.toString() == "" || textt.toString().isEmpty() || textt.toString().isBlank())    {
            Toast.makeText(activity,"Kindly type in a text", Toast.LENGTH_SHORT).show()
        }
            else{
            var userInputtedValue:String
            userInputtedValue = textt.toString()
            viewModel.searchNews(userInputtedValue)
            inputText.hideKeyboard()

        }

        }
//        viewModel = (activity as MainActivity).viewModelProviderFactory
        search_rvView = view.findViewById(R.id.search_rv)
        //mRecyclerView = findViewById(R.id.recyclerView);
        setupRecyclerView()
        homeAdapter.setOnItemClickListerner {
            val bundle = Bundle().apply {
                putSerializable("item",it)
            }
            findNavController().navigate(
                R.id.action_homeSearchFragment2_to_detailFragment,
                bundle
            )
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { searchResponse ->
                        homeAdapter.differ.submitList(searchResponse.items.toList())
                        val totalPages = searchResponse.total_count / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.searchGithubPage == totalPages
                        if (isLastPage){
                            search_rvView.setPadding(0, 0, 0, 0)
                        }
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
//                        Log.e(TAG, "An Error Occured: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    private fun hideProgressBar() {
        progressbarMain.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        progressbarMain.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.searchNews(inputText.text.toString())
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        search_rvView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HomeFragment.scrollListener)
        }
    }

}