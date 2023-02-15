package de.kayteem.apps.tfmgamelogconverter.model.internal

/**
 * Data class required as internal model for a player.
 *
 * Author: Tobias Mielke
 */
data class Player(
    val name: String,
    val corporation: String,
    val elo: Int,
    val finalScore: Int
)