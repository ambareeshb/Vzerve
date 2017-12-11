package com.hexeleries.ambareeshb.vzerve.dagger.modules

import com.hexeleries.ambareeshb.vzerve.api.ApiInterface
import com.hexeleries.ambareeshb.vzerve.dagger.scopes.UserScope
import com.hexeleries.ambareeshb.vzerve.db.AppDB
import com.hexeleries.ambareeshb.vzerve.db.ServiceDao
import com.hexeleries.ambareeshb.vzerve.db.UserDao
import com.hexeleries.ambareeshb.vzerve.repositories.HomeRepo
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

    @Provides
    @UserScope
    fun provideServiceDao(appDB: AppDB) = appDB.serviceDao()

    @Provides
    @UserScope
    fun provideUserRepo(apiInterface: ApiInterface, serviceDao: ServiceDao) = HomeRepo(apiInterface, serviceDao)

}
