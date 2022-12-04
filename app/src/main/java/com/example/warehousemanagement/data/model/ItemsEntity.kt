package com.example.warehousemanagement.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "items")
data class ItemsEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId:Int,
    val client:String?,
    val boxNumber:Int?,
    val code:String?,
    val itemName:String?,
    val brandName:String?,
):Parcelable
