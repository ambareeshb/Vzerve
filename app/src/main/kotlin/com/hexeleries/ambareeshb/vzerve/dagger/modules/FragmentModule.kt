package com.hexeleries.ambareeshb.vzerve.dagger.modules

import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.utils.FragmentUtils
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 27/11/17.
 * A fragment utils provider module.
 */
@Module
class FragmentModule(private val activityContext: AppCompatActivity){
    @Provides
    @ActivityScope
    fun provideFragmentUtils(): FragmentUtils = FragmentUtils(activityContext.supportFragmentManager)
}
