package de.kayteem.apps.tfmgamelogconverter.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Players(
    @JsonAlias("1")
    val player1: Player,

    @JsonAlias("2")
    val player2: Player,

    @JsonAlias("3")
    val player3: Player? = null,

    @JsonAlias("4")
    val player4: Player? = null,

    @JsonAlias("5")
    val player5: Player? = null,
) {

    fun all(): List<Player> {
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
