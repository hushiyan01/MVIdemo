package com.myworkshop.myapplication.model

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun dataCallback(context: Context) = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            val itemDao = AppDatabase.getAppDatabase(context)!!.getItemDao()

            itemDao.addItem(
                Item(0,"Test name A","This is the very first item in db!")
            )

        }
    }
}