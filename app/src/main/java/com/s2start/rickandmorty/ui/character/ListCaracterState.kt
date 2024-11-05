package com.s2start.rickandmorty.ui.character

sealed class ListCaracterState{
    object Loading:ListCaracterState()
    data class Success(val list:List<CharacterModel>):ListCaracterState()
    data class Error(val e:Exception):ListCaracterState()
}