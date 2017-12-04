package com.hexeleries.ambareeshb.vzerve.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by ambareesh on 28/11/17.
 * DB table for users.
 */

@Entity

data class User(
        @SerializedName("id") @PrimaryKey(autoGenerate = true) var id: Long = 1,
        @SerializedName("tocken") @ColumnInfo(name = "user_auth") var userAuth: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("mobile") var phone: String = "",
        @SerializedName("user_id") @ColumnInfo(name = "user_id") var userId: Long = 0,
        @SerializedName("password") @ColumnInfo(name = "password") var password: String = "",
        @SerializedName("signed_in") @ColumnInfo(name = "signed_in") var signedIn: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            signedIn = parcel.readInt() != 0)


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeLong(id)
            writeString(userAuth)
            writeString(email)
            writeLong(userId)
            writeString(phone)
            writeString(password)
            writeInt(if (signedIn) 1 else 0)
        }

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)

        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}
