package com.example.warehousemanagement.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.lang.reflect.Field
import java.util.*

@Parcelize
data class Items(
//    val itemId:Int,

//    val timeStamp:@RawValue FieldValue? = null,
    val client:String? = null,
    val boxNumber:Int? = null,
    val code:String? = null,
    val itemName:String? = null,
    val brandName:String? = null,
):Parcelable
