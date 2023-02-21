package com.pavillionsearch.utils

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

fun validateURL(url: String?): Boolean{

    val urlSafe: String = url ?: ""
    return urlSafe.toHttpUrlOrNull() != null

}