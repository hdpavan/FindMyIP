package com.example.findmyip.presentation

import com.example.findmyip.domain.model.MyIPInfo

data class UIState(
    val isLoading: Boolean = false,
    val data: MyIPInfo? = null,
    val error: String? = null
)
