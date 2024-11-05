package com.s2start.rickandmorty.di

import com.s2start.rickandmorty.data.usecase.GetListCharacterUseCase
import com.s2start.rickandmorty.mock.listCharacters
import com.s2start.rickandmorty.ui.character.CharacterModel
import com.s2start.rickandmorty.ui.character.ListCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
//testando git hub
val appModule = module {
    single<List<CharacterModel>> { listCharacters }

    factory { GetListCharacterUseCase(get()) }

    viewModel { ListCharacterViewModel(get()) }
}