package com.pavillionsearch.model

data class SearchResponse(
    val items: List<Item>,
    val total_count: Int
)