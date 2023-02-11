package com.example.domain.usecase

import com.example.domain.entity.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    configuration: Configuration,
    private val postRepository: PostRepository
) : UseCase<GetPostUseCase.Request, GetPostUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        postRepository.getPost(request.postId)
            .map {
                Response(it)
            }

    data class Request(val postId: Long) : UseCase.Request
    data class Response(val post: Post) : UseCase.Response
}