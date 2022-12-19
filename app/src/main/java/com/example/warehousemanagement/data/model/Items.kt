package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Items(
    val client:String = "",
    val boxNumber:Int = 0,
    val code:String = "",
    val itemName:String = "",
    val brandName:String = ""
):Parcelable


