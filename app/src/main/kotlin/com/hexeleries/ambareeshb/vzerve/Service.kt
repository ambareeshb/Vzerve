package com.hexeleries.ambareeshb.vzerve

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ambareeshb on 05/12/17.
 * Describe a service.
 */
@Entity
data class Service(@PrimaryKey val serviceid: Long,
                   val servicename: String,
                   val serviceicon: String,
                   val spotservice: Int,
                   val is_bid_service: Int)
