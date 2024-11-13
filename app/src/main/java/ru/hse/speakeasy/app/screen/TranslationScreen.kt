package ru.hse.speakeasy.app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.hse.speakeasy.app.ui.TextInput
import ru.hse.speakeasy.app.ui.TranslateButton
import ru.hse.speakeasy.app.ui.TranslationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(title = { Text("Translation App") })

        TextInput(
            language = uiState.value.sourceLang,
            text = uiState.value.inputText,
            onTextChange = { viewModel.updateInputText(it) },
            onClearText = { viewModel.clearInputText() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslateButton(onTranslate = { viewModel.translateText() })

        uiState.value.translatedText?.let {
            TranslationResult(it)
        }
    }

}