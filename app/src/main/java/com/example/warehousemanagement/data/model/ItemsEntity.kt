package com.example.warehousemanagement.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "artists")
data class ItemsEntity(
    @PrimaryKey(autoGenerate = true)
    val ArtistId:Int,
    val Name:String,
):Parcelable
