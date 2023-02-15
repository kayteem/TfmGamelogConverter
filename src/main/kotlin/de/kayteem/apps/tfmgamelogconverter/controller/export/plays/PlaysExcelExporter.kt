package de.kayteem.apps.tfmgamelogconverter.controller.export.plays

import de.kayteem.apps.tfmgamelogconverter.controller.converters.PlayToExcelPlayConverter
import de.kayteem.apps.tfmgamelogconverter.controller.converters.PlaysToUsernameConverter
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellStyleBuilder
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.ExcelPlay
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Implementation for exporting Play objects to an Excel file.
 *
 * Excel export tutorials:
 * https://www.developersoapbox.com/basic-read-and-write-excel-using-kotlin/
 * https://www.baeldung.com/apache-poi-background-color
 *
 * Author: Tobias Mielke
 */
class PlaysExcelExporter : PlaysExporter {

    // members
    private lateinit var _username: String

    private lateinit var _workbook: XSSFWorkbook
    private lateinit var _sheet: XSSFSheet
    private lateinit var _cellStyleBuilder: CellStyleBuilder

    private lateinit var _topHeaderCellStyle: XSSFCellStyle
    private lateinit var _bottomHeaderCellStyle: XSSFCellStyle
    private lateinit var _stringDataCenterCellStyle: XSSFCellStyle
    private lateinit var _boldStringDataCenterCellStyle: XSSFCellStyle
    private lateinit var _intDataCellStyle: XSSFCellStyle
    private lateinit var _timestampCellStyle: XSSFCellStyle


    // interface
    override fun export(path: Path, plays: List<Play>) {
        val playToExcelPlayConverter = PlayToExcelPlayConverter()
        val excelPlays = plays.map { playToExcelPlayConverter.process(it) }

        val playsToUsernameConverter = PlaysToUsernameConverter()
        _username = playsToUsernameConverter.process(plays)

        _workbook = XSSFWorkbook()
        _sheet = _workbook.createSheet("Plays")

        buildCellStyles()
        setColumnsWidths()
        populateSheet(excelPlays)
        mergeAndBorderHeaderCells()

        val fos = FileOutputStream(path.toFile())
        _workbook.write(fos)
        _workbook.close()
    }


