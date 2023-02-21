package com.pavillionsearch.data.remote

import com.pavillionsearch.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") searchQuery:String,
                            @Query("per_page")per_page:Int = 10,
                            @Query("page") pageNumber:Int = 1): Response<SearchResponse>
}