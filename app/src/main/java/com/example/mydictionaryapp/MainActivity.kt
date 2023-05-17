package com.example.mydictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mydictionaryapp.presentation.WordInfoViewModel
import com.example.mydictionaryapp.presentation.components.WordInfoItem
import com.example.mydictionaryapp.ui.theme.MyDictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDictionaryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: WordInfoViewModel = hiltViewModel()
                    val state = viewModel.state.value

                    val scaffoldState = rememberScaffoldState()

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest { event ->
                            when (event) {
                                is WordInfoViewModel.UIEvent.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                                }
                            }
                        }
                    }

                    Scaffold(scaffoldState = scaffoldState) {

                        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {

                                TextField(
                                    value = viewModel.searchQuery.value,
                                    onValueChange = viewModel::onSearch, // Ou viewModel::onSearch
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(text = "Enter a word...")
                                    }
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(state.wordInfoItems.size) { i ->
                                        val wordInfo = state.wordInfoItems[i]
                                        if (i > 0) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                            WordInfoItem(wordInfo = wordInfo)
                                            if (i < state.wordInfoItems.size - 1) {
                                                Divider()
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDictionaryAppTheme {
    }
}