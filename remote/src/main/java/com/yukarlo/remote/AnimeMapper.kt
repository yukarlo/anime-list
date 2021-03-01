package com.yukarlo.remote

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Image
import com.yukarlo.anime.core.model.Title
import query.AnimeQuery
import javax.inject.Inject

internal class AnimeMapper @Inject constructor() {

    fun mapTopAnimeToDomain(result: List<AnimeQuery.Medium?>?): List<Anime>? =
        result?.map {
            Anime(
                title = Title(
                    english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                    native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                    userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                ),
                coverImage = Image(
                    extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                    large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                ),
                status = it?.fragments?.animeMedia?.status?.name.orEmpty(),
                genres = it?.fragments?.animeMedia?.genres?.joinToString(separator = " • ")
                    .orEmpty(),
                startDate = "${it?.fragments?.animeMedia?.startDate?.month}.${it?.fragments?.animeMedia?.startDate?.day}.${it?.fragments?.animeMedia?.startDate?.year}",
                endDate = "${it?.fragments?.animeMedia?.endDate?.month}.${it?.fragments?.animeMedia?.endDate?.day}.${it?.fragments?.animeMedia?.endDate?.year}",
                formatAndYear = it?.fragments?.animeMedia?.let { anime ->
                    "${anime.format?.name?.toLowerCase()} • ${anime.startDate?.year}"
                }.orEmpty()
            )
        }
}
