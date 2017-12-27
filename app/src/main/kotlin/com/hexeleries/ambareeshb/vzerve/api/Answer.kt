package com.hexeleries.ambareeshb.vzerve.api

/**
 * Created by ambareeshb on 24/12/17.
 * Answer model.
 */
data class Answer(val answer_id: String,
                  val answer_value: String,
                  val next_question: Long,
                  var selected: Boolean

)
