package de.kayteem.apps.tfmgamelogconverter.controller.export.styleManagers

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.CorpsSheetFactory.Companion.CorpsColumns
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.CorpsSheetFactory.Companion.CorpsColumns.*
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Creates and manages cell styles for the "Corps" sheet.
 *
 * TODO: use shadowing!
 *
 * Author: Tobias Mielke
 */
class CorpsStyleManager(workbook: XSSFWorkbook) : AbstractStyleManager(workbook) {

    // interface
    fun applyStyle(column: CorpsColumns, boldRow: Boolean, cellBuilder: CellBuilder): CellBuilder {
        return when(column) {
            CORPORATION         -> applyCorporationStyle(cellBuilder)
            BOARD               -> applyBoardStyle(cellBuilder)

            PLAYED_BY_YOU       -> applyPlayedStyle(boldRow, false, cellBuilder)
            PLAYED_BY_OPPONENTS -> applyPlayedStyle(boldRow, false, cellBuilder)
            PLAYED_TOTAL        -> applyPlayedTotalStyle(false, cellBuilder)
        }
    }


    // helpers
    private fun applyCorporationStyle(cellBuilder: CellBuilder): CellBuilder {
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

    private fun applyBoardStyle(cellBuilder: CellBuilder): CellBuilder {
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

    private fun applyPlayedStyle(bold: Boolean, shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(bold)
            .intFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

    private fun applyPlayedTotalStyle(shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(true)
            .intFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(getBgColor(shadowed))
            .cellPattern(getPattern(shadowed))
            .build()

        return cellBuilder.cellStyle(style)
    }

}
