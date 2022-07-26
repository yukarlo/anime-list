package com.yukarlo.anime.lib.anime.mapper

import com.yukarlo.anime.core.model.*
import com.yukarlo.fragment.AnimeMedia
import com.yukarlo.AnimeDetailsQuery
import com.yukarlo.AnimeQuery
import com.yukarlo.MultipleAnimeSortQuery
import java.util.regex.Pattern
import javax.inject.Inject
import kotlin.math.floor

internal class AnimeMapper @Inject constructor() {

    companion object {
        private const val YOUTUBE = "youtube"
        private const val YOUTUBE_FULL_LINK = "https://www.youtube.com/watch?v="
        private const val YOUTUBE_SHORT_LINK = "https://youtu.be/"
        private const val PATTERN_YOUTUBE_EXTRACT = "(\\?v=)(.*)"
        private const val NO_THUMBNAIL = "http://placehold.it/1280x720?text=No+Preview+Available"
        private const val VIDEO_THUMBNAIL = "https://img.youtube.com/vi/%s/hqdefault.jpg"

        private const val DAY_IN_SECONDS = 86400
        private const val HOUR_IN_SECONDS = 3600
    }

    fun mapAnimeToDomain(result: List<AnimeQuery.Medium?>?): List<Anime>? =
        result?.map {
            mapAnime(animeMedia = it?.animeMedia)
        }

    fun mapMultipleAnimeToDomain(data: MultipleAnimeSortQuery.Data?): MultipleAnimeSort =
        MultipleAnimeSort(
            top10 = data?.top10?.media?.map {
                mapAnime(animeMedia = it?.animeMedia)
            } ?: emptyList(),
            popularThisSeason = data?.popularThisSeason?.media?.map {
                mapAnime(animeMedia = it?.animeMedia)
            } ?: emptyList(),
            trendingNow = data?.trendingNow?.media?.map {
                mapAnime(animeMedia = it?.animeMedia)
            } ?: emptyList(),
            allTimePopular = data?.allTimePopular?.media?.map {
                mapAnime(animeMedia = it?.animeMedia)
            } ?: emptyList()
        )

    fun mapAnimeDetailsToDomain(result: AnimeDetailsQuery.AnimeMedia?): AnimeDetails =
        AnimeDetails(
            basicInfo = mapAnime(animeMedia = result?.animeMedia),
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
                mapAnime(animeMedia = it?.mediaRecommendation?.animeMedia)
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
            },
            nextAiringSchedule = result?.nextAiringEpisode?.timeUntilAiring?.let {
                AiringSchedule(
                    episodeNumber = result.nextAiringEpisode?.episode ?: 0,
                    date = calculateToDaysHoursMinutesRemaining(totalSeconds = it)
                )
            }
        )

    private fun mapAnime(animeMedia: AnimeMedia?): Anime =
        Anime(
            id = animeMedia?.id,
            title = Title(
                english = animeMedia?.title?.english.orEmpty(),
                userPreferred = animeMedia?.title?.userPreferred.orEmpty(),
                native = animeMedia?.title?.native.orEmpty(),
            ),
            coverImage = Image(
                extraLarge = animeMedia?.coverImage?.extraLarge.orEmpty(),
                large = animeMedia?.coverImage?.large.orEmpty()
            ),
            status = animeMedia?.status?.name.orEmpty(),
            genres = animeMedia?.genres.orEmpty().joinToString(separator = " • "),
            startDate = if (animeMedia?.startDate?.month != null) {
                animeMedia.startDate?.let {
                    Date(
                        month = it.month.toString(),
                        day = it.day.toString(),
                        year = it.year.toString()
                    )
                }
            } else {
                null
            },
            endDate = if (animeMedia?.endDate?.month != null) {
                animeMedia.endDate?.let {
                    Date(
                        month = it.month.toString(),
                        day = it.day.toString(),
                        year = it.year.toString()
                    )
                }
            } else {
                null
            },
            format = animeMedia?.format?.name?.lowercase().orEmpty(),
            averageScore = animeMedia?.averageScore ?: 0
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

    private fun calculateToDaysHoursMinutesRemaining(totalSeconds: Int): String {
        val daysRemaining = floor((totalSeconds / DAY_IN_SECONDS).toDouble())
        val hoursRemaining =
            floor((totalSeconds - daysRemaining * DAY_IN_SECONDS) / HOUR_IN_SECONDS)

        return "${daysRemaining.toInt()}d ${hoursRemaining.toInt()}h"
    }
}
