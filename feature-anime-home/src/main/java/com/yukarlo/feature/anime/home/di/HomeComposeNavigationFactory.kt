package com.yukarlo.feature.anime.home.di

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.yukarlo.anime.core.navigation.ComposeNavigationFactory
import com.yukarlo.feature.anime.home.naviationgraph.homeGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Singleton

internal class HomeComposeNavigationFactory @Inject constructor() : ComposeNavigationFactory {
    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.homeGraph(navController)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface ComposeNavigationFactoryModule {
    @Singleton
    @Binds
    @IntoSet
    fun bindComposeNavigationFactory(factory: HomeComposeNavigationFactory): ComposeNavigationFactory
}
