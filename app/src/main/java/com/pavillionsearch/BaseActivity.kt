package com.pavillionsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.pavillionsearch.data.repository.Repository
import com.pavillionsearch.data.viewmodel.SearchViewModel
import com.pavillionsearch.data.viewmodel.SearchViewModelFactory

class BaseActivity : AppCompatActivity() {
    lateinit var navController: NavController
//
    lateinit var viewModel2: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)

        val repository = Repository()
        val viewModelProviderFactory = SearchViewModelFactory(repository)
        viewModel2 = ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel::class.java)

      val navHostFragment= supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(findViewById(R.id.toolbar_activity))
        findViewById<Toolbar>(R.id.toolbar_activity)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}