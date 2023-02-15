package de.kayteem.apps.tfmgamelogconverter.controller.export.sheetBuilders

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellStyleBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetBuilders.PlaysSheetFactory.Companion.Columns.*
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A Factory for creating the "Plays" sheet in the Excel workbook.
 *
 * Author: Tobias Mielke
 */
class PlaysSheetFactory(private val workbook: XSSFWorkbook) {

    // dependencies
    private lateinit var _username: String


    // members
    private lateinit var _sheet: XSSFSheet
    private lateinit var _cellStyleBuilder: CellStyleBuilder

    private lateinit var _topHeaderCellStyle: XSSFCellStyle
    private lateinit var _bottomHeaderCellStyle: XSSFCellStyle
    private lateinit var _stringDataCellStyle: XSSFCellStyle
    private lateinit var _boldStringDataCellStyle: XSSFCellStyle
    private lateinit var _intDataCellStyle: XSSFCellStyle
    private lateinit var _timestampCellStyle: XSSFCellStyle


    // interface
    fun create(plays: List<Play>, username: String): XSSFSheet {
        _username = username
        _sheet = workbook.createSheet("Plays")

        buildCellStyles()
        setColumnsWidths()
        populateSheet(plays)
        mergeAndBorderHeaderCells()

        return _sheet
    }


    // helpers
    private fun buildCellStyles() {
        _cellStyleBuilder = CellStyleBuilder(workbook)

        _topHeaderCellStyle = _cellStyleBuilder
            .fontSize(12)
            .bold(true)
            .stringFormat()
            .backgroundColor(IndexedColors.LIGHT_GREEN)
            .build()

        _bottomHeaderCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .stringFormat()
            .backgroundColor(IndexedColors.LIGHT_GREEN)
            .build()

        _stringDataCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .stringFormat()
            .backgroundColor(IndexedColors.WHITE)
            .build()

        _boldStringDataCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(true)
            .stringFormat()
            .backgroundColor(IndexedColors.WHITE)
            .build()

        _intDataCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .intFormat()
            .backgroundColor(IndexedColors.WHITE)
            .build()

        _timestampCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .dateFormat()
            .backgroundColor(IndexedColors.LIGHT_GREEN)
            .build()
    }

    private fun setColumnsWidths() {
        Columns.values().forEach { column ->
            _sheet.setColumnWidth(column.idx(), columnWidths[column]!!)
        }
    }

    private fun mergeAndBorderHeaderCells() {
        mergeAndBorderCells(RANGE_TIMESTAMP)
        mergeAndBorderCells(RANGE_BOARD)
        mergeAndBorderCells(RANGE_GENERATIONS)
        mergeAndBorderCells(RANGE_PLAYER_1)
        mergeAndBorderCells(RANGE_PLAYER_2)
        mergeAndBorderCells(RANGE_PLAYER_3)
        mergeAndBorderCells(RANGE_PLAYER_4)
        mergeAndBorderCells(RANGE_PLAYER_5)
    }

    private fun mergeAndBorderCells(range: CellRangeAddress) {
        _sheet.addMergedRegion(range)

        RegionUtil.setBorderTop(BORDER_STYLE, range, _sheet)
        RegionUtil.setBorderLeft(BORDER_STYLE, range, _sheet)
        RegionUtil.setBorderRight(BORDER_STYLE, range, _sheet)
        RegionUtil.setBorderBottom(BORDER_STYLE, range, _sheet)
    }

    private fun populateSheet(plays: List<Play>) {

        // populate header rows
        populateTopHeaderRow()
        populateBottomHeaderRow()

        // populate data rows
        var firstDataRowIdx = ROW_IDX_FIRST_DATA_ROW
        plays.forEach { play ->
            populateDataRow(firstDataRowIdx, play)
            firstDataRowIdx++
        }

        // add filters
        _sheet.setAutoFilter(RANGE_FILTERS)
    }

