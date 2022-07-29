package com.yukarlo.anime.feature.anime.details.di

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.yukarlo.anime.core.navigation.ComposeNavigationFactory
import com.yukarlo.anime.feature.anime.details.navigationgraph.animeDetailsGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Singleton

internal class AnimeDetailsComposeNavigationFactory @Inject constructor() : ComposeNavigationFactory {
    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.animeDetailsGraph(navController)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface ComposeNavigationFactoryModule {
    @Singleton
    @Binds
    @IntoSet
    fun bindComposeNavigationFactory(factory: AnimeDetailsComposeNavigationFactory): ComposeNavigationFactory
}
