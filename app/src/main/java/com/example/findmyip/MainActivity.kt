package com.example.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.findmyip.presentation.IPViewModel
import com.example.findmyip.ui.theme.FindMyIPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    val viewModel = hiltViewModel<IPViewModel>()

                    val uiState = viewModel.state.collectAsState().value

                    Column {

                        Button(onClick = {
                            viewModel.getMyIpInfo()
                        }) {
                            Text(text = "GETMYIPINFO")
                        }

                        if (uiState.isLoading) {
                            CircularProgressIndicator()
                        }

                        if (uiState.error != null) {
                            Text(text = "Something Went Wrong")
                        }

                        if (uiState.data != null) {
                            Text(text = uiState.data.toString())
                        }

                    }

                }
            }
        }
    }
}
