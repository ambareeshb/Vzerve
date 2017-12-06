package com.hexeleries.ambareeshb.vzerve.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.hexeleries.ambareeshb.vzerve.Service

/**
 * Created by ambareeshb on 05/12/17.
 * Access to Service DB.
 */
@Dao
interface ServiceDao {
    @Insert
    fun insertServiceList(services: List<Service>)

    @Query("SELECT * FROM Service")
    fun getAllServices(): LiveData<List<Service>>
}
