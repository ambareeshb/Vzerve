package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.hexeleries.ambareeshb.vzerve.Service

/**
 * Created by ambareesh on 28/11/17.
 * DB for the entire Application.
 */
@Database(entities = arrayOf(User::class, Service::class), version = 4, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serviceDao():ServiceDao
}
