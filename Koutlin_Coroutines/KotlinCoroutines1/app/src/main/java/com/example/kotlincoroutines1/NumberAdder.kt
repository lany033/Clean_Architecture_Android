package com.example.kotlincoroutines1

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext


private const val DELAY = 5000 //add 5 seconds delay before performing the sum of the two numbers

class NumberAdder(private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
                  private val delay: Int = DELAY
) {
    /*suspend fun add(a: Int, b:Int): Int {
        return withContext(dispatcher){
            delay(delay.toLong())
            a + b
        }
    }*/

    suspend fun add(a: Int, b:Int): Flow<Int> {
        return flow {
            emit(a + b)
        }.onEach {
            delay(delay.toLong()) //we put a delay on each item emitted in the stream
        }.flowOn(dispatcher)
    }
}