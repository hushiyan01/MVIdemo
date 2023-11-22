package com.myworkshop.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase():RoomDatabase() {
    abstract fun getItemDao():ItemDao

    companion object{
        private var instance:AppDatabase?=null

        fun getAppDatabase(context:Context):AppDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "item"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(dataCallback(context.applicationContext))
                    .build()
            }
            return instance
        }
    }
}