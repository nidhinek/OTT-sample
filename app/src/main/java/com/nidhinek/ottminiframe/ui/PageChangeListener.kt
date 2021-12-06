package com.nidhinek.ottminiframe.ui

import androidx.fragment.app.Fragment

interface PageChangeListener {
    fun startPage(
        fragment: Fragment,
        addToBackStack: Boolean = false,
        addAsOverlay: Boolean = false
    )
}
