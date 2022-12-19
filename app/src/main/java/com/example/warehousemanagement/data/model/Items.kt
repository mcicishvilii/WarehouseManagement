package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*


data class Items(
    val cities:Cities? = null,
){
    data class Cities(
        val capital:Boolean? = null,
        val country:String? = null,
        val name:String? = null,
        val population:Long? = null,
        val regions:Regions? = null,
        val state:String? = null
    ){
        data class Regions(
            val region:String? = null
        )
    }
}


