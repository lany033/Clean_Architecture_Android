package com.example.kotlincoroutines1

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NumberAddTest {
    @get: Rule
    val dispatcherTestRule = DispatcherTestRule()

    @ExperimentalCoroutinesApi
    @Test
    fun testAdd() = runBlockingTest {
        val adder = NumberAdder (dispatcherTestRule.testDispatcher, 0)
        assertEquals(5, adder.add(1,4))
    }
}