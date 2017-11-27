package com.hexeleries.ambareeshb.vzerve.utils

import com.hexeleries.ambareeshb.vzerve.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by ambareeshb on 16/09/17.
 */
object RetrofitUtils {
    private var retrofit: Retrofit? = null


    fun <T> initRetrofit(apiInterface: Class<T>): T {
        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()
        }
        return retrofit!!.create(apiInterface)
    }

    /**
     * Ok http client with logging interceptor.
     * @return
     */
    val client: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            return client
        }
}