package com.hexeleries.ambareeshb.vzerve.api

/**
 * Created by ambareesh on 4/12/17.
 * ApiResponse.
 */
open class ApiResponse {
    var response_type: String = ""
    var response_text: String = "Error"
    var response_code: String = ApiConstants.STATUS_CODE_ERROR
}
