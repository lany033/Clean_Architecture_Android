package com.example.retrofit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//2. Create a UserDao class that will contain methods for inserting users and querying
/********************************************************************************/
//all the users as flows.
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<UserEntity>>

    // if the same user is inserted multiple times, then it will be replaced with the one that will be inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>)
}