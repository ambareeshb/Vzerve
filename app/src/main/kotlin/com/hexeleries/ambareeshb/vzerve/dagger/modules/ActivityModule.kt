package com.hexeleries.ambareeshb.vzerve.dagger.modules

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 27/11/17.
 * For the life scope  of an Activity.
 */

@Module(includes = arrayOf(FragmentModule::class))
class ActivityModule(private val activity: AppCompatActivity) {
    @Provides
    @ActivityScope
    fun provideActivityContext(): AppCompatActivity = activity
}
