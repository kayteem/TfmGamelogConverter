package de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.CorpsSheetFactory.Companion.CorpsColumns.*
import de.kayteem.apps.tfmgamelogconverter.controller.export.styleManagers.CorpsStyleManager
import de.kayteem.apps.tfmgamelogconverter.model.internal.Boards
import de.kayteem.apps.tfmgamelogconverter.model.internal.Corporation
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * A Factory for creating the "Corps" sheet in the Excel workbook.
 *
 * Author: Tobias Mielke
 */
class CorpsSheetFactory(private val workbook: XSSFWorkbook) : AbstractSheetFactory() {

    // members
    private lateinit var _styleManager: CorpsStyleManager

    
    // interface
    fun create(corps: List<Corporation>): XSSFSheet {
        sheet = workbook.createSheet("Corps")
        _styleManager = CorpsStyleManager(workbook)

        setColumnsWidths(columnWidths)
        populateSheet(corps)
        mergeAndBorderHeaderCells()

        return sheet
    }


    // helpers
    private fun populateSheet(corps: List<Corporation>) {

        // populate header rows
        populateHeaderRow(ROW_IDX_TOP_HEADER, topHeaderColumnNames, _styleManager.buildPrimaryHeaderStyle())
        populateHeaderRow(ROW_IDX_BOTTOM_HEADER, bottomHeaderColumnNames, _styleManager.buildSecondaryHeaderStyle())

        // populate data rows
        var rowIdx = ROW_IDX_FIRST_DATA_ROW
        corps.forEach { corp ->

            // row per board
            Boards.values().forEach { board ->
                populateDataRow(rowIdx, corp, board)
                rowIdx++
            }

            // total row
            populateDataRow(rowIdx, corp)
            rowIdx++
        }

        // add filters
        sheet.setAutoFilter(RANGE_FILTERS)
    }

    private fun populateDataRow(rowIdx: Int, corp: Corporation, board: Boards? = null) {
        val row = sheet.createRow(rowIdx)
        val cellBuilder = CellBuilder(row)

        val corpName = corp.name
        val boardName = board?.mapName ?: "Total"

        val playedByYou: Int =
            if (board != null) corp.playedOnMapByYou.getOrDefault(board, 0)
            else corp.playedByYou()

        val playedByOpponents: Int =
            if (board != null) corp.playedOnMapByOpponents.getOrDefault(board, 0)
            else corp.playedByOpponents()

        val playedTotal: Int =
            if (board != null) corp.playedOnMapTotal().getOrDefault(board, 0)
            else corp.playedTotal()

        CorpsColumns.values().forEach { column ->
            with(cellBuilder) {
                _styleManager
                    .applyStyle(column, cellBuilder)
                    .columnIdx(column.ordinal)

                when (column) {
                    CORPORATION         -> build(corpName)
                    BOARD               -> build(boardName)

                    PLAYED_BY_YOU       -> build(playedByYou)
                    PLAYED_BY_OPPONENTS -> build(playedByOpponents)
                    PLAYED_TOTAL        -> build(playedTotal)
                }
            }
        }
    }

    private fun mergeAndBorderHeaderCells() {
        mergeAndBorderCells(RANGE_CORPORATION, BORDER_STYLE)
        mergeAndBorderCells(RANGE_BOARD, BORDER_STYLE)
        mergeAndBorderCells(RANGE_PLAYED, BORDER_STYLE)
    }


    // companion
    companion object {

        // columns
        enum class CorpsColumns {
            CORPORATION,
            BOARD,
            PLAYED_BY_YOU,
            PLAYED_BY_OPPONENTS,
            PLAYED_TOTAL,
        }

        // header column names
        private const val HEADER_STR_CORPORTION = "Corporation"
        private const val HEADER_STR_BOARD = "Board"
        
        private const val HEADER_STR_PLAYED = "Played"
        private const val HEADER_STR_PLAYED_BY_YOU = "By You"
        private const val HEADER_STR_PLAYED_BY_OPPONENTS = "By Opponents"
        private const val HEADER_STR_PLAYED_TOTAL = "Total"

        val topHeaderColumnNames = mapOf(
            CORPORATION to HEADER_STR_CORPORTION,
            BOARD to HEADER_STR_BOARD,
            PLAYED_BY_YOU to HEADER_STR_PLAYED,
        )

        val bottomHeaderColumnNames = mapOf(
            PLAYED_BY_YOU to HEADER_STR_PLAYED_BY_YOU,
            PLAYED_BY_OPPONENTS to HEADER_STR_PLAYED_BY_OPPONENTS,
            PLAYED_TOTAL to HEADER_STR_PLAYED_TOTAL,
        )


        // column widths
        private const val COL_WIDTH_CORPORATION = 6500
        private const val COL_WIDTH_BOARD = 2500
        private const val COL_WIDTH_PLAYED_BY_YOU = 4000
        private const val COL_WIDTH_PLAYED_BY_OPPONENTS = 4000
        private const val COL_WIDTH_PLAYED_TOTAL = 4000

        val columnWidths = mapOf(
            CORPORATION to COL_WIDTH_CORPORATION,
            BOARD to COL_WIDTH_BOARD,
            PLAYED_BY_YOU to COL_WIDTH_PLAYED_BY_YOU,
            PLAYED_BY_OPPONENTS to COL_WIDTH_PLAYED_BY_OPPONENTS,
            PLAYED_TOTAL to COL_WIDTH_PLAYED_TOTAL,
        )


        // row indices
        const val ROW_IDX_TOP_HEADER = 0
        const val ROW_IDX_BOTTOM_HEADER = 1
        const val ROW_IDX_FIRST_DATA_ROW = 2

        // cell range addresses (for merged regions and auto-filters)
        val RANGE_CORPORATION = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, CORPORATION.ordinal, CORPORATION.ordinal)
        val RANGE_BOARD = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, BOARD.ordinal, BOARD.ordinal)
        val RANGE_PLAYED = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYED_BY_YOU.ordinal, PLAYED_TOTAL.ordinal)
        val RANGE_FILTERS = CellRangeAddress(ROW_IDX_BOTTOM_HEADER, ROW_IDX_BOTTOM_HEADER, CORPORATION.ordinal, PLAYED_TOTAL.ordinal)

        // border style
        val BORDER_STYLE = BorderStyle.THIN

    }

}