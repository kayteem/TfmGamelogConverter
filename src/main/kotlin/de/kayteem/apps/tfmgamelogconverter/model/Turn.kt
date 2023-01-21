package de.kayteem.apps.tfmgamelogconverter.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Turn(
    val generation: Int,

    @JsonAlias("playerInfo")
    val playerInfos: PlayerInfos
)
