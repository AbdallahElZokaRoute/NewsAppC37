package com.route.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.route.domain.entity.NewsItemDTO
import com.route.domain.reposImplForTesting.FakeNewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNewsUseCaseTest {
    /***
     *
     *  if you called API or Database -> It will take time
     *  Fake Repository
     */

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var getNewsUseCase: GetNewsUseCase
    lateinit var fakeNewsRepositoryImpl: FakeNewsRepositoryImpl


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        fakeNewsRepositoryImpl = FakeNewsRepositoryImpl()
        getNewsUseCase = GetNewsUseCase(fakeNewsRepositoryImpl)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke() with Blank Source then returns empty List`() = runTest {

        //Arrange
        val source = ""
        // Act
        val result = getNewsUseCase(source)

        //Assert
        assertEquals(emptyList<NewsItemDTO>(), result)

    }

    @Test
    fun `invoke() with Blank Source then returns empty List2`() = runTest {

        //Arrange
        val source = ""
        // Act
        val result = getNewsUseCase(source)

        //Assert
        assertEquals(emptyList<NewsItemDTO>(), result)

    }

}