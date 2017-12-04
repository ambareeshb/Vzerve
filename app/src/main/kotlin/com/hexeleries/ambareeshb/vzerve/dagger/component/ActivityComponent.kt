package com.hexeleries.ambareeshb.vzerve.dagger.component

import com.hexeleries.ambareeshb.vzerve.dagger.modules.FragmentModule
import com.hexeleries.ambareeshb.vzerve.dagger.scopes.ActivityScope
import com.hexeleries.ambareeshb.vzerve.ui.activities.SignUpActivity
import com.hexeleries.ambareeshb.vzerve.ui.activities.SplashActivity
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import dagger.Component

/**
 * Created by ambareeshb on 26/11/17.
 * Dependency for each component.
 */
@Component(modules = arrayOf(FragmentModule::class))
@ActivityScope
interface ActivityComponent {
    fun fragmentUtils(): FragmentUtils

    fun inject(activity: SplashActivity)
    fun inject(activity: SignUpActivity)
}
