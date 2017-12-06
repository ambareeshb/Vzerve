package com.hexeleries.ambareeshb.vzerve.api

/**
 * Created by ambareesh on 4/12/17.
 */
data class SignUpResponse(val tocken: String,
                          val email: String,
                          val mobile: String,
                          val user_id: String): ApiResponse()


