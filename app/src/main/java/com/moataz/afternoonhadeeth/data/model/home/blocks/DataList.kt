package com.moataz.afternoonhadeeth.data.model.home.blocks

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class DataList(
    var text: String?,
    var textWar: String?,
    var textWarDetails: String?,
    var url: String?,
    var image: String?,
    var icon: String?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeString(textWar)
        parcel.writeString(textWarDetails)
        parcel.writeString(url)
        parcel.writeString(image)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataList> {
        override fun createFromParcel(parcel: Parcel): DataList {
            return DataList(parcel)
        }

        override fun newArray(size: Int): Array<DataList?> {
            return arrayOfNulls(size)
        }
    }
}