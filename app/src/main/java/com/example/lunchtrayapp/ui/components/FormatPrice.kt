package com.example.lunchtrayapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.NumberFormat

@Composable
fun FormatPrice(price: String, modifier: Modifier = Modifier) {
    val cost = NumberFormat.getCurrencyInstance().format(price.toDouble())
    Text(
        modifier = modifier,
        text = cost
    )
}