package com.s2start.rickandmorty.ui.character

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.s2start.rickandmorty.R
import com.s2start.rickandmorty.ui.theme.RickAndMortyTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun ListCharacter() {
    val viewModel:ListCharacterViewModel = koinViewModel()
    val stateView by viewModel.listCharacters.collectAsState()

    when(val state = stateView) {
        is ListCaracterState.Loading -> ListLoaddingScreen()
        is ListCaracterState.Error -> ListErrorScreen(state.e.message.toString())
        is ListCaracterState.Success -> { ScreenSucessList(state.list) }
    }
}

@Composable
private fun ListLoaddingScreen(){
    Column(Modifier
        .fillMaxSize()
        .background(Color.Gray)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Carregando...")
    }
}

@Composable
private fun ListErrorScreen(messageError:String){
    Column(Modifier
        .fillMaxSize()
        .background(Color.Red)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = messageError)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenSucessList(list:List<CharacterModel>){

    val (search,setSearch) = remember {
        mutableStateOf(TextFieldValue())
    }


    LazyColumn {
        item {
            TextField(
                value = search,
                onValueChange = {
                    setSearch(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(3.dp)),
                placeholder = { Text(text = "Buscar") },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor =  Color.Transparent
                )
            )
        }
        items(list){ item ->
            var textIsAlive = if(item.isAlive){ "Vivao" } else {"Mortao"}
            var expandedState by remember { mutableStateOf(false) }

            val rotationState by animateFloatAsState(
                targetValue = if (expandedState) 180f else 0f, label = ""
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    .padding(12.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {

                }
            ) {
                Row(Modifier) {
                    AsyncImage(
                        model = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.ic_back),
                        modifier = Modifier.size(50.dp)
                    )

                    Column (Modifier.padding(start = 10.dp)){
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Human - $textIsAlive",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier
                            .alpha(0.2f)
                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down Arrow"
                        )
                    }
                }
                if (expandedState) {
                    Column(Modifier.padding(20.dp)) {
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Status: ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = textIsAlive,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }

                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Sexo: ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Male",
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }

                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Espécies: ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Animal",
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }

                        Row (verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Origem: ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Marte",
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }

                    }
                }
            }
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
