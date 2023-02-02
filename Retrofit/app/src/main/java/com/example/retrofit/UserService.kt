package com.example.retrofit

import retrofit2.http.GET

//class that will have a method to retrieve a list of users
interface UserService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}