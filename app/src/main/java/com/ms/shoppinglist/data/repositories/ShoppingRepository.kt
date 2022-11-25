package com.ms.shoppinglist.data.repositories

import com.ms.shoppinglist.data.db.ShoppingDatabase
import com.ms.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val database: ShoppingDatabase
) {
    fun getAllShoppingItems()=database.getAllShoppingDao().getAllShoppingItems()

    suspend fun insert(item:ShoppingItem)=database.getAllShoppingDao().insert(item)

    suspend fun delete(item:ShoppingItem)=database.getAllShoppingDao().delete(item)


}