package com.pavillionsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavillionsearch.data.repository.Repository

class SearchViewModelFactory(
    val newsRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(newsRepository) as T
    }
}