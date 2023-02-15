package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.PlaysSheetFactory.Companion.PlaysColumns
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.PlaysSheetFactory.Companion.PlaysColumns.*
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.INT_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.PRIMARY_HEADER_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.SECONDARY_HEADER_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.STRING_DATA_BOLD_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.STRING_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.TIMESTAMP_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Creates and manages cell styles for all sheets.
 *
 * Author: Tobias Mielke
 */
class PlaysStyleManager(workbook: XSSFWorkbook, private val username: String) : AbstractStyleManager(workbook) {

    // initialization
    init {
        registerStyle(
            PRIMARY_HEADER_STYLE,
            cellStyleBuilder
                .fontSize(12)
                .bold(true)
                .stringFormat()
                .backgroundColor(IndexedColors.LIGHT_GREEN)
                .build()
        )

        registerStyle(
            SECONDARY_HEADER_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .stringFormat()
                .backgroundColor(IndexedColors.LIGHT_GREEN)
                .build()
        )

        registerStyle(
            STRING_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .stringFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            STRING_DATA_BOLD_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(true)
                .stringFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            INT_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .intFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            TIMESTAMP_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .dateFormat()
                .backgroundColor(IndexedColors.LIGHT_GREEN)
                .build()
        )
    }


    // interface
    fun applyStyle(column: PlaysColumns, players: List<Player>, winner: Player?, cellBuilder: CellBuilder): CellBuilder {
        return when(column) {
            TIMESTAMP       -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)
            BOARD           -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)
            GENERATIONS     -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)

            PLAYER_1_NAME   -> applyPlayerNameStyle(players.getOrNull(0), username, cellBuilder)
            PLAYER_1_CORP   -> applyStyle(STRING_DATA_STYLE, cellBuilder)
            PLAYER_1_SCORE  -> applyScoreStyle(players.getOrNull(0), winner, cellBuilder)
            PLAYER_1_ELO    -> applyEloStyle(players.getOrNull(0), cellBuilder)

            PLAYER_2_NAME   -> applyPlayerNameStyle(players.getOrNull(1), username, cellBuilder)
            PLAYER_2_CORP   -> applyStyle(STRING_DATA_STYLE, cellBuilder)
            PLAYER_2_SCORE  -> applyScoreStyle(players.getOrNull(1), winner, cellBuilder)
            PLAYER_2_ELO    -> applyEloStyle(players.getOrNull(2), cellBuilder)

            PLAYER_3_NAME   -> applyPlayerNameStyle(players.getOrNull(2), username, cellBuilder)
            PLAYER_3_CORP   -> applyStyle(STRING_DATA_STYLE, cellBuilder)
            PLAYER_3_SCORE  -> applyScoreStyle(players.getOrNull(2), winner, cellBuilder)
            PLAYER_3_ELO    -> applyEloStyle(players.getOrNull(2), cellBuilder)

            PLAYER_4_NAME   -> applyPlayerNameStyle(players.getOrNull(3), username, cellBuilder)
            PLAYER_4_CORP   -> applyStyle(STRING_DATA_STYLE, cellBuilder)
            PLAYER_4_SCORE  -> applyScoreStyle(players.getOrNull(3), winner, cellBuilder)
            PLAYER_4_ELO    -> applyEloStyle(players.getOrNull(3), cellBuilder)

            PLAYER_5_NAME   -> applyPlayerNameStyle(players.getOrNull(4), username, cellBuilder)
            PLAYER_5_CORP   -> applyStyle(STRING_DATA_STYLE, cellBuilder)
            PLAYER_5_SCORE  -> applyScoreStyle(players.getOrNull(4), winner, cellBuilder)
            PLAYER_5_ELO    -> applyEloStyle(players.getOrNull(4), cellBuilder)
        }
    }


    // helpers
    private fun applyPlayerNameStyle(player: Player?, username: String, cellBuilder: CellBuilder): CellBuilder {
        val styleName = when {
            player != null && player.name == username -> STRING_DATA_BOLD_STYLE
            else -> STRING_DATA_STYLE
        }

        return applyStyle(styleName, cellBuilder)
    }

    private fun applyScoreStyle(player: Player?, winner: Player?, cellBuilder: CellBuilder): CellBuilder {
        val styleName = when (player) {
            null -> STRING_DATA_STYLE
            winner -> SECONDARY_HEADER_STYLE
            else -> INT_DATA_STYLE
        }

        return applyStyle(styleName, cellBuilder)
    }

    private fun applyEloStyle(player: Player?, cellBuilder: CellBuilder): CellBuilder {
        val styleName = when {
            player != null -> INT_DATA_STYLE
            else -> STRING_DATA_STYLE
        }

        return applyStyle(styleName, cellBuilder)
    }

}