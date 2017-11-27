package com.hexeleries.ambareeshb.vzerve.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 26/11/17.
 * Module for managing shared preferences.
 */
@Module
class SharedPrefModule(private val activityContext: AppCompatActivity,  private val name: String) {
    @ActivityScope
    @Provides
    fun provideSharedPrefEditor(): SharedPreferences
            = activityContext.getSharedPreferences(name, Context.MODE_PRIVATE)


}