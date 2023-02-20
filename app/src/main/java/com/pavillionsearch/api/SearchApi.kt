package com.pavillionsearch.api

import com.pavillionsearch.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") searchQuery:String,
                            @Query("page") pageNumber:Int = 1): Response<SearchResponse>
}