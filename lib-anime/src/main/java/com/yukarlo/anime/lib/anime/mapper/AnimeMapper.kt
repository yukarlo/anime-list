package com.yukarlo.anime.lib.anime.mapper

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Image
import com.yukarlo.anime.core.model.Title
import query.TopAnimesQuery
import javax.inject.Inject

class AnimeMapper @Inject constructor() {

    fun mapTopAnimeToDomain(result: List<TopAnimesQuery.Medium?>?): List<Anime>? =
        result?.map {
            Anime(
                title = Title(
                    english = it?.title?.english.orEmpty(),
                    native = it?.title?.native_.orEmpty(),
                    userPreferred = it?.title?.userPreferred.orEmpty()
                ),
                coverImage = Image(
                    extraLarge = it?.coverImage?.extraLarge.orEmpty(),
                    large = it?.coverImage?.large.orEmpty()
                ),
                status = it?.status?.name.orEmpty()
            )
        }
}
