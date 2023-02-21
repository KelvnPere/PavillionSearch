package com.pavillionsearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    @Expose
    val items: List<Item>,

    @SerializedName("total_count")
    @Expose
    val total_count: Int
)