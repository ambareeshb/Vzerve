package com.hexeleries.ambareeshb.vzerve.api

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
               @Query("password") password: String): Observable<SignInResponse>

    @GET("signup")
    fun signUp(@Query("email") email: String,
               @Query("password") password: String,
               @Query("mobile") phone: String): rx.Observable<SignUpResponse>

    @GET("get-services-of-location")
    fun getServices(@Query("latitude") latitude: String,
                    @Query("longitude") longitude: String): Observable<ServiceResponse>

    @GET("get-first-question")
    fun getFirstQuestion(@Query("serviceid") serviceId: Long): Observable<QuestionResponse>

    @GET("get-next-question")
    fun getNextQuestion(@Query("questionid") questionId: Long): Observable<QuestionResponse>

    @GET("get-answer-options")
    fun getAnswers(@Query("questionid") questionId: Long): Observable<AnswerResponse>

}
