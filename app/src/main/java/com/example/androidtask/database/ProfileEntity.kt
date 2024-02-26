package com.example.androidtask.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ProfileEntity")

data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    var id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("email")
    val email: String = "",

    @SerialName("mobile")
    val mobile: String = "",

    @SerialName("gender")
    val gender: String = "",

    )

