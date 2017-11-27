package com.hexeleries.ambareeshb.vzerve.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ambareeshb on 26/11/17.
 * A User.
 */
data class User(private val userAuth:String,
                private val email:String,
                private val phone:Int,
                private val userId:Long): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong())


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeString(userAuth)
            writeString(email)
            writeLong(userId)
            writeInt(phone)
        }

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
