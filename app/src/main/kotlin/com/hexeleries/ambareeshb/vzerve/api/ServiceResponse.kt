package com.hexeleries.ambareeshb.vzerve.api

import com.hexeleries.ambareeshb.vzerve.Service

/**
 * Created by ambareeshb on 10/12/17.
 * For service response.
 */
data class ServiceResponse(val services: List<Service>) : ApiResponse()

