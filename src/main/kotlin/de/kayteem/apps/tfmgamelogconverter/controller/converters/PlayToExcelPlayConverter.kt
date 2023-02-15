package de.kayteem.apps.tfmgamelogconverter.controller.converters

import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.ExcelPlay

/**
 * Implementation for converting a Play into an ExcelPlay.
 *
 * Author: Tobias Mielke
 */
class PlayToExcelPlayConverter : Converter<Play, ExcelPlay> {

    override fun process(input: Play): ExcelPlay {
        val players: List<Player> = input.players

        val player1: Player = input.players[0]
        val player2: Player = input.players[1]
        val player3: Player? = if (players.size > 2) input.players[2] else null
        val player4: Player? = if (players.size > 3) input.players[3] else null
        val player5: Player? = if (players.size > 4) input.players[4] else null

        return ExcelPlay(
            input.timestamp,
            input.board,
            input.generations,
            player1.name,
            player1.corporation,
            player1.elo,
            player1.finalScore,
            player2.name,
            player2.corporation,
            player2.elo,
            player2.finalScore,
            player3?.name,
            player3?.corporation,
            player3?.elo,
            player3?.finalScore,
            player4?.name,
            player4?.corporation,
            player4?.elo,
            player4?.finalScore,
            player5?.name,
            player5?.corporation,
            player5?.elo,
            player5?.finalScore
        )
    }

}