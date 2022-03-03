package com.moataz.afternoonhadeeth.data.model.videos.top

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Data(
    val details: String?,
    val image: String?,
    val text: String?,
    val times: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(details)
        parcel.writeString(image)
        parcel.writeString(text)
        parcel.writeString(times)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}