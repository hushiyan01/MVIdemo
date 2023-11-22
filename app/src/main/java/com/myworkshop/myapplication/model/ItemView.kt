package com.myworkshop.myapplication.model

import android.os.Parcel
import android.os.Parcelable

data class ItemView(
    val id:Int=0,

    val name: String?,

    val description: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    companion object CREATOR : Parcelable.Creator<ItemView> {
        override fun createFromParcel(parcel: Parcel): ItemView {
            return ItemView(parcel)
        }

        override fun newArray(size: Int): Array<ItemView?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(name)
        parcel.writeInt(id)
        parcel.writeString(description)
    }
}