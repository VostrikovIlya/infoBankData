package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.api.BankService
import com.example.myapplication.database.AppRoomDatabase
import com.example.myapplication.ui.IntentHelper
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BankApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): BankService =
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppRoomDatabase {
        return Room.databaseBuilder(appContext, AppRoomDatabase::class.java, "BankDatabase").build()
    }

//    @Provides
//    fun provideIntentHelper(@ActivityContext context: Context): IntentHelper =
//        IntentHelper(context = context)
}