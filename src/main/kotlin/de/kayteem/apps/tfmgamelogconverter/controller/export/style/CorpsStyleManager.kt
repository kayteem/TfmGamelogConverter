package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.CorpsSheetFactory.Companion.CorpsColumns
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.CorpsSheetFactory.Companion.CorpsColumns.*
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Creates and manages cell styles for the "Corps" sheet.
 *
 * TODO:
 * - use shadowing!
 * - extract common code into AbstractStyleManager!
 *
 * Author: Tobias Mielke
 */
class CorpsStyleManager(workbook: XSSFWorkbook) : AbstractStyleManager(workbook) {

    // interface
    fun applyStyle(column: CorpsColumns, cellBuilder: CellBuilder): CellBuilder {
        return when(column) {
            CORPORATION         -> applyCorporationStyle(cellBuilder)
            BOARD               -> applyBoardStyle(cellBuilder)

            PLAYED_BY_YOU       -> applyPlayedStyle(false, cellBuilder)
            PLAYED_BY_OPPONENTS -> applyPlayedStyle(false, cellBuilder)
            PLAYED_TOTAL        -> applyPlayedTotalStyle(false, cellBuilder)
        }
    }

    fun applyPrimaryHeaderStyle(cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(12)
            .bold(true)
            .stringFormat()
            .textColor(IndexedColors.BLACK)
            .cellForegroundColor(IndexedColors.LIGHT_GREEN)
            .cellPattern(FillPatternType.SOLID_FOREGROUND)
            .build()

        return cellBuilder.cellStyle(style)
    }

    fun applySecondaryHeaderStyle(cellBuilder: CellBuilder): CellBuilder {
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

    private fun applyPlayedStyle(shadowed: Boolean, cellBuilder: CellBuilder): CellBuilder {
        val style = cellStyleBuilder
            .fontSize(10)
            .bold(false)
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


    private fun getBgColor(shadowed: Boolean): IndexedColors {
        return if (shadowed) IndexedColors.LIGHT_GREEN else IndexedColors.WHITE
    }

    private fun getPattern(shadowed: Boolean): FillPatternType {
        return if (shadowed) FillPatternType.SPARSE_DOTS else FillPatternType.SOLID_FOREGROUND
    }

}

