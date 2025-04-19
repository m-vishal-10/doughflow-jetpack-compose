package com.vishal.doughflow.viewmodels

import androidx.lifecycle.ViewModel
import com.vishal.doughflow.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class AddScreenState (
    val amount: String = "",
    val recurrence: Recurrence = Recurrence.None,
    val date: LocalDate = LocalDate.now(),
    val note: String = "",
    val category: String? = null
)

class AddViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddScreenState())
    val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

    fun setAmount(amount: String) {
        var parsed = amount.toDoubleOrNull()

        if (amount.isEmpty()) {
            parsed = 0.0
        }

        if (parsed != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    amount = amount.trim().ifEmpty { "0" },
                )
            }
        }
    }
    fun setRecurrence(recurrence: Recurrence){
        _uiState.update { currentState ->
            currentState.copy(recurrence = recurrence)
        }
    }

    fun setDate(date: LocalDate){
        _uiState.update { currentState ->
            currentState.copy(date = date)
        }
    }
    fun setNote(note: String){
        _uiState.update { currentState ->
            currentState.copy(note = note)
        }
    }
    fun setCategory(category: String){
        _uiState.update { currentState ->
            currentState.copy(category = category)
        }
    }

}