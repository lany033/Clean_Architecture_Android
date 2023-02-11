package com.example.domain.repository

import com.example.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<com.example.domain.entity.User>>
    fun getUser(id: Long): Flow<com.example.domain.entity.User>
}