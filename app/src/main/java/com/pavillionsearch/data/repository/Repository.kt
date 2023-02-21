package com.pavillionsearch.data.repository

import com.pavillionsearch.data.remote.RetrofitInstance
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val retrofitInstance: RetrofitInstance
) {
    suspend fun searchUsers(searchQuery:String)=
        retrofitInstance.getSearch(searchQuery)
}