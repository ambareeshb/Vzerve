package com.hexeleries.ambareeshb.vzerve.dagger.component

import com.hexeleries.ambareeshb.vzerve.ApiInterface
import com.hexeleries.ambareeshb.vzerve.dagger.modules.DatabaseModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.NetworkModule
import com.hexeleries.ambareeshb.vzerve.dagger.modules.UserModule
import com.hexeleries.ambareeshb.vzerve.db.AppDB
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ambareeshb on 25/11/17.
 * The Main component interface for the whole Application.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, DatabaseModule::class))

interface ApplicationComponent {
    fun apiInterface(): ApiInterface
    fun appDb(): AppDB
    fun addUserComponent(userModule: UserModule): UserComponent
}