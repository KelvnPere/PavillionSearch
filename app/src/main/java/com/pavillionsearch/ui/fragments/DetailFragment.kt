package com.pavillionsearch.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pavillionsearch.BaseActivity
import com.pavillionsearch.R
import com.pavillionsearch.data.repository.Repository
import com.pavillionsearch.ui.viewmodel.SearchViewModel
import com.pavillionsearch.ui.viewmodel.SearchViewModelFactory
import com.pavillionsearch.databinding.FragmentDetailBinding
import com.pavillionsearch.model.Item
import com.pavillionsearch.model.SearchResponse
import com.pavillionsearch.utils.AutoClearedValue
import com.pavillionsearch.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

//    private lateinit var viewModel: SearchViewModel
    private val viewModel by viewModels<SearchViewModel>()
    private var binding:FragmentDetailBinding by autoCleared()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = (activity as BaseActivity).viewModel2
        val comics = arguments?.getSerializable("item")
        setup(comics as Item)

    }
    private fun setup(searchResponse: Item) {
        binding.detailLoginTextView.text = searchResponse.login
        binding.detailTypeTextView.text = searchResponse.type
        activity?.let {
            Glide.with(this)
                .load(searchResponse.avatar_url)
                .into(binding.detailAvatarUrlImage)
        }
    }

}