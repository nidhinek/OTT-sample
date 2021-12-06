package com.nidhinek.ottminiframe.ui.base

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.ui.PageChangeListener

abstract class BaseActivity : AppCompatActivity(), PageChangeListener {
    private fun addFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        try { // D_LUNA Added try catch to avoid crash  while supportFragmentManager transaction commit
            if (!isFinishing) {
                val ft = supportFragmentManager.beginTransaction()
                ft.setTransition(FragmentTransaction.TRANSIT_NONE)
                ft.add(R.id.frameContainer, fragment, fragment::class.java.name)
                if (addToBackStack) ft.addToBackStack(fragment::class.java.name)
                ft.commit()
            }
        } catch (illegalStateException: IllegalStateException) {
            Log.d("error", "error", illegalStateException)
        }

    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        try { // D_LUNA Added try catch to avoid crash  while supportFragmentManager transaction commit
            if (!isFinishing) {
                val ft = supportFragmentManager.beginTransaction()
                ft.setTransition(FragmentTransaction.TRANSIT_NONE)
                ft.replace(R.id.frameContainer, fragment, fragment::class.java.name)
                if (addToBackStack) ft.addToBackStack(fragment::class.java.name)
                ft.commit()
            }
        } catch (exception: IllegalStateException) {
            Log.d("error", "error", exception)
        }
    }

    override fun startPage(
        fragment: Fragment,
        addToBackStack: Boolean,
        addAsOverlay: Boolean
    ) {
        if (addAsOverlay) addFragment(fragment, addToBackStack)
        else replaceFragment(fragment, addToBackStack)
    }
}
