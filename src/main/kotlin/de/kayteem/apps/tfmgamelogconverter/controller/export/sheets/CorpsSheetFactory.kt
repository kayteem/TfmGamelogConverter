package de.kayteem.apps.tfmgamelogconverter.controller.export.sheets

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.CorpsSheetFactory.Companion.CorpsColumns.*
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.CorpsStyleManager
import de.kayteem.apps.tfmgamelogconverter.model.internal.Boards
import de.kayteem.apps.tfmgamelogconverter.model.internal.Corporation
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
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

        setColumnsWidths()
        populateSheet(corps)
        mergeAndBorderHeaderCells()

        return sheet
    }


    // helpers
    private fun setColumnsWidths() {
        CorpsColumns.values().forEach { column ->
            sheet.setColumnWidth(column.idx(), columnWidths[column]!!)
        }
    }

    private fun mergeAndBorderHeaderCells() {
        mergeAndBorderCells(RANGE_CORPORATION)
        mergeAndBorderCells(RANGE_BOARD)
        mergeAndBorderCells(RANGE_PLAYED)
    }

    private fun mergeAndBorderCells(range: CellRangeAddress) {
        sheet.addMergedRegion(range)

        RegionUtil.setBorderTop(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderLeft(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderRight(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderBottom(BORDER_STYLE, range, sheet)
    }

    private fun populateSheet(corps: List<Corporation>) {

        // populate header rows
        populateTopHeaderRow()
        populateBottomHeaderRow()

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

    private fun populateTopHeaderRow() {
        val headerRow = sheet.createRow(ROW_IDX_TOP_HEADER)
        val cellBuilder = CellBuilder(headerRow)

        CorpsColumns.values().forEach { column ->
            _styleManager
                .applyPrimaryHeaderStyle(cellBuilder)
                .columnIdx(column.idx())
                .build(topHeaderColumnNames[column])
        }
    }

    private fun populateBottomHeaderRow() {
        val headerRow = sheet.createRow(ROW_IDX_BOTTOM_HEADER)
        val cellBuilder = CellBuilder(headerRow)

        CorpsColumns.values().forEach { column ->
            _styleManager
                .applySecondaryHeaderStyle(cellBuilder)
                .columnIdx(column.idx())
                .build(bottomHeaderColumnNames[column])
        }
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
                    .columnIdx(column.idx())

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


    // companion
    companion object {

        // columns
        enum class CorpsColumns {
            CORPORATION,
            BOARD,
            PLAYED_BY_YOU,
            PLAYED_BY_OPPONENTS,
            PLAYED_TOTAL;

            fun idx(): Int {
                return ordinal
            }
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
        val RANGE_CORPORATION = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, CORPORATION.idx(), CORPORATION.idx())
        val RANGE_BOARD = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, BOARD.idx(), BOARD.idx())
        val RANGE_PLAYED = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYED_BY_YOU.idx(), PLAYED_TOTAL.idx())
        val RANGE_FILTERS = CellRangeAddress(ROW_IDX_BOTTOM_HEADER, ROW_IDX_BOTTOM_HEADER, CORPORATION.idx(), PLAYED_TOTAL.idx())

        // border style
        val BORDER_STYLE = BorderStyle.THIN

    }

}