package de.kayteem.apps.tfmgamelogconverter.controller.export.styleManagers

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.PlaysSheetFactory.Companion.PlaysColumns
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.PlaysSheetFactory.Companion.PlaysColumns.*
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Creates and manages cell styles for the "Plays" sheet.
 *
 * Author: Tobias Mielke
 */
class PlaysStyleManager(workbook: XSSFWorkbook, private val username: String) : AbstractStyleManager(workbook) {

    // interface
    fun applyStyle(column: PlaysColumns, players: List<Player>, winner: Player?, cellBuilder: CellBuilder): CellBuilder {
        return when(column) {
            TIMESTAMP       -> applyTimestampStyle(cellBuilder)
            BOARD           -> applyBoardStyle(cellBuilder)
            GENERATIONS     -> applyGenerationsStyle(cellBuilder)
            PLAYER_COUNT    -> applyPlayerCountStyle(cellBuilder)
            WINNER          -> applyWinnerStyle(cellBuilder)

            PLAYER_1_NAME   -> applyPlayerNameStyle(players.getOrNull(0), winner, false, cellBuilder)
            PLAYER_1_CORP   -> applyCorpStyle(players.getOrNull(0), winner, false, cellBuilder)
            PLAYER_1_SCORE  -> applyScoreStyle(players.getOrNull(0), winner, false, cellBuilder)
            PLAYER_1_ELO    -> applyEloStyle(players.getOrNull(0), winner, false, cellBuilder)

            PLAYER_2_NAME   -> applyPlayerNameStyle(players.getOrNull(1), winner, true, cellBuilder)
            PLAYER_2_CORP   -> applyCorpStyle(players.getOrNull(1), winner, true, cellBuilder)
            PLAYER_2_SCORE  -> applyScoreStyle(players.getOrNull(1), winner, true, cellBuilder)
            PLAYER_2_ELO    -> applyEloStyle(players.getOrNull(1), winner, true, cellBuilder)

            PLAYER_3_NAME   -> applyPlayerNameStyle(players.getOrNull(2), winner, false, cellBuilder)
            PLAYER_3_CORP   -> applyCorpStyle(players.getOrNull(2), winner, false, cellBuilder)
            PLAYER_3_SCORE  -> applyScoreStyle(players.getOrNull(2), winner, false, cellBuilder)
            PLAYER_3_ELO    -> applyEloStyle(players.getOrNull(2), winner, false, cellBuilder)

            PLAYER_4_NAME   -> applyPlayerNameStyle(players.getOrNull(3), winner, true, cellBuilder)
            PLAYER_4_CORP   -> applyCorpStyle(players.getOrNull(3), winner, true, cellBuilder)
            PLAYER_4_SCORE  -> applyScoreStyle(players.getOrNull(3), winner, true, cellBuilder)
            PLAYER_4_ELO    -> applyEloStyle(players.getOrNull(3), winner, true, cellBuilder)

            PLAYER_5_NAME   -> applyPlayerNameStyle(players.getOrNull(4), winner, false, cellBuilder)
            PLAYER_5_CORP   -> applyCorpStyle(players.getOrNull(4), winner, false, cellBuilder)
            PLAYER_5_SCORE  -> applyScoreStyle(players.getOrNull(4), winner, false, cellBuilder)
            PLAYER_5_ELO    -> applyEloStyle(players.getOrNull(4), winner, false, cellBuilder)
        }
    }


    // helpers
    private fun applyTimestampStyle(cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .dateFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(IndexedColors.LIGHT_GREEN)
            .cellPattern(FillPatternType.SOLID_FOREGROUND)
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyBoardStyle(cellBuilder: CellBuilder): CellBuilder {
        return cellBuilder.cellStyle(buildSecondaryHeaderStyle())
    }

    private fun applyGenerationsStyle(cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .intFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(IndexedColors.LIGHT_GREEN)
            .cellPattern(FillPatternType.SOLID_FOREGROUND)
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyPlayerCountStyle(cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .intFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(IndexedColors.LIGHT_GREEN)
            .cellPattern(FillPatternType.SOLID_FOREGROUND)
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyWinnerStyle(cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .stringFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(IndexedColors.LIGHT_GREEN)
            .cellPattern(FillPatternType.SOLID_FOREGROUND)
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyPlayerNameStyle(player: Player?, winner: Player?, shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(isUser(player))
            .stringFormat()
            .textColor(getWinningScoreTextColor(player, winner))
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyCorpStyle(player: Player?, winner: Player?, shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(isUser(player))
            .stringFormat()
            .textColor(getWinningScoreTextColor(player, winner))
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyScoreStyle(player: Player?, winner: Player?, shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(isUser(player) || isWinner(player, winner))
            .intFormat()
            .textColor(getWinningScoreTextColor(player, winner))
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyEloStyle(player: Player?, winner: Player?, shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(isUser(player))
            .intFormat()
            .textColor(getWinningScoreTextColor(player, winner))
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun getWinningScoreTextColor(player: Player?, winner: Player?): IndexedColors {
        return if (!isWinner(player, winner)) IndexedColors.BLACK
        else if (isUser(player)) IndexedColors.GREEN
        else IndexedColors.RED
    }

    private fun isUser(player: Player?): Boolean {
        return player != null && player.name == username
    }

    private fun isWinner(player: Player?, winner: Player?): Boolean {
        return player != null && player == winner
    }

}
