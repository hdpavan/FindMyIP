package com.example.findmyip.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.findmyip.Util.APIResponse
import com.example.findmyip.domain.model.MyIPInfo
import com.example.findmyip.domain.repository.IRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetIPUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var repository: IRepository

    lateinit var getIPUseCase: GetIPUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getIPUseCase = GetIPUseCase(repository)

    }

    @Test
    fun `when success`() {
        val data = MyIPInfo(city = "Bengaluru", region = "Karnataka")

        runTest {
            Mockito.`when`(repository.getMyIpInfo()).thenReturn(APIResponse.Success(data = data))
            getIPUseCase().test {
                Assert.assertEquals(true, awaitItem() is APIResponse.Loading)
                assertEquals(data, awaitItem().data)
                awaitComplete()
                cancel()
            }
        }
    }


    @Test
    fun `when error`() {
        runTest {
            Mockito.`when`(repository.getMyIpInfo())
                .thenReturn(APIResponse.Error(error = "Something went wrong"))
            getIPUseCase().test {
                Assert.assertEquals(true, awaitItem() is APIResponse.Loading)
                Assert.assertEquals(true, awaitItem() is APIResponse.Error)
                awaitComplete()
                cancel()
            }
        }
    }

}