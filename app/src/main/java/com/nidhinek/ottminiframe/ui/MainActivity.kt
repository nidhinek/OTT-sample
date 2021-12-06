package com.nidhinek.ottminiframe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPage(HomeFragment.newInstance(),addToBackStack = true)
    }
}
