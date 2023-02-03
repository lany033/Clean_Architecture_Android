package com.example.kotlincoroutines1

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//1. Create a UserEntity class that will be a Room entity
/********************************************************************************/
//The UserEntity class has the same fields as the User class, and it contains the
//Room annotations for the table name and the names of each column.
class UserEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name="username") val username: String,
    @ColumnInfo(name = "email") val email: String
) {
}