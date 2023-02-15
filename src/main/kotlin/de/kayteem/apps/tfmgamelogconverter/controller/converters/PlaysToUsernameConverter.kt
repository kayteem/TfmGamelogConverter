package de.kayteem.apps.tfmgamelogconverter.controller.converters

import de.kayteem.apps.tfmgamelogconverter.model.internal.Play

/**
 * Implementation for extracting the username from a list of plays.
 * The username is considered to be the name of the most frequent player in all plays.
 *
 * Author: Tobias Mielke
 */
class PlaysToUsernameConverter : Converter<List<Play>, String> {

    override fun process(input: List<Play>): String {
        val playerCounts = input
            .flatMap { it.players }
            .groupingBy { it.name }
            .eachCount()

        return playerCounts
            .maxBy { it.value }
            .key
    }

}