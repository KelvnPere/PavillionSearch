package com.pavillionsearch.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavillionsearch.data.repository.Repository
import com.pavillionsearch.model.SearchResponse
import com.pavillionsearch.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(val searchRepository: Repository): ViewModel() {


    val searchNews: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchNewsResponse: SearchResponse? = null

    fun searchNews(searchQuery:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val respone = searchRepository.searchUsers(searchQuery)
        searchNews.postValue(handleSearchResponse(respone))
    }

    private fun handleSearchResponse(response: Response<SearchResponse>) : Resource<SearchResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                if(searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.items
                    val newArticles = resultResponse.items
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
