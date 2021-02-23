package com.yukarlo.anime.lib.anime.di

import com.yukarlo.anime.lib.anime.data.AnimeRepositoryImpl
import com.yukarlo.anime.lib.anime.domain.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class LibAnimeModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeRepository(
        animeRepository: AnimeRepositoryImpl
    ): AnimeRepository
}
