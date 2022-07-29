package com.yukarlo.anime.feature.anime.list.di

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.yukarlo.anime.core.navigation.ComposeNavigationFactory
import com.yukarlo.anime.feature.anime.list.navigationgraph.animeListGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Singleton

internal class AnimeListComposeNavigationFactory @Inject constructor() : ComposeNavigationFactory {
    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.animeListGraph(navController)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface ComposeNavigationFactoryModule {
    @Singleton
    @Binds
    @IntoSet
    fun bindComposeNavigationFactory(factory: AnimeListComposeNavigationFactory): ComposeNavigationFactory
}