    // helpers
    private fun buildCellStyles() {
        _cellStyleBuilder = CellStyleBuilder(_workbook)

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

        _stringDataCenterCellStyle = _cellStyleBuilder
            .fontSize(10)
            .bold(false)
            .stringFormat()
            .backgroundColor(IndexedColors.WHITE)
            .build()

        _boldStringDataCenterCellStyle = _cellStyleBuilder
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
        with(_sheet) {
            setColumnWidth(COL_IDX_TIMESTAMP, COL_WIDTH_TIMESTAMP)
            setColumnWidth(COL_IDX_BOARD, COL_WIDTH_BOARD)
            setColumnWidth(COL_IDX_GENERATIONS, COL_WIDTH_GENERATIONS)
            setColumnWidth(COL_IDX_PLAYER_1_NAME, COL_WIDTH_PLAYER_NAME)
            setColumnWidth(COL_IDX_PLAYER_1_CORP, COL_WIDTH_PLAYER_CORP)
            setColumnWidth(COL_IDX_PLAYER_1_SCORE, COL_WIDTH_PLAYER_SCORE)
            setColumnWidth(COL_IDX_PLAYER_1_ELO, COL_WIDTH_PLAYER_ELO)
            setColumnWidth(COL_IDX_PLAYER_2_NAME, COL_WIDTH_PLAYER_NAME)
            setColumnWidth(COL_IDX_PLAYER_2_CORP, COL_WIDTH_PLAYER_CORP)
            setColumnWidth(COL_IDX_PLAYER_2_SCORE, COL_WIDTH_PLAYER_SCORE)
            setColumnWidth(COL_IDX_PLAYER_2_ELO, COL_WIDTH_PLAYER_ELO)
            setColumnWidth(COL_IDX_PLAYER_3_NAME, COL_WIDTH_PLAYER_NAME)
            setColumnWidth(COL_IDX_PLAYER_3_CORP, COL_WIDTH_PLAYER_CORP)
            setColumnWidth(COL_IDX_PLAYER_3_SCORE, COL_WIDTH_PLAYER_SCORE)
            setColumnWidth(COL_IDX_PLAYER_3_ELO, COL_WIDTH_PLAYER_ELO)
            setColumnWidth(COL_IDX_PLAYER_4_NAME, COL_WIDTH_PLAYER_NAME)
            setColumnWidth(COL_IDX_PLAYER_4_CORP, COL_WIDTH_PLAYER_CORP)
            setColumnWidth(COL_IDX_PLAYER_4_SCORE, COL_WIDTH_PLAYER_SCORE)
            setColumnWidth(COL_IDX_PLAYER_4_ELO, COL_WIDTH_PLAYER_ELO)
            setColumnWidth(COL_IDX_PLAYER_5_NAME, COL_WIDTH_PLAYER_NAME)
            setColumnWidth(COL_IDX_PLAYER_5_CORP, COL_WIDTH_PLAYER_CORP)
            setColumnWidth(COL_IDX_PLAYER_5_SCORE, COL_WIDTH_PLAYER_SCORE)
            setColumnWidth(COL_IDX_PLAYER_5_ELO, COL_WIDTH_PLAYER_ELO)
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

    private fun populateSheet(excelPlays: List<ExcelPlay>) {

        // populate header rows
        populateTopHeaderRow()
        populateBottomHeaderRow()

        // populate data rows
        var firstDataRowIdx = ROW_IDX_FIRST_DATA_ROW
        excelPlays.forEach { excelPlay ->
            populateDataRow(firstDataRowIdx, excelPlay)
            firstDataRowIdx++
        }

        // add filters
        _sheet.setAutoFilter(RANGE_FILTERS)
    }

    private fun populateTopHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_TOP_HEADER)

        val cellBuilder = CellBuilder(headerRow, _topHeaderCellStyle)

        with(cellBuilder) {
            columnIdx(COL_IDX_TIMESTAMP).build(HEADER_STR_TIMESTAMP)
            columnIdx(COL_IDX_BOARD).build(HEADER_STR_BOARD)
            columnIdx(COL_IDX_GENERATIONS).build(HEADER_STR_GENERATIONS)
            columnIdx(COL_IDX_PLAYER_1_NAME).build(HEADER_STR_PLAYER_1_NAME)
            columnIdx(COL_IDX_PLAYER_2_NAME).build(HEADER_STR_PLAYER_2_NAME)
            columnIdx(COL_IDX_PLAYER_3_NAME).build(HEADER_STR_PLAYER_3_NAME)
            columnIdx(COL_IDX_PLAYER_4_NAME).build(HEADER_STR_PLAYER_4_NAME)
            columnIdx(COL_IDX_PLAYER_5_NAME).build(HEADER_STR_PLAYER_5_NAME)
        }
    }

