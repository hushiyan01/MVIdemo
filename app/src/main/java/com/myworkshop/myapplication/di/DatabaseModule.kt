package com.myworkshop.myapplication.di

import android.content.Context
import com.myworkshop.myapplication.model.AppDatabase
import com.myworkshop.myapplication.model.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context) ?: throw IllegalStateException("AppDatabase is not initialized.")
    }

    @Singleton
    @Provides
    fun getItemDao(appDatabase: AppDatabase):ItemDao{
        return appDatabase.getItemDao()
    }
}