package com.pavillionsearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("avatar_url")
    @Expose
    val avatar_url: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("url")
    @Expose
    val url: String
): Serializable