package com.hexeleries.ambareeshb.vzerve.dagger.modules

import android.arch.persistence.room.Room
import com.hexeleries.ambareeshb.vzerve.App
import com.hexeleries.ambareeshb.vzerve.db.AppDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ambareesh on 28/11/17.
 * For DB handling.
 */
@Module
class DatabaseModule(val app: App) {
    @Provides
    @Singleton
    fun provideDB():AppDB = Room.databaseBuilder(app, AppDB::class.java, "VZERVE_DB").build()
}
