package com.nidhinek.ottminiframe.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPage(HomeFragment.newInstance(), addToBackStack = true)
        viewModel.title.observe(this) {
            supportActionBar?.title = it
        }
    }
}
