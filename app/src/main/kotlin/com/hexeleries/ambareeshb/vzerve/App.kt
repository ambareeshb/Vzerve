package com.hexeleries.ambareeshb.vzerve

import android.app.Application
import com.hexeleries.ambareeshb.vzerve.dagger.component.ApplicationComponent
import com.hexeleries.ambareeshb.vzerve.dagger.component.DaggerApplicationComponent
import com.hexeleries.ambareeshb.vzerve.dagger.component.UserComponent
import com.hexeleries.ambareeshb.vzerve.dagger.modules.DatabaseModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.NetworkModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.UserModule
import timber.log.Timber

/**
 * Created by ambareeshb on 25/11/17.
 * The main entry point of the Application.
 */
class App : Application() {
private val TAG = "App"
    private lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent =
                DaggerApplicationComponent.builder()
                        .databaseModule(DatabaseModule(this))
                        .networkModule(NetworkModule())
                        .build()
    }
    /**
     * Add user sub component to [applicationComponent].
     */
    val userComponent:UserComponent by lazy {
        Timber.i("$TAG Added UserComponent")
        applicationComponent.addUserComponent(UserModule())
    }

}