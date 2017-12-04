package com.hexeleries.ambareeshb.vzerve

import com.hexeleries.ambareeshb.vzerve.db.User
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by ambareeshb on 25/11/17.
 * For all API related work.
 */
interface ApiInterface {

    @GET("signin")
    fun signIn(@Query("email") email: String,
               @Query("password") password: String):Observable<SignInResponse>

    @GET("signup")
    fun signUp(@Query("email") email: String,
               @Query("password") password: String,
               @Query("mobile") phone: String): rx.Observable<SignUpResponse>

    @GET("get-services-of-location")
    fun getServices(@Query("lattitude") latitude: String,
                    @Query("longitude") longitude: String)

    @GET("get-first-question")
    fun getFirstQuestion(@Query("serviceid") serviceid: Long)
}
