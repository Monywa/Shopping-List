package com.ms.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ms.shoppinglist.data.db.entities.ShoppingItem


@Dao
interface ShoppingDao {

    @Query("SELECT * FROM SHOPPING_ITEMS")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

}