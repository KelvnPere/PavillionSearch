package com.pavillionsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BaseActivityFragment : AppCompatActivity() {
//
//    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)





    }
}