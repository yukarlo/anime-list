package com.yukarlo.anime.lib.anime.mapper

import com.yukarlo.anime.core.model.Anime
import com.yukarlo.anime.core.model.Title
import query.TopAnimesQuery
import javax.inject.Inject

class AnimeMapper @Inject constructor() {

    fun mapToDomain(result: List<TopAnimesQuery.Medium?>?): List<Anime>? {
        return result?.map {
            Anime(
                title = Title(
                    english = it?.title?.english.orEmpty(),
                    native = it?.title?.native_.orEmpty(),
                    userPreferred = it?.title?.userPreferred.orEmpty()
                ),
                coverImage = it?.coverImage?.large.orEmpty(),
                status = ""
            )
        }
    }
}
