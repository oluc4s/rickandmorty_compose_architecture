package com.s2start.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.s2start.rickandmorty.ui.character.ListCharacter
import com.s2start.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
        }
    }
}


@Preview("List Rick")
@Composable
fun Screen(){
    RickAndMortyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column (Modifier.padding(innerPadding)){
                ListCharacter()
            }
        }
    }
}
