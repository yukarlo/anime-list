package com.yukarlo.anime.core.model

data class Character(
    val name: String = "",
    val image: Image = Image(),
    val voiceActor: String = "",
    val voiceActorImage: Image = Image()
)
