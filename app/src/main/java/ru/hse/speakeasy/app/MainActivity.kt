package ru.hse.speakeasy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.hse.speakeasy.app.ui.theme.SpeakEasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeakEasyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar()
                    }
                ) { innerPadding ->

                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar() {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primarySurface,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_chat),
                        contentDescription = "Chat"
                    )
                },
                selected = false,
                onClick = {

                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_camera),
                        contentDescription = "Camera"
                    )
                },
                selected = false,
                onClick = {

                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_translate),
                        contentDescription = "Translate"
                    )
                },
                selected = false,
                onClick = {

                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_history),
                        contentDescription = "History"
                    )
                },
                selected = false,
                onClick = {

                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_fav),
                        contentDescription = "Favorite"
                    )
                },
                selected = false,
                onClick = {

                }
            )
        }
    }
}