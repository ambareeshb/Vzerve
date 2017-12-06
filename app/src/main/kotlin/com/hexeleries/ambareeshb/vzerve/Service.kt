package com.hexeleries.ambareeshb.vzerve

import android.arch.persistence.room.Entity

/**
 * Created by ambareeshb on 05/12/17.
 * Describe a service.
 */
@Entity
data class Service(val serviceid:Long,
                   val servicename:String,
                   val serviceicon:String,
                   val spotservice:Int,
                   val is_bid_service:Boolean)