    private fun populateTopHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_TOP_HEADER)

        val cellBuilder = CellBuilder(headerRow, _topHeaderCellStyle)

        Columns.values().forEach { column ->
            cellBuilder
                .columnIdx(column.idx())
                .build(topHeaderColumnNames.getOrDefault(column, ""))
        }
    }

    private fun populateBottomHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_BOTTOM_HEADER)

        val cellBuilder = CellBuilder(headerRow, _bottomHeaderCellStyle)

        Columns.values().forEach { column ->
            cellBuilder
                .columnIdx(column.idx())
                .build(bottomHeaderColumnNames.getOrDefault(column, ""))
        }
    }

    private fun populateDataRow(rowIdx: Int, play: Play) {
        val row = _sheet.createRow(rowIdx)

        val timestamp = LocalDateTime.parse(play.timestamp, DateTimeFormatter.ISO_DATE_TIME)
        val player1 = play.players.getOrNull(0)
        val player2 = play.players.getOrNull(1)
        val player3 = play.players.getOrNull(2)
        val player4 = play.players.getOrNull(3)
        val player5 = play.players.getOrNull(4)

        val cellBuilder = CellBuilder(row, _stringDataCellStyle)

        Columns.values().forEach { column ->

            cellBuilder.columnIdx(column.idx())

            when (column) {
                TIMESTAMP       -> cellBuilder.cellStyle(_timestampCellStyle).build(timestamp)
                BOARD           -> cellBuilder.cellStyle(_bottomHeaderCellStyle).build(play.board)
                GENERATIONS     -> cellBuilder.cellStyle(_bottomHeaderCellStyle).build(play.generations)

                PLAYER_1_NAME   -> populatePlayerNameCell(player1, cellBuilder)
                PLAYER_1_CORP   -> populateStringCell(player1?.corporation, cellBuilder)
                PLAYER_1_SCORE  -> populateIntCell(player1?.finalScore, cellBuilder)
                PLAYER_1_ELO    -> populateIntCell(player1?.elo, cellBuilder)

                PLAYER_2_NAME   -> populatePlayerNameCell(player2, cellBuilder)
                PLAYER_2_CORP   -> populateStringCell(player2?.corporation, cellBuilder)
                PLAYER_2_SCORE  -> populateIntCell(player2?.finalScore, cellBuilder)
                PLAYER_2_ELO    -> populateIntCell(player2?.elo, cellBuilder)

                PLAYER_3_NAME   -> populatePlayerNameCell(player3, cellBuilder)
                PLAYER_3_CORP   -> populateStringCell(player3?.corporation, cellBuilder)
                PLAYER_3_SCORE  -> populateIntCell(player3?.finalScore, cellBuilder)
                PLAYER_3_ELO    -> populateIntCell(player3?.elo, cellBuilder)

                PLAYER_4_NAME   -> populatePlayerNameCell(player4, cellBuilder)
                PLAYER_4_CORP   -> populateStringCell(player4?.corporation, cellBuilder)
                PLAYER_4_SCORE  -> populateIntCell(player4?.finalScore, cellBuilder)
                PLAYER_4_ELO    -> populateIntCell(player4?.elo, cellBuilder)

                PLAYER_5_NAME   -> populatePlayerNameCell(player5, cellBuilder)
                PLAYER_5_CORP   -> populateStringCell(player5?.corporation, cellBuilder)
                PLAYER_5_SCORE  -> populateIntCell(player5?.finalScore, cellBuilder)
                PLAYER_5_ELO    -> populateIntCell(player5?.elo, cellBuilder)
            }
        }
    }

    private fun populatePlayerNameCell(player: Player?, cellBuilder: CellBuilder) {
        val cellStyle =
            if (player != null && player.name == _username) _boldStringDataCellStyle
            else _stringDataCellStyle

        val value = player?.name ?: ""
        
        cellBuilder.cellStyle(cellStyle).build(value)
    }

    private fun populateStringCell(value: String?, cellBuilder: CellBuilder) {
        cellBuilder.cellStyle(_stringDataCellStyle).build(value ?: "")
    }

    private fun populateIntCell(value: Int?, cellBuilder: CellBuilder) {
        cellBuilder.cellStyle(_intDataCellStyle).build(value ?: 0)
    }


    // companion
    companion object {

        // columns
        enum class Columns {
            TIMESTAMP,
            BOARD,
            GENERATIONS,
            PLAYER_1_NAME,
            PLAYER_1_CORP,
            PLAYER_1_SCORE,
            PLAYER_1_ELO,
            PLAYER_2_NAME,
            PLAYER_2_CORP,
            PLAYER_2_SCORE,
            PLAYER_2_ELO,
            PLAYER_3_NAME,
            PLAYER_3_CORP,
            PLAYER_3_SCORE,
            PLAYER_3_ELO,
            PLAYER_4_NAME,
            PLAYER_4_CORP,
            PLAYER_4_SCORE,
            PLAYER_4_ELO,
            PLAYER_5_NAME,
            PLAYER_5_CORP,
            PLAYER_5_SCORE,
            PLAYER_5_ELO;

            fun idx(): Int {
                return ordinal
            }
        }

        // header column names
        private const val HEADER_STR_TIMESTAMP = "Timestamp"
        private const val HEADER_STR_BOARD = "Board"
        private const val HEADER_STR_GENERATIONS = "Gens"
        private const val HEADER_STR_PLAYER_1 = "Player 1"
        private const val HEADER_STR_PLAYER_2 = "Player 2"
        private const val HEADER_STR_PLAYER_3 = "Player 3"
        private const val HEADER_STR_PLAYER_4 = "Player 4"
        private const val HEADER_STR_PLAYER_5 = "Player 5"
        private const val HEADER_STR_NAME = "Name"
        private const val HEADER_STR_CORP = "Corporation"
        private const val HEADER_STR_SCORE = "Score"
        private const val HEADER_STR_ELO = "ELO"
        
        val topHeaderColumnNames = mapOf(
            TIMESTAMP to HEADER_STR_TIMESTAMP,
            BOARD to HEADER_STR_BOARD,
            GENERATIONS to HEADER_STR_GENERATIONS,
            PLAYER_1_NAME to HEADER_STR_PLAYER_1,
            PLAYER_2_NAME to HEADER_STR_PLAYER_2,
            PLAYER_3_NAME to HEADER_STR_PLAYER_3,
            PLAYER_4_NAME to HEADER_STR_PLAYER_4,
            PLAYER_5_NAME to HEADER_STR_PLAYER_5,
        )

        val bottomHeaderColumnNames = mapOf(
            PLAYER_1_NAME to HEADER_STR_NAME,
            PLAYER_1_CORP to HEADER_STR_CORP,
            PLAYER_1_SCORE to HEADER_STR_SCORE,
            PLAYER_1_ELO to HEADER_STR_ELO,
            PLAYER_2_NAME to HEADER_STR_NAME,
            PLAYER_2_CORP to HEADER_STR_CORP,
            PLAYER_2_SCORE to HEADER_STR_SCORE,
            PLAYER_2_ELO to HEADER_STR_ELO,
            PLAYER_3_NAME to HEADER_STR_NAME,
            PLAYER_3_CORP to HEADER_STR_CORP,
            PLAYER_3_SCORE to HEADER_STR_SCORE,
            PLAYER_3_ELO to HEADER_STR_ELO,
            PLAYER_4_NAME to HEADER_STR_NAME,
            PLAYER_4_CORP to HEADER_STR_CORP,
            PLAYER_4_SCORE to HEADER_STR_SCORE,
            PLAYER_4_ELO to HEADER_STR_ELO,
            PLAYER_5_NAME to HEADER_STR_NAME,
            PLAYER_5_CORP to HEADER_STR_CORP,
            PLAYER_5_SCORE to HEADER_STR_SCORE,
            PLAYER_5_ELO to HEADER_STR_ELO,
        )


        // column widths
        private const val COL_WIDTH_TIMESTAMP = 5000
        private const val COL_WIDTH_BOARD = 2500
        private const val COL_WIDTH_GENERATIONS = 2500
        private const val COL_WIDTH_PLAYER_NAME = 4000
        private const val COL_WIDTH_PLAYER_CORP = 5500
        private const val COL_WIDTH_PLAYER_SCORE = 2500
        private const val COL_WIDTH_PLAYER_ELO = 2500

        val columnWidths = mapOf(
            TIMESTAMP to COL_WIDTH_TIMESTAMP,
            BOARD to COL_WIDTH_BOARD,
            GENERATIONS to COL_WIDTH_GENERATIONS,
            PLAYER_1_NAME to COL_WIDTH_PLAYER_NAME,
            PLAYER_1_CORP to COL_WIDTH_PLAYER_CORP,
            PLAYER_1_SCORE to COL_WIDTH_PLAYER_SCORE,
            PLAYER_1_ELO to COL_WIDTH_PLAYER_ELO,
            PLAYER_2_NAME to COL_WIDTH_PLAYER_NAME,
            PLAYER_2_CORP to COL_WIDTH_PLAYER_CORP,
            PLAYER_2_SCORE to COL_WIDTH_PLAYER_SCORE,
            PLAYER_2_ELO to COL_WIDTH_PLAYER_ELO,
            PLAYER_3_NAME to COL_WIDTH_PLAYER_NAME,
            PLAYER_3_CORP to COL_WIDTH_PLAYER_CORP,
            PLAYER_3_SCORE to COL_WIDTH_PLAYER_SCORE,
            PLAYER_3_ELO to COL_WIDTH_PLAYER_ELO,
            PLAYER_4_NAME to COL_WIDTH_PLAYER_NAME,
            PLAYER_4_CORP to COL_WIDTH_PLAYER_CORP,
            PLAYER_4_SCORE to COL_WIDTH_PLAYER_SCORE,
            PLAYER_4_ELO to COL_WIDTH_PLAYER_ELO,
            PLAYER_5_NAME to COL_WIDTH_PLAYER_NAME,
            PLAYER_5_CORP to COL_WIDTH_PLAYER_CORP,
            PLAYER_5_SCORE to COL_WIDTH_PLAYER_SCORE,
            PLAYER_5_ELO to COL_WIDTH_PLAYER_ELO,
        )


        // row indices
        const val ROW_IDX_TOP_HEADER = 0
        const val ROW_IDX_BOTTOM_HEADER = 1
        const val ROW_IDX_FIRST_DATA_ROW = 2

        // cell range addresses (for merged regions and auto-filters)
        val RANGE_TIMESTAMP = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, TIMESTAMP.idx(), TIMESTAMP.idx())
        val RANGE_BOARD = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, BOARD.idx(), BOARD.idx())
        val RANGE_GENERATIONS = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, GENERATIONS.idx(), GENERATIONS.idx())
        val RANGE_PLAYER_1 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYER_1_NAME.idx(), PLAYER_1_ELO.idx())
        val RANGE_PLAYER_2 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYER_2_NAME.idx(), PLAYER_2_ELO.idx())
        val RANGE_PLAYER_3 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYER_3_NAME.idx(), PLAYER_3_ELO.idx())
        val RANGE_PLAYER_4 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYER_4_NAME.idx(), PLAYER_4_ELO.idx())
        val RANGE_PLAYER_5 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, PLAYER_5_NAME.idx(), PLAYER_5_ELO.idx())
        val RANGE_FILTERS = CellRangeAddress(ROW_IDX_BOTTOM_HEADER, ROW_IDX_BOTTOM_HEADER, TIMESTAMP.idx(), PLAYER_5_ELO.idx())

        // border style
        val BORDER_STYLE = BorderStyle.THIN

    }

}