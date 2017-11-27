package com.hexeleries.ambareeshb.vzerve.dagger.component

import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.ActivityScope
import com.hexeleries.ambareeshb.vzerve.dagger.modules.FragmentModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.SharedPrefModule
import com.hexeleries.ambareeshb.vzerve.ui.SplashActivity
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import dagger.Component

/**
 * Created by ambareeshb on 26/11/17.
 * Dependency for each component.
 */
@Component(modules = arrayOf(ActivityModule::class,SharedPrefModule::class))
@ActivityScope
interface ActivityComponent {
    fun activity():AppCompatActivity
    fun fragmentUtils(): FragmentUtils

    fun inject(activity: SplashActivity)
}
