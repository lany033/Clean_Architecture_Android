package com.example.retrofit

import android.app.Application
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.preferencesDataStore
//import androidx.room.Room
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.HiltAndroidApp
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import java.util.concurrent.TimeUnit

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

@HiltAndroidApp
class MyApplication: Application()
/*
{

    companion object {
        lateinit var userService: UserService
        //we will need to initialize the AppDatabase object
        lateinit var userDao: UserDao

        lateinit var appDataStore: AppDataStore

        //create the instance of the MainTextFormatter class in the
        //MyApplication class:
        lateinit var mainTextFormatter: MainTextFormatter
    }

    override fun onCreate() {
        super.onCreate()
        val okHttpClient = OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        userService = retrofit.create(UserService::class.java)

        //we will need to initialize the AppDatabase object
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"my-database"
        ).build()
        userDao = db.userDao()

        appDataStore = AppDataStore(dataStore)

        mainTextFormatter = MainTextFormatter(this)
    }
}
*/