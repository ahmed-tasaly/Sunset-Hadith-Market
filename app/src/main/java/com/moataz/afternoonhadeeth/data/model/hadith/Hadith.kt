package com.moataz.afternoonhadeeth.data.model.hadith

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Hadith(var hadith: String?, var authorName: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hadith)
        parcel.writeString(authorName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hadith> {
        override fun createFromParcel(parcel: Parcel): Hadith {
            return Hadith(parcel)
        }

        override fun newArray(size: Int): Array<Hadith?> {
            return arrayOfNulls(size)
        }
    }
}