package ru.hse.speakeasy.app.screen.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.lang.reflect.Modifier

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val favorites by viewModel.favoriteTranslations.collectAsState(initial = emptyList())

    LazyColumn {
        items(favorites) { translation ->
            Text(
                text = "${translation.sourceText} → ${translation.translatedText}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
