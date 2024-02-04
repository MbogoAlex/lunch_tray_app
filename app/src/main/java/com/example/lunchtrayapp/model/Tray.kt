package com.example.lunchtrayapp.model

import androidx.annotation.StringRes

data class Tray(
    @StringRes val foodName: Int,
    @StringRes val foodDesc: Int,
    val price: Double
)
