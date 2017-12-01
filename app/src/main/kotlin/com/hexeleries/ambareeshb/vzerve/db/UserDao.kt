package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by ambareesh on 28/11/17.
 * Database access class for [User] object.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("UPDATE User SET phone = :phone WHERE email = :email")
    fun updatePhone(phone: String, email: String)

    @Query("SELECT * FROM User WHERE signed_in = 1")
    fun signedInUser(): User
}