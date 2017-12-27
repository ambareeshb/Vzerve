package com.hexeleries.ambareeshb.vzerve.api

import com.google.gson.annotations.SerializedName

/**
 * Created by ambareeshb on 13/12/17.
 * Model a question.
 */
data class QuestionResponse(@SerializedName("question_id") val questionId: Long
                            , @SerializedName("question") val question: String
                            , @SerializedName("element") val element: String) : ApiResponse()