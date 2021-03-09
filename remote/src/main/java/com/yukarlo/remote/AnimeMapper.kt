package com.yukarlo.remote

import com.yukarlo.anime.core.model.*
import fragment.AnimeMedia
import query.AnimeDetailsQuery
import query.AnimeQuery
import query.MultipleAnimeSortQuery
import java.util.regex.Pattern
import javax.inject.Inject

internal class AnimeMapper @Inject constructor() {

    companion object {
        private const val YOUTUBE = "youtube"
        private const val YOUTUBE_FULL_LINK = "https://www.youtube.com/watch?v="
        private const val YOUTUBE_SHORT_LINK = "https://youtu.be/"
        private const val PATTERN_YOUTUBE_EXTRACT = "(\\?v=)(.*)"
        private const val NO_THUMBNAIL = "http://placehold.it/1280x720?text=No+Preview+Available"
        private const val VIDEO_THUMBNAIL = "https://img.youtube.com/vi/%s/hqdefault.jpg"
    }

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
            basicInfo = mapAnime(animeMedia = result?.fragments?.animeMedia),
            characters = result?.characterPreview?.edges?.map {
                Character(
                    name = it?.node?.name?.full.orEmpty(),
                    image = Image(
                        large = it?.node?.image?.large.orEmpty()
                    ),
                    voiceActor = it?.voiceActors?.first()?.name?.full.orEmpty(),
                    voiceActorImage = Image(
                        large = it?.voiceActors?.first()?.image?.large.orEmpty()
                    )
                )
            }.orEmpty(),
            description = result?.description.orEmpty(),
            duration = result?.duration ?: 0,
            episodes = result?.episodes ?: 0,
            recommendations = result?.recommendations?.nodes?.map {
                mapAnime(animeMedia = it?.mediaRecommendation?.fragments?.animeMedia)
            }.orEmpty(),
            studio = result?.studios?.nodes?.first()?.name.orEmpty(),
            trailer = buildYoutubeUrl(
                site = result?.trailer?.site.orEmpty(),
                id = result?.trailer?.id.orEmpty()
            ).let {
                Trailer(
                    youtubeTrailerUrl = it,
                    youtubeThumbnail = getYoutubeThumbnail(link = it)
                )
            }

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
                Date(
                    month = it.month.toString(),
                    day = it.day.toString(),
                    year = it.year.toString()
                )
            },
            endDate = animeMedia?.endDate?.let {
                Date(
                    month = it.month.toString(),
                    day = it.day.toString(),
                    year = it.year.toString()
                )
            },
            format = animeMedia?.format?.name?.toLowerCase().orEmpty()
        )

    private fun buildYoutubeUrl(site: String, id: String): String =
        when {
            site.contains(YOUTUBE) ||
                    site.contains(YOUTUBE_SHORT_LINK) ||
                    site.contains(YOUTUBE_FULL_LINK) -> {
                YOUTUBE_FULL_LINK + id
            }
            else -> {
                ""
            }
        }

    private fun getYoutubeThumbnail(link: String): String {
        val matcher = Pattern.compile(PATTERN_YOUTUBE_EXTRACT).matcher(link)

        return if (matcher.find()) {
            String.format(VIDEO_THUMBNAIL, matcher.group(matcher.groupCount()))
        } else {
            NO_THUMBNAIL
        }
    }
}
