package com.example.firstassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val recipes: List<FoodItem> = emptyList(),
    val isLoading: Boolean = false
)

class RecipesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _foodList = listOf(
        FoodItem("Black Karaage with Curry Bento", "Crispy black karaage...", R.drawable.food_item_1),
        FoodItem("Seafood Udon", "Quick and easy to prepare...", R.drawable.food_item_2),
        FoodItem("Tonkotsu Ramen", "Noodle soup dish originated in Fukuoka...", R.drawable.food_item_3),
        FoodItem("Takoyaki", "Ball-shaped snack made of batter...", R.drawable.food_item_4),
        FoodItem("Tempura", "Consists of seafood and vegetables...", R.drawable.food_item_5),
        FoodItem("Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken...", R.drawable.food_item_6)
    )

    private var searchJob: Job? = null

    fun loadRecipes() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(isLoading = false, recipes = _foodList) }
        }
    }

    private val _filteredFoodList = MutableStateFlow(_foodList)
    val filteredFoodList: StateFlow<List<FoodItem>> = _filteredFoodList.asStateFlow()

    fun filter(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            _uiState.update { it.copy(isLoading = true, recipes = emptyList()) }
            delay(2000)

            val filteredList = if (query.length < 3) {
                _foodList
            } else {
                _foodList.filter {
                    it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
                }
            }

            _uiState.update { it.copy(isLoading = false, recipes = filteredList) }
        }
    }
}
