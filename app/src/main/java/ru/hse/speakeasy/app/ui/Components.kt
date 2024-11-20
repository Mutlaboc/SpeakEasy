package ru.hse.speakeasy.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(language: String, text: String, onTextChange: (String) -> Unit, onClearText: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = language)
        OutlinedTextField(value = text, onValueChange = onTextChange, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Enter text here...") }, trailingIcon = {
            IconButton(onClick = onClearText) {
                Icon(Icons.Default.Clear, contentDescription = "Clear text")
            }
        })
    }
}

@Composable
fun TranslateButton(onTranslate: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = onTranslate,
            modifier = Modifier
                .align(Alignment.CenterEnd) // Aligns the button to the right side
                .padding(top = 8.dp)
        ) {
            Text("Translate")
        }
    }
}

@Composable
fun TranslationResult(
    result: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = result,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = "Toggle Favorite"
            )
        }
    }

}


@Composable
fun LanguageSelector(
    sourceLanguage: String,
    targetLanguage: String,
    onSourceLanguageChange: (String) -> Unit,
    onTargetLanguageChange: (String) -> Unit,
    onSwapLanguages: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        LanguageDropdown(
            selectedLanguage = sourceLanguage,
            onLanguageChange = onSourceLanguageChange
        )
        IconButton(onClick = onSwapLanguages) {
            Icon(Icons.Default.SwapHoriz, contentDescription = "Swap Languages")
        }
        LanguageDropdown(
            selectedLanguage = targetLanguage,
            onLanguageChange = onTargetLanguageChange
        )
    }
}

@Composable
fun LanguageDropdown(
    selectedLanguage: String,
    onLanguageChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("English", "Russian", "Spanish") // Добавьте нужные языки

    Box {
        Text(
            text = selectedLanguage,
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            languages.forEach { language ->
                DropdownMenuItem(onClick = {
                    onLanguageChange(language)
                    expanded = false
                }) {
                    Text(text = language)
                }
            }
        }
    }
}
