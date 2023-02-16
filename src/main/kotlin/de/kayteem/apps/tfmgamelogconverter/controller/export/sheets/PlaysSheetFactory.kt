package de.kayteem.apps.tfmgamelogconverter.controller.export.sheets

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheets.PlaysSheetFactory.Companion.PlaysColumns.*
import de.kayteem.apps.tfmgamelogconverter.controller.export.style.PlaysStyleManager
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A Factory for creating the "Plays" sheet in the Excel workbook.
 *
 * Author: Tobias Mielke
 */
class PlaysSheetFactory(private val workbook: XSSFWorkbook, private val username: String) : AbstractSheetFactory() {

    // members
    private lateinit var _styleManager: PlaysStyleManager

    
    // interface
    fun create(plays: List<Play>): XSSFSheet {
        sheet = workbook.createSheet("Plays")
        _styleManager = PlaysStyleManager(workbook, username)

        setColumnsWidths()
        populateSheet(plays)
        mergeAndBorderHeaderCells()

        return sheet
    }


    // helpers
    private fun setColumnsWidths() {
        PlaysColumns.values().forEach { column ->
            sheet.setColumnWidth(column.idx(), columnWidths[column]!!)
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
        sheet.addMergedRegion(range)

        RegionUtil.setBorderTop(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderLeft(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderRight(BORDER_STYLE, range, sheet)
        RegionUtil.setBorderBottom(BORDER_STYLE, range, sheet)
    }

    private fun populateSheet(plays: List<Play>) {

        // populate header rows
        populateTopHeaderRow()
        populateBottomHeaderRow()

        // populate data rows
        var rowIdx = ROW_IDX_FIRST_DATA_ROW
        plays.forEach { play ->
            populateDataRow(rowIdx, play)
            rowIdx++
        }

        // add filters
        sheet.setAutoFilter(RANGE_FILTERS)
    }

    private fun populateTopHeaderRow() {
        val headerRow = sheet.createRow(ROW_IDX_TOP_HEADER)
        val cellBuilder = CellBuilder(headerRow)

        PlaysColumns.values().forEach { column ->
            _styleManager
                .applyPrimaryHeaderStyle(cellBuilder)
                .columnIdx(column.idx())
                .build(topHeaderColumnNames[column])
        }
    }

    private fun populateBottomHeaderRow() {
        val headerRow = sheet.createRow(ROW_IDX_BOTTOM_HEADER)
        val cellBuilder = CellBuilder(headerRow)

        PlaysColumns.values().forEach { column ->
            _styleManager
                .applySecondaryHeaderStyle(cellBuilder)
                .columnIdx(column.idx())
                .build(bottomHeaderColumnNames[column])
        }
    }

    private fun populateDataRow(rowIdx: Int, play: Play) {
        val row = sheet.createRow(rowIdx)
        val cellBuilder = CellBuilder(row)

        val timestamp = LocalDateTime.parse(play.timestamp, DateTimeFormatter.ISO_DATE_TIME)
        val players = play.players
        val winner = play.winner()
        val player1 = players.getOrNull(0)
        val player2 = players.getOrNull(1)
        val player3 = players.getOrNull(2)
        val player4 = players.getOrNull(3)
        val player5 = players.getOrNull(4)

        PlaysColumns.values().forEach { column ->
            with(cellBuilder) {
                _styleManager
                    .applyStyle(column, players, winner, cellBuilder)
                    .columnIdx(column.idx())

                when (column) {
                    TIMESTAMP       -> build(timestamp)
                    BOARD           -> build(play.board)
                    GENERATIONS     -> build(play.generations)

                    PLAYER_1_NAME   -> build(player1?.name)
                    PLAYER_1_CORP   -> build(player1?.corporation)
                    PLAYER_1_SCORE  -> build(player1?.finalScore)
                    PLAYER_1_ELO    -> build(player1?.elo)

                    PLAYER_2_NAME   -> build(player2?.name)
                    PLAYER_2_CORP   -> build(player2?.corporation)
                    PLAYER_2_SCORE  -> build(player2?.finalScore)
                    PLAYER_2_ELO    -> build(player2?.elo)

                    PLAYER_3_NAME   -> build(player3?.name)
                    PLAYER_3_CORP   -> build(player3?.corporation)
                    PLAYER_3_SCORE  -> build(player3?.finalScore)
                    PLAYER_3_ELO    -> build(player3?.elo)

                    PLAYER_4_NAME   -> build(player4?.name)
                    PLAYER_4_CORP   -> build(player4?.corporation)
                    PLAYER_4_SCORE  -> build(player4?.finalScore)
                    PLAYER_4_ELO    -> build(player4?.elo)

                    PLAYER_5_NAME   -> build(player5?.name)
                    PLAYER_5_CORP   -> build(player5?.corporation)
                    PLAYER_5_SCORE  -> build(player5?.finalScore)
                    PLAYER_5_ELO    -> build(player5?.elo)
                }
            }
        }
    }


    // companion
    companion object {

        // columns
        enum class PlaysColumns {
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