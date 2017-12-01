package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by ambareesh on 28/11/17.
 * DB table for users.
 */

@Entity

data class User(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @JsonProperty("user_auth") @ColumnInfo(name = "user_auth") val userAuth: String = "",
        @JsonProperty("email") val email: String,
        @JsonProperty("phone") val phone: String = "",
        @JsonProperty("user_id") @ColumnInfo(name = "user_id") val userId: Long = 0,
        @ColumnInfo(name = "signed_in") val signedIn: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            signedIn = parcel.readInt() != 0)


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeLong(id)
            writeString(userAuth)
            writeString(email)
            writeLong(userId)
            writeString(phone)
            writeInt(if (signedIn) 1 else 0)
        }

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)

        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}
