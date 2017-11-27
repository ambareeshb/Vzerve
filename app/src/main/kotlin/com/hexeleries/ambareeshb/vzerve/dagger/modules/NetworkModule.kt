package com.hexeleries.ambareeshb.vzerve.dagger.modules

import com.hexeleries.ambareeshb.vzerve.ApiInterface
import com.hexeleries.ambareeshb.vzerve.utils.RetrofitUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ambareeshb on 25/11/17.
 */
@Module
class NetworkModule {
    //Provide an instance of our ApiInterface
    @Singleton
    @Provides
    fun provideApiInterface():ApiInterface =
            RetrofitUtils.initRetrofit(ApiInterface::class.java)
    }

