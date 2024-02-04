package com.example.lunchtrayapp.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.lunchtrayapp.datasource.LunchTrayUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LunchTrayViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LunchTrayUiState())
    val uiState: StateFlow<LunchTrayUiState> = _uiState.asStateFlow()

    fun addEntry(@StringRes name: Int, price: Double) {
        Log.i("MYTAG", "ADDING $name OF PRICE: $price")
        _uiState.update {
            it.copy(
                entree = Pair(name, price)
            )
        }
    }

    fun addSideDish(@StringRes name: Int, price: Double) {
        Log.i("MYTAG", "ADDING $name OF PRICE: $price")
        _uiState.update {
            it.copy(
                sideDish = Pair(name, price)
            )
        }
    }

    fun addAccompaniment(@StringRes name: Int, price: Double) {
        Log.i("MYTAG", "ADDING $name OF PRICE: $price")
        _uiState.update {
            it.copy(
                accompaniment = Pair(name, price)
            )
        }
        calculateTotal()
    }

    private fun calculateTotal() {
        val tax = uiState.value.tax
        val entreePrice = uiState.value.entree.second
        val sideDishPrice = uiState.value.sideDish.second
        val accompanimentPrice = uiState.value.accompaniment.second
        val subtotal = entreePrice + sideDishPrice + accompanimentPrice
        val total = subtotal + tax
        _uiState.update {
            it.copy(
                subtotal = subtotal,
                tax = tax,
                total = total
            )
        }
    }
}