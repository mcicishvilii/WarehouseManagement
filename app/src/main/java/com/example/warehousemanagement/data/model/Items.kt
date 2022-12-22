package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

    @Parcelize
    data class Items(
        val timestamp: Long = 0,
        val brandAbbreviation:String = "",
        val brand:String = "",
        val boxQuantity:Int = 0,
        val sku:String = "",
        val productName:String = "",
        val clientName:String = "",
    ):Parcelable
//    {
//        @Parcelize
//        data class Regions(
//            val region:List<String>? = null
//        ):Parcelable
//    }



