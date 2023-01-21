package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerInfo(
    val score: Score
)
