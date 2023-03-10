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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavillionsearch.BaseActivity
import com.pavillionsearch.R
import com.pavillionsearch.ui.viewmodel.SearchViewModel
import com.pavillionsearch.ui.adapter.HomeAdapter

import com.pavillionsearch.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()
   // lateinit var viewModel: SearchViewModel
    lateinit var homeAdapter: HomeAdapter
    lateinit var search_rvView: RecyclerView
    lateinit var inputText:EditText
    lateinit var searchbtn:Button
    lateinit var progressbarMain:ProgressBar

    companion object {
        const val TAG1:String = "HomeFragment_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        progressbarMain = view.findViewById(R.id.paginationProgressBar1)
        inputText = view.findViewById(R.id.searchTextView)
        searchbtn = view.findViewById(R.id.btn_search)
        var job: Job? = null


        searchbtn.setOnClickListener {
           // showProgressBar()
            job?.cancel()
            var textt:Editable = inputText.text
            job = MainScope().launch {
                delay(500L)
                if (textt.toString() == "" || textt.toString().isEmpty() || textt.toString().isBlank())    {
                    Toast.makeText(activity,"Kindly type in a text", Toast.LENGTH_SHORT).show()
                }
                else{
                    var userInputtedValue:String
                    userInputtedValue = textt.toString()
                    viewModel.searchNews(userInputtedValue)
                  // hideProgressBar()
                    inputText.hideKeyboard()

                }
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

    }

    private fun showProgressBar() {
        progressbarMain.visibility = View.VISIBLE

    }


    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        search_rvView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}