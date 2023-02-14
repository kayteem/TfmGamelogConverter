package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data class required for importing root/turnLogs/<TurnLog>/playerInfo/<N> elements from JSON.
 *
 * Author: Tobias Mielke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerInfo(
    val score: Score
)
