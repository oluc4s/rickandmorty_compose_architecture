package com.s2start.rickandmorty.data.usecase

import com.s2start.rickandmorty.ui.character.CharacterModel

class GetListCharacterUseCase(
    private val listCaracterModel: List<CharacterModel>
) {
    suspend operator fun invoke(): List<CharacterModel> {
        return listCaracterModel
    }
}