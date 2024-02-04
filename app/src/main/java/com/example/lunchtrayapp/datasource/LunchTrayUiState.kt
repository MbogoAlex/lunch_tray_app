package com.example.lunchtrayapp.datasource

data class LunchTrayUiState(
    val entree: Pair<Int, Double> = Pair(0, 0.00),
    val sideDish: Pair<Int, Double> = Pair(0, 0.00),
    val accompaniment: Pair<Int, Double> = Pair(0, 0.00),
    val subtotal: Double = 0.00,
    val tax: Double = 0.84,
    val total: Double = 0.00,
)
