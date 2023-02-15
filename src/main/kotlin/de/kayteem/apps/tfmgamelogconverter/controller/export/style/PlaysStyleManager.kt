package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.PlaysSheetFactory.Companion.PlaysColumns
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.PlaysSheetFactory.Companion.PlaysColumns.*
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.CENTERED_INT_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.CENTERED_STRING_DATA_BOLD_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.CENTERED_STRING_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.CENTERED_TIMESTAMP_DATA_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.PRIMARY_HEADER_STYLE
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.StyleManager.Companion.SECONDARY_HEADER_STYLE
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
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
            CENTERED_STRING_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .stringFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            CENTERED_STRING_DATA_BOLD_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(true)
                .stringFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            CENTERED_INT_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .intFormat()
                .backgroundColor(IndexedColors.WHITE)
                .build()
        )

        registerStyle(
            CENTERED_TIMESTAMP_DATA_STYLE,
            cellStyleBuilder
                .fontSize(10)
                .bold(false)
                .dateFormat()
                .backgroundColor(IndexedColors.LIGHT_GREEN)
                .build()
        )
    }


    // interface
    fun applyStyle(column: PlaysColumns, play: Play, cellBuilder: CellBuilder): CellBuilder {
        return when(column) {
            TIMESTAMP       -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)
            BOARD           -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)
            GENERATIONS     -> applyStyle(SECONDARY_HEADER_STYLE, cellBuilder)

            PLAYER_1_NAME   -> applyPlayerNameStyle(play.players.getOrNull(0), username, cellBuilder)
            PLAYER_1_CORP   -> applyStyle(CENTERED_STRING_DATA_STYLE, cellBuilder)
            PLAYER_1_SCORE  -> applyIntDataStyle(play.players.getOrNull(0), cellBuilder)
            PLAYER_1_ELO    -> applyIntDataStyle(play.players.getOrNull(0), cellBuilder)

            PLAYER_2_NAME   -> applyPlayerNameStyle(play.players.getOrNull(1), username, cellBuilder)
            PLAYER_2_CORP   -> applyStyle(CENTERED_STRING_DATA_STYLE, cellBuilder)
            PLAYER_2_SCORE  -> applyIntDataStyle(play.players.getOrNull(1), cellBuilder)
            PLAYER_2_ELO    -> applyIntDataStyle(play.players.getOrNull(2), cellBuilder)

            PLAYER_3_NAME   -> applyPlayerNameStyle(play.players.getOrNull(2), username, cellBuilder)
            PLAYER_3_CORP   -> applyStyle(CENTERED_STRING_DATA_STYLE, cellBuilder)
            PLAYER_3_SCORE  -> applyIntDataStyle(play.players.getOrNull(2), cellBuilder)
            PLAYER_3_ELO    -> applyIntDataStyle(play.players.getOrNull(2), cellBuilder)

            PLAYER_4_NAME   -> applyPlayerNameStyle(play.players.getOrNull(3), username, cellBuilder)
            PLAYER_4_CORP   -> applyStyle(CENTERED_STRING_DATA_STYLE, cellBuilder)
            PLAYER_4_SCORE  -> applyIntDataStyle(play.players.getOrNull(3), cellBuilder)
            PLAYER_4_ELO    -> applyIntDataStyle(play.players.getOrNull(3), cellBuilder)

            PLAYER_5_NAME   -> applyPlayerNameStyle(play.players.getOrNull(4), username, cellBuilder)
            PLAYER_5_CORP   -> applyStyle(CENTERED_STRING_DATA_STYLE, cellBuilder)
            PLAYER_5_SCORE  -> applyIntDataStyle(play.players.getOrNull(4), cellBuilder)
            PLAYER_5_ELO    -> applyIntDataStyle(play.players.getOrNull(4), cellBuilder)
        }
    }


    // helpers
    private fun applyPlayerNameStyle(player: Player?, username: String, cellBuilder: CellBuilder): CellBuilder {
        val styleName =
            if (player != null && player.name == username) CENTERED_STRING_DATA_BOLD_STYLE
            else CENTERED_STRING_DATA_STYLE

        return applyStyle(styleName, cellBuilder)
    }

    private fun applyIntDataStyle(player: Player?, cellBuilder: CellBuilder): CellBuilder {
        val styleName =
            if (player != null) CENTERED_INT_DATA_STYLE
            else CENTERED_STRING_DATA_STYLE

        return applyStyle(styleName, cellBuilder)
    }

}