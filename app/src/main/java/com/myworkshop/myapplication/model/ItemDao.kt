package com.myworkshop.myapplication.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Query("select * from item")
    fun getAllItem(): Flow<List<Item>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(item: Item)
}