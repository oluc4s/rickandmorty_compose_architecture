package com.s2start.rickandmorty.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.rickandmorty.data.usecase.GetListCharacterUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListCharacterViewModel(
    val getListCharacterUseCase: GetListCharacterUseCase
):ViewModel() {

    private val _listCharacters = MutableStateFlow<ListCaracterState>(ListCaracterState.Loading)
    val listCharacters = _listCharacters
        .onStart { getCharacters() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )


    private fun getCharacters(){
        viewModelScope.launch {
            delay(1000)
            _listCharacters.value = ListCaracterState.Success(getListCharacterUseCase.invoke())
            delay(3000)
            _listCharacters.value = ListCaracterState.Error(Exception("Erro ao carregar"))
        }
    }
}

data class CharacterModel(
    var name:String,
    var isAlive:Boolean,
)