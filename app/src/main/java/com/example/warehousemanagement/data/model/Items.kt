package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Items(
//    val timeStamp:@RawValue FieldValue? = null,
    val client:String? = null,
    val boxNumber:Int? = null,
    val code:String? = null,
    val itemName:String? = null,
    val brandName:String? = null,
):Parcelable


val itemsList = mutableListOf<Items>()
