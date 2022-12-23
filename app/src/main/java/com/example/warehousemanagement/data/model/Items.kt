package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

    @Parcelize
    data class Items(
        val ltd:String = "",
        val brandAbbreviation:String = "",
        val brand:String = "",
        val productName:String = "",
        val clientName:String = "",
        val coworker:String = "",
        val status:String = "",
        val boxNumber:Int = 0,
        val timestamp: String = "",
    ):Parcelable



