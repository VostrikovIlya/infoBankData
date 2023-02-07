package com.example.myapplication.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var bin: String? = "",
    @Embedded(prefix = "bank")
    var bank: Bank? = Bank(),
    var brand: String? = "",
    @Embedded(prefix = "country")
    var country: Country? = Country(),
    @Embedded(prefix = "number")
    var number: Number? = Number(),
    var prepaid: Boolean? = false,
    var scheme: String? = "",
    var type: String? = ""
)