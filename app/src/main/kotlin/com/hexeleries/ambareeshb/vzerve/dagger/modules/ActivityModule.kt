package com.hexeleries.ambareeshb.vzerve.dagger.modules

import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.vzerve.dagger.scopes.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 27/11/17.
 * For the life scope  of an Activity.
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @Provides
    @ActivityScope
    fun provideActivityContext(): AppCompatActivity = activity
}
