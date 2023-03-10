package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data class required for importing root/players elements from JSON.
 *
 * Author: Tobias Mielke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Players(
    @JsonAlias("1")
    val player1: LogPlayer,

    @JsonAlias("2")
    val player2: LogPlayer,

    @JsonAlias("3")
    val player3: LogPlayer? = null,

    @JsonAlias("4")
    val player4: LogPlayer? = null,

    @JsonAlias("5")
    val player5: LogPlayer? = null,
) {

    fun all(): List<LogPlayer> {
        val list = mutableListOf(player1, player2)

        if (player3 != null) {
            list += player3
        }

        if (player4 != null) {
            list += player4
        }

        if (player5 != null) {
            list += player5
        }

        return list
    }

}
