package com.example.retrofit

import androidx.room.Database
import androidx.room.RoomDatabase

//3. Create an AppDatabase class that will represent the application's database.
/********************************************************************************/
//In AppDatabase, we provide the UserDao class to be accessed and we use the
//UserEntity class for the users' table.

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}