package com.ms.shoppinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ms.shoppinglist.data.db.entities.ShoppingItem
import com.ms.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository,
) : ViewModel() {

    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun insert(item: ShoppingItem) =
        viewModelScope.launch(Dispatchers.IO) { repository.insert(item) }

    fun delete(item: ShoppingItem) =
        viewModelScope.launch(Dispatchers.IO) { repository.delete(item) }


//    companion object {
//
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
//
//                return ShoppingViewModel(
//                    (application as BaseApplication).rep,
//                    savedStateHandle
//                ) as T
//            }
//        }
//    }

}

class ShoppingViewModelProviderFactory(private val repository: ShoppingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShoppingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



