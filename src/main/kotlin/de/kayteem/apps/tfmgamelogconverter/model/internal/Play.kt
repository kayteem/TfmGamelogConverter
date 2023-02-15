package de.kayteem.apps.tfmgamelogconverter.model.internal

/**
 * Data class required as internal model for a play.
 *
 * Author: Tobias Mielke
 */
data class Play(
    val timestamp: String,
    val board: String,
    val generations: Int,
    val players: List<Player>
)