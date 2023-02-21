package com.pavillionsearch

import com.pavillionsearch.utils.validateURL
import org.junit.Assert
import org.junit.Test

class UrlValidationTest {

    @Test
    fun httpAddressIsValidURL(){
        val url = "http://api.github.com/search/users?q=Tom"
        val isValid = validateURL(url)
        Assert.assertTrue(isValid)
    }



    @Test
    fun httpsAddressIsValidURL(){
        val url = "https://api.github.com/search/users?q=Tom"
        val isValid = validateURL(url)
        Assert.assertTrue(isValid)
    }

    @Test
    fun nullTextIsNotValidURL(){
        val url = "null"
        val isValid = validateURL(url)
        Assert.assertFalse(isValid)
    }

    @Test
    fun nullIsNotValidURL(){
        val url = null
        val isValid = validateURL(url)
        Assert.assertFalse(isValid)
    }

    @Test
    fun emptyStringIsNotValidURL(){
        val url = ""
        val isValid = validateURL(url)
        Assert.assertFalse(isValid)
    }


}