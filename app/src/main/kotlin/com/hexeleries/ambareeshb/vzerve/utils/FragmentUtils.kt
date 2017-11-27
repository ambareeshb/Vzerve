package com.hexeleries.ambareeshb.vzerve.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by ambareeshb on 16/09/17.
 */
class FragmentUtils(fragmentManager: FragmentManager) {
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

    /**
     * Add a given fragment to the given container.
     * @param containerId of fragment
     * *
     * @param fragment we are interested.
     * *
     * @return this class
     */
    fun add(containerId: Int, fragment: Fragment): FragmentUtils {
        fragmentTransaction.add(containerId, fragment)
        return this
    }

    /**
     * Add a given fragment to the given container.
     * @param containerId of fragment
     * *
     * @param fragment we are interested.
     * *
     * @return this class
     */
    fun replace(containerId: Int, fragment: Fragment): FragmentUtils {
        fragmentTransaction.replace(containerId, fragment)
        return this
    }

    /**
     * Commit a fragment transaction.
     * @return
     */
    fun commit(): Int {
        return this.fragmentTransaction.commit()
    }

    /**
     * Set custom transition for fragment.
     * @param start transition resource id.
     * *
     * @param end transition resource id.
     */
    fun setTransition(start: Int, end: Int): FragmentUtils {
        fragmentTransaction.setCustomAnimations(start, end)
        return this
    }

    /**

     * @param add
     */
    fun addToBackStack(add: Boolean, tag: String): FragmentUtils {
        if (add) fragmentTransaction.addToBackStack(tag)
        return this

    }
}
