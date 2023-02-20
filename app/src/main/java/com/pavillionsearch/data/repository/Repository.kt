package com.pavillionsearch.data.repository

import com.pavillionsearch.api.RetrofitInstance

class Repository {

    suspend fun searchUsers(searchQuery:String, pageNumber:Int)=
        RetrofitInstance.api.searchUsers(searchQuery, pageNumber)
}