    private fun populateBottomHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_BOTTOM_HEADER)

        val cellBuilder = CellBuilder(headerRow, _bottomHeaderCellStyle)

        with(cellBuilder) {
            columnIdx(COL_IDX_PLAYER_1_NAME).build(HEADER_STR_NAME)
            columnIdx(COL_IDX_PLAYER_1_CORP).build(HEADER_STR_CORP)
            columnIdx(COL_IDX_PLAYER_1_SCORE).build(HEADER_STR_SCORE)
            columnIdx(COL_IDX_PLAYER_1_ELO).build(HEADER_STR_ELO)

            columnIdx(COL_IDX_PLAYER_2_NAME).build(HEADER_STR_NAME)
            columnIdx(COL_IDX_PLAYER_2_CORP).build(HEADER_STR_CORP)
            columnIdx(COL_IDX_PLAYER_2_SCORE).build(HEADER_STR_SCORE)
            columnIdx(COL_IDX_PLAYER_2_ELO).build(HEADER_STR_ELO)

            columnIdx(COL_IDX_PLAYER_3_NAME).build(HEADER_STR_NAME)
            columnIdx(COL_IDX_PLAYER_3_CORP).build(HEADER_STR_CORP)
            columnIdx(COL_IDX_PLAYER_3_SCORE).build(HEADER_STR_SCORE)
            columnIdx(COL_IDX_PLAYER_3_ELO).build(HEADER_STR_ELO)

            columnIdx(COL_IDX_PLAYER_4_NAME).build(HEADER_STR_NAME)
            columnIdx(COL_IDX_PLAYER_4_CORP).build(HEADER_STR_CORP)
            columnIdx(COL_IDX_PLAYER_4_SCORE).build(HEADER_STR_SCORE)
            columnIdx(COL_IDX_PLAYER_4_ELO).build(HEADER_STR_ELO)

            columnIdx(COL_IDX_PLAYER_5_NAME).build(HEADER_STR_NAME)
            columnIdx(COL_IDX_PLAYER_5_CORP).build(HEADER_STR_CORP)
            columnIdx(COL_IDX_PLAYER_5_SCORE).build(HEADER_STR_SCORE)
            columnIdx(COL_IDX_PLAYER_5_ELO).build(HEADER_STR_ELO)
        }
    }

    private fun populateDataRow(rowIdx: Int, excelPlay: ExcelPlay) {
        val row = _sheet.createRow(rowIdx)
        
        val date = LocalDateTime.parse(excelPlay.timestamp, DateTimeFormatter.ISO_DATE_TIME)

        val cellBuilder = CellBuilder(row, _stringDataCenterCellStyle)

        // timestamp
        cellBuilder
            .columnIdx(COL_IDX_TIMESTAMP)
            .cellStyle(_timestampCellStyle)
            .build(date)

        // board
        cellBuilder
            .columnIdx(COL_IDX_BOARD)
            .cellStyle(_bottomHeaderCellStyle)
            .build(excelPlay.board)

        // generations
        cellBuilder
            .columnIdx(COL_IDX_GENERATIONS)
            .cellStyle(_bottomHeaderCellStyle)
            .build(excelPlay.generations)

        // player data
        with(excelPlay) {
            populatePlayerData(
                player1Name, player1Corp, player1Score, player1Elo, 
                COL_IDX_PLAYER_1_NAME, COL_IDX_PLAYER_1_CORP, COL_IDX_PLAYER_1_SCORE, COL_IDX_PLAYER_1_ELO,
                cellBuilder
            )
            populatePlayerData(
                player2Name, player2Corp, player2Score, player2Elo,
                COL_IDX_PLAYER_2_NAME, COL_IDX_PLAYER_2_CORP, COL_IDX_PLAYER_2_SCORE, COL_IDX_PLAYER_2_ELO,
                cellBuilder
            )
            populatePlayerData(
                player3Name, player3Corp, player3Score, player3Elo,
                COL_IDX_PLAYER_3_NAME, COL_IDX_PLAYER_3_CORP, COL_IDX_PLAYER_3_SCORE, COL_IDX_PLAYER_3_ELO,
                cellBuilder
            )
            populatePlayerData(
                player4Name, player4Corp, player4Score, player4Elo,
                COL_IDX_PLAYER_4_NAME, COL_IDX_PLAYER_4_CORP, COL_IDX_PLAYER_4_SCORE, COL_IDX_PLAYER_4_ELO,
                cellBuilder
            )
            populatePlayerData(
                player5Name, player5Corp, player5Score, player5Elo,
                COL_IDX_PLAYER_5_NAME, COL_IDX_PLAYER_5_CORP, COL_IDX_PLAYER_5_SCORE, COL_IDX_PLAYER_5_ELO,
                cellBuilder
            )
        }
    }

    private fun populatePlayerData(name: String?, corp: String?, score: Int?, elo: Int?, nameColIdx: Int, corpColIdx: Int, scoreColIdx: Int, eloColIdx: Int, cellBuilder: CellBuilder) {

        // name
        val nameCellStyle =
            if (name == _username) _boldStringDataCenterCellStyle
            else _stringDataCenterCellStyle

        cellBuilder
            .columnIdx(nameColIdx)
            .cellStyle(nameCellStyle)
            .build(name)

        // corp
        cellBuilder
            .columnIdx(corpColIdx)
            .cellStyle(_stringDataCenterCellStyle)
            .build(corp)

        // score
        cellBuilder
            .columnIdx(scoreColIdx)
            .cellStyle(_intDataCellStyle)
            .build(score)

        // elo
        cellBuilder
            .columnIdx(eloColIdx)
            .cellStyle(_intDataCellStyle)
            .build(elo)
    }


    // companion
    companion object {

        // header strings
        const val HEADER_STR_TIMESTAMP = "Timestamp"
        const val HEADER_STR_BOARD = "Board"
        const val HEADER_STR_GENERATIONS = "Gens"
        const val HEADER_STR_PLAYER_1_NAME = "Player 1"
        const val HEADER_STR_PLAYER_2_NAME = "Player 2"
        const val HEADER_STR_PLAYER_3_NAME = "Player 3"
        const val HEADER_STR_PLAYER_4_NAME = "Player 4"
        const val HEADER_STR_PLAYER_5_NAME = "Player 5"
        const val HEADER_STR_NAME = "Name"
        const val HEADER_STR_CORP = "Corporation"
        const val HEADER_STR_SCORE = "Score"
        const val HEADER_STR_ELO = "ELO"

        // row indices
        const val ROW_IDX_TOP_HEADER = 0
        const val ROW_IDX_BOTTOM_HEADER = 1
        const val ROW_IDX_FIRST_DATA_ROW = 2

        // column indices
        const val COL_IDX_TIMESTAMP = 0
        const val COL_IDX_BOARD = 1
        const val COL_IDX_GENERATIONS = 2
        const val COL_IDX_PLAYER_1_NAME = 3
        const val COL_IDX_PLAYER_1_CORP = 4
        const val COL_IDX_PLAYER_1_SCORE = 5
        const val COL_IDX_PLAYER_1_ELO = 6
        const val COL_IDX_PLAYER_2_NAME = 7
        const val COL_IDX_PLAYER_2_CORP = 8
        const val COL_IDX_PLAYER_2_SCORE = 9
        const val COL_IDX_PLAYER_2_ELO = 10
        const val COL_IDX_PLAYER_3_NAME = 11
        const val COL_IDX_PLAYER_3_CORP = 12
        const val COL_IDX_PLAYER_3_SCORE = 13
        const val COL_IDX_PLAYER_3_ELO = 14
        const val COL_IDX_PLAYER_4_NAME = 15
        const val COL_IDX_PLAYER_4_CORP = 16
        const val COL_IDX_PLAYER_4_SCORE = 17
        const val COL_IDX_PLAYER_4_ELO = 18
        const val COL_IDX_PLAYER_5_NAME = 19
        const val COL_IDX_PLAYER_5_CORP = 20
        const val COL_IDX_PLAYER_5_SCORE = 21
        const val COL_IDX_PLAYER_5_ELO = 22

        // column widths
        const val COL_WIDTH_TIMESTAMP = 5000
        const val COL_WIDTH_BOARD = 2500
        const val COL_WIDTH_GENERATIONS = 2500
        const val COL_WIDTH_PLAYER_NAME = 4000
        const val COL_WIDTH_PLAYER_CORP = 5500
        const val COL_WIDTH_PLAYER_SCORE = 2500
        const val COL_WIDTH_PLAYER_ELO = 2500

        // cell range addresses (for merged regions and auto-filters)
        val RANGE_TIMESTAMP = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, COL_IDX_TIMESTAMP, COL_IDX_TIMESTAMP)
        val RANGE_BOARD = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, COL_IDX_BOARD, COL_IDX_BOARD)
        val RANGE_GENERATIONS = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_BOTTOM_HEADER, COL_IDX_GENERATIONS, COL_IDX_GENERATIONS)
        val RANGE_PLAYER_1 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, COL_IDX_PLAYER_1_NAME, COL_IDX_PLAYER_1_ELO)
        val RANGE_PLAYER_2 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, COL_IDX_PLAYER_2_NAME, COL_IDX_PLAYER_2_ELO)
        val RANGE_PLAYER_3 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, COL_IDX_PLAYER_3_NAME, COL_IDX_PLAYER_3_ELO)
        val RANGE_PLAYER_4 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, COL_IDX_PLAYER_4_NAME, COL_IDX_PLAYER_4_ELO)
        val RANGE_PLAYER_5 = CellRangeAddress(ROW_IDX_TOP_HEADER, ROW_IDX_TOP_HEADER, COL_IDX_PLAYER_5_NAME, COL_IDX_PLAYER_5_ELO)
        val RANGE_FILTERS = CellRangeAddress(ROW_IDX_BOTTOM_HEADER, ROW_IDX_BOTTOM_HEADER, COL_IDX_TIMESTAMP, COL_IDX_PLAYER_5_ELO)

        // border style
        val BORDER_STYLE = BorderStyle.THIN

    }

}