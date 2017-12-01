package com.hexeleries.ambareeshb.vzerve.dagger.modules

import com.hexeleries.ambareeshb.vzerve.dagger.scopes.UserScope
import com.hexeleries.ambareeshb.vzerve.db.AppDB
import com.hexeleries.ambareeshb.vzerve.db.UserDao
import dagger.Module
import dagger.Provides

/**
 * Created by ambareesh on 28/11/17.
 * User Module.
 */
@Module
class UserModule {
    @Provides
    @UserScope
    fun provideUserDao(appDB: AppDB): UserDao = appDB.userDao()

}
