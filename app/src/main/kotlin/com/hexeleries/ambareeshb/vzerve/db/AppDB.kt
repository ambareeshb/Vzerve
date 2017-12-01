package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by ambareesh on 28/11/17.
 * DB for the entire Application.
 */
@Database(entities = arrayOf(User::class),version = 2,exportSchema = false)
abstract class AppDB:RoomDatabase(){
    abstract fun userDao():UserDao
}
