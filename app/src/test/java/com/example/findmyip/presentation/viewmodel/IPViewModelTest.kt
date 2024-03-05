package com.example.findmyip.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.findmyip.TestDispatchers
import com.example.findmyip.Util.APIResponse
import com.example.findmyip.domain.model.MyIPInfo
import com.example.findmyip.domain.repository.IRepository
import com.example.findmyip.domain.usecase.GetIPUseCase
import com.example.findmyip.presentation.IPViewModel
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

class IPViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var ipViewModel: IPViewModel

    @Mock
    lateinit var repository: IRepository

    @Mock
    lateinit var getIPUseCase: GetIPUseCase


    lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        testDispatchers = TestDispatchers()

        getIPUseCase = GetIPUseCase(repository)

        ipViewModel =
            IPViewModel(getIPUseCase, testDispatchers)
    }

    @Test
    fun getMYIpInfo() {
        val data = MyIPInfo(city = "Bengaluru", region = "Karnataka")

        runTest {
            Mockito.`when`(repository.getMyIpInfo()).thenReturn(APIResponse.Success(data = data))

            ipViewModel.getMyIpInfo()

            ipViewModel.state.test {
                assertEquals(
                    data,
                    awaitItem().data
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

}