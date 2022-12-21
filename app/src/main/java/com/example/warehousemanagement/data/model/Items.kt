package com.example.warehousemanagement.data.model

import android.os.Parcelable
import com.google.firebase.firestore.FieldValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Items(
    val cities:Cities? = null,
):Parcelable{
    @Parcelize
    data class Cities(
        val capital:Boolean = false,
        val country:String = "",
        val name:String = "",
        val population:Long = 0,
        val regions:Regions? = null,
        val state:String = ""
    ):Parcelable{
        @Parcelize
        data class Regions(
            val region:List<String>? = null
        ):Parcelable
    }
}


