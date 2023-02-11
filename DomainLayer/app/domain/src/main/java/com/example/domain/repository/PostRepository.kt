package com.example.domain.repository

import com.example.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<com.example.domain.entity.Post>>
    fun getPost(id: Long): Flow<com.example.domain.entity.Post>
}