package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by ambareesh on 28/11/17.
 * Database access class for [User] object.
 * Note:All Operations are performed such that only one user exist at a time.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("UPDATE User SET signed_in = 1 WHERE email = :email")
    fun login(email: String)

    @Query("UPDATE User SET signed_in = 0")
    fun logout()

    @Query("SELECT * FROM User WHERE signed_in = 1")
    fun signedInUser(): User

    @Query("SELECT * FROM USER")
    fun getUser(): User


}