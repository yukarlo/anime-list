package com.yukarlo.remote

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Image
import com.yukarlo.anime.core.model.MultipleAnimeSort
import com.yukarlo.anime.core.model.Title
import fragment.AnimeMedia
import query.AnimeQuery
import query.MultipleAnimeSortQuery
import javax.inject.Inject

internal class AnimeMapper @Inject constructor() {

    fun mapAnimeToDomain(result: List<AnimeQuery.Medium?>?): List<Anime>? =
        result?.map {
            mapAnime(
                id = it?.fragments?.animeMedia?.id,
                title = Title(
                    english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                    native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                    userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                ),
                image = Image(
                    extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                    large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                ),
                genres = it?.fragments?.animeMedia?.genres,
                status = it?.fragments?.animeMedia?.status?.name,
                startDate = it?.fragments?.animeMedia?.startDate,
                endDate = it?.fragments?.animeMedia?.endDate,
                format = it?.fragments?.animeMedia?.format?.name
            )
        }

    fun mapMultipleAnimeToDomain(data: MultipleAnimeSortQuery.Data): MultipleAnimeSort =
        MultipleAnimeSort(
            top10 = data.top10?.media?.map {
                mapAnime(
                    id = it?.fragments?.animeMedia?.id,
                    title = Title(
                        english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                        native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                        userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                    ),
                    image = Image(
                        extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                        large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                    ),
                    genres = it?.fragments?.animeMedia?.genres,
                    status = it?.fragments?.animeMedia?.status?.name,
                    startDate = it?.fragments?.animeMedia?.startDate,
                    endDate = it?.fragments?.animeMedia?.endDate,
                    format = it?.fragments?.animeMedia?.format?.name
                )
            } ?: emptyList(),
            popularThisSeason = data.popularThisSeason?.media?.map {
                mapAnime(
                    id = it?.fragments?.animeMedia?.id,
                    title = Title(
                        english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                        native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                        userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                    ),
                    image = Image(
                        extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                        large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                    ),
                    genres = it?.fragments?.animeMedia?.genres,
                    status = it?.fragments?.animeMedia?.status?.name,
                    startDate = it?.fragments?.animeMedia?.startDate,
                    endDate = it?.fragments?.animeMedia?.endDate,
                    format = it?.fragments?.animeMedia?.format?.name
                )
            } ?: emptyList(),
            trendingNow = data.trendingNow?.media?.map {
                mapAnime(
                    id = it?.fragments?.animeMedia?.id,
                    title = Title(
                        english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                        native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                        userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                    ),
                    image = Image(
                        extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                        large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                    ),
                    genres = it?.fragments?.animeMedia?.genres,
                    status = it?.fragments?.animeMedia?.status?.name,
                    startDate = it?.fragments?.animeMedia?.startDate,
                    endDate = it?.fragments?.animeMedia?.endDate,
                    format = it?.fragments?.animeMedia?.format?.name
                )
            } ?: emptyList(),
            allTimePopular = data.allTimePopular?.media?.map {
                mapAnime(
                    id = it?.fragments?.animeMedia?.id,
                    title = Title(
                        english = it?.fragments?.animeMedia?.title?.english.orEmpty(),
                        native = it?.fragments?.animeMedia?.title?.native_.orEmpty(),
                        userPreferred = it?.fragments?.animeMedia?.title?.userPreferred.orEmpty()
                    ),
                    image = Image(
                        extraLarge = it?.fragments?.animeMedia?.coverImage?.extraLarge.orEmpty(),
                        large = it?.fragments?.animeMedia?.coverImage?.large.orEmpty()
                    ),
                    genres = it?.fragments?.animeMedia?.genres,
                    status = it?.fragments?.animeMedia?.status?.name,
                    startDate = it?.fragments?.animeMedia?.startDate,
                    endDate = it?.fragments?.animeMedia?.endDate,
                    format = it?.fragments?.animeMedia?.format?.name
                )
            } ?: emptyList()
        )

    private fun mapAnime(
        id: Int?,
        title: Title,
        image: Image,
        status: String?,
        genres: List<String?>?,
        startDate: AnimeMedia.StartDate?,
        endDate: AnimeMedia.EndDate?,
        format: String?
    ): Anime = Anime(
        id = id,
        title = title,
        coverImage = image,
        status = status.orEmpty(),
        genres = genres.orEmpty().joinToString(separator = " • "),
        startDate = startDate?.let {
            "${it.month}.${it.day}.${it.year}"
        } ?: "",
        endDate = endDate?.let {
            "${it.month}.${it.day}.${it.year}"
        } ?: "",
        formatAndYear = "$format • ${startDate?.year}"
    )
}
