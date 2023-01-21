package de.kayteem.apps.tfmgamelogconverter.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerInfos(
    @JsonAlias("1")
    val playerInfo1: PlayerInfo,

    @JsonAlias("2")
    val playerInfo2: PlayerInfo,

    @JsonAlias("3")
    val playerInfo3: PlayerInfo? = null,

    @JsonAlias("4")
    val playerInfo4: PlayerInfo? = null,

    @JsonAlias("5")
    val playerInfo5: PlayerInfo? = null,
)
