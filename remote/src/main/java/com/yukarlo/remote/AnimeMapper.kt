package com.yukarlo.remote

import com.yukarlo.anime.core.model.*
import fragment.AnimeMedia
import query.AnimeDetailsQuery
import query.AnimeQuery
import query.MultipleAnimeSortQuery
import javax.inject.Inject

internal class AnimeMapper @Inject constructor() {

    fun mapAnimeToDomain(result: List<AnimeQuery.Medium?>?): List<Anime>? =
        result?.map {
            mapAnime(animeMedia = it?.fragments?.animeMedia)
        }

    fun mapMultipleAnimeToDomain(data: MultipleAnimeSortQuery.Data): MultipleAnimeSort =
        MultipleAnimeSort(
            top10 = data.top10?.media?.map {
                mapAnime(animeMedia = it?.fragments?.animeMedia)
            } ?: emptyList(),
            popularThisSeason = data.popularThisSeason?.media?.map {
                mapAnime(animeMedia = it?.fragments?.animeMedia)
            } ?: emptyList(),
            trendingNow = data.trendingNow?.media?.map {
                mapAnime(animeMedia = it?.fragments?.animeMedia)
            } ?: emptyList(),
            allTimePopular = data.allTimePopular?.media?.map {
                mapAnime(animeMedia = it?.fragments?.animeMedia)
            } ?: emptyList()
        )

    fun mapAnimeDetailsToDomain(result: AnimeDetailsQuery.Media?): AnimeDetails =
        AnimeDetails(
            basicInfo = mapAnime(animeMedia = result?.fragments?.animeMedia)
        )

    private fun mapAnime(animeMedia: AnimeMedia?): Anime =
        Anime(
            id = animeMedia?.id,
            title = Title(
                english = animeMedia?.title?.english.orEmpty(),
                userPreferred = animeMedia?.title?.userPreferred.orEmpty(),
                native = animeMedia?.title?.native_.orEmpty(),
            ),
            coverImage = Image(
                extraLarge = animeMedia?.coverImage?.extraLarge.orEmpty(),
                large = animeMedia?.coverImage?.large.orEmpty()
            ),
            status = animeMedia?.status?.name.orEmpty(),
            genres = animeMedia?.genres.orEmpty().joinToString(separator = " • "),
            startDate = animeMedia?.startDate?.let {
                "${it.month}.${it.day}.${it.year}"
            } ?: "",
            endDate = animeMedia?.endDate?.let {
                "${it.month}.${it.day}.${it.year}"
            } ?: "",
            formatAndYear = "${animeMedia?.format?.name?.toLowerCase()} • ${animeMedia?.startDate?.year}"
        )
}
