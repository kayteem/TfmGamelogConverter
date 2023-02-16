package de.kayteem.apps.tfmgamelogconverter.model.internal

/**
 * Enum class required as internal model for a map.
 *
 * Author: Tobias Mielke
 */
enum class Boards(val mapName: String) {
    THARSIS("Tharsis"),
    HELLAS("Hellas"),
    ELYSIUM("Elysium");

    companion object {

        fun fromName(mapName: String): Boards {
            return values().find { it.mapName == mapName }
                ?: throw Exception("Invalid map name: $mapName")
        }
        
    }
}