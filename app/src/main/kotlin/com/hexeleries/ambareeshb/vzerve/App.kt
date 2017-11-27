package com.hexeleries.ambareeshb.vzerve

import android.app.Application
import com.hexeleries.ambareeshb.vzerve.dagger.component.ApplicationComponent
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerApplicationComponent

/**
 * Created by ambareeshb on 25/11/17.
 * The main entry point of the Application.
 */
class App:Application(){

    override fun onCreate() {
        super.onCreate()

    }
    companion object {
        val applicationComponent:ApplicationComponent by lazy {
            DaggerApplicationComponent.create()
        }
    }
}