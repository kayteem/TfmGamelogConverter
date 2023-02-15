package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data class required for importing root/players/<N> elements from JSON.
 *
 * Author: Tobias Mielke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class LogPlayer(
    val name: String,
    val color: String,
    val corporation: String,
    val elo: Double
)
