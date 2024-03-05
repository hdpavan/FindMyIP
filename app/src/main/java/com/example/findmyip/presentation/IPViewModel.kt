package com.example.findmyip.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmyip.Util.APIResponse
import com.example.findmyip.Util.DispatchersProvider
import com.example.findmyip.domain.usecase.GetIPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IPViewModel @Inject constructor(
    val useCase: GetIPUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    fun getMyIpInfo() {
        viewModelScope.launch(dispatchersProvider.io) {

            useCase().collect { response ->
                when (response) {
                    is APIResponse.Loading -> {
                        Log.d("IPViewModel", "Loading")
                        _state.value = UIState(isLoading = true)
                    }

                    is APIResponse.Success -> {
                        Log.d("IPViewModel", "Success ${response.data}")
                        _state.value = UIState(isLoading = false, data = response.data)
                    }

                    is APIResponse.Error -> {
                        Log.d("IPViewModel", "Error ${response.error}")
                        _state.value = UIState(isLoading = false, error = response.error)
                    }
                }

            }

        }
    }

}