package de.kayteem.apps.tfmgamelogconverter.controller.export

import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.nio.file.Path


/**
 * Created by Tobias Mielke
 * Created on 22.01.2023
 * Changed on 22.01.2023
 *
 * Excel export tutorials:
 * https://www.developersoapbox.com/basic-read-and-write-excel-using-kotlin/
 * https://www.baeldung.com/apache-poi-background-color
 */
class GamesSummariesExcelExporter : GamesSummariesExporter {

    // members
    private lateinit var _workbook: XSSFWorkbook
    private lateinit var _sheet: XSSFSheet
    private lateinit var _topHeaderCellStyle: XSSFCellStyle
    private lateinit var _bottomHeaderCellStyle: XSSFCellStyle


    // interface
    override fun export(path: Path, gamesSummaries: List<GameSummary>) {
        _workbook = XSSFWorkbook()
        _sheet = _workbook.createSheet()

        _topHeaderCellStyle = buildCellStyle(12, true, IndexedColors.LIGHT_GREEN)
        _bottomHeaderCellStyle = buildCellStyle(10, false, IndexedColors.LIGHT_GREEN)

        setColumnsWidths()
        populateSheet(gamesSummaries)
        mergeAndBorderHeaderCells()

        val fos = FileOutputStream(path.toFile())
        _workbook.write(fos)
        _workbook.close()
    }


    // helpers
    private fun buildCellStyle(fontSize: Int, bold: Boolean, backgroundColor: IndexedColors): XSSFCellStyle {

        // font
        val font = _workbook.createFont()
        font.fontHeightInPoints = fontSize.toShort()
        font.bold = bold

        // cell style
        val cellStyle = _workbook.createCellStyle()
        with(cellStyle) {
            setFont(font)
            alignment = HorizontalAlignment.CENTER
            verticalAlignment = VerticalAlignment.CENTER
            fillForegroundColor = backgroundColor.index     // field is called foregroundColor, but sets background color
            fillPattern = FillPatternType.SOLID_FOREGROUND
            borderTop = BORDER_STYLE
            borderLeft = BORDER_STYLE
            borderRight = BORDER_STYLE
            borderBottom = BORDER_STYLE
        }

        return cellStyle
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

    private fun populateSheet(gamesSummaries: List<GameSummary>) {

        // populate header rows
        populateTopHeaderRow()
        populateBottomHeaderRow()

        // populate data rows
        var firstDataRowIdx = ROW_IDX_FIRST_DATA_ROW
        gamesSummaries.forEach { summary ->
            populateDataRow(firstDataRowIdx, summary)
            firstDataRowIdx++
        }

        // add filters
        _sheet.setAutoFilter(RANGE_FILTERS)
    }

    private fun populateTopHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_TOP_HEADER)
        
        populateCell(headerRow, COL_IDX_TIMESTAMP, HEADER_STR_TIMESTAMP, CellType.STRING)
        populateCell(headerRow, COL_IDX_BOARD, HEADER_STR_BOARD, CellType.STRING)
        populateCell(headerRow, COL_IDX_GENERATIONS, HEADER_STR_GENERATIONS, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_1_NAME, HEADER_STR_PLAYER_1_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_2_NAME, HEADER_STR_PLAYER_2_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_3_NAME, HEADER_STR_PLAYER_3_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_4_NAME, HEADER_STR_PLAYER_4_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_5_NAME, HEADER_STR_PLAYER_5_NAME, CellType.STRING)

        applyHeaderCellStyle(headerRow, _topHeaderCellStyle)
    }

    private fun populateBottomHeaderRow() {
        val headerRow = _sheet.createRow(ROW_IDX_BOTTOM_HEADER)

        populateCell(headerRow, COL_IDX_PLAYER_1_NAME, HEADER_STR_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_1_CORP, HEADER_STR_CORP, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_1_SCORE, HEADER_STR_SCORE, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_1_ELO, HEADER_STR_ELO, CellType.STRING)
        
        populateCell(headerRow, COL_IDX_PLAYER_2_NAME, HEADER_STR_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_2_CORP, HEADER_STR_CORP, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_2_SCORE, HEADER_STR_SCORE, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_2_ELO, HEADER_STR_ELO, CellType.STRING)
        
        populateCell(headerRow, COL_IDX_PLAYER_3_NAME, HEADER_STR_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_3_CORP, HEADER_STR_CORP, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_3_SCORE, HEADER_STR_SCORE, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_3_ELO, HEADER_STR_ELO, CellType.STRING)
        
        populateCell(headerRow, COL_IDX_PLAYER_4_NAME, HEADER_STR_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_4_CORP, HEADER_STR_CORP, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_4_SCORE, HEADER_STR_SCORE, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_4_ELO, HEADER_STR_ELO, CellType.STRING)
        
        populateCell(headerRow, COL_IDX_PLAYER_5_NAME, HEADER_STR_NAME, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_5_CORP, HEADER_STR_CORP, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_5_SCORE, HEADER_STR_SCORE, CellType.STRING)
        populateCell(headerRow, COL_IDX_PLAYER_5_ELO, HEADER_STR_ELO, CellType.STRING)

        applyHeaderCellStyle(headerRow, _bottomHeaderCellStyle)
    }

    private fun populateDataRow(rowIdx: Int, gameSummary: GameSummary) {
        val row = _sheet.createRow(rowIdx)

        populateCell(row, COL_IDX_TIMESTAMP, gameSummary.timestamp, CellType.STRING)
        populateCell(row, COL_IDX_BOARD, gameSummary.board, CellType.STRING)
        populateCell(row, COL_IDX_GENERATIONS, gameSummary.generations, CellType.NUMERIC)

        populateCell(row, COL_IDX_PLAYER_1_NAME, gameSummary.player1Name, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_1_CORP, gameSummary.player1Corp, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_1_SCORE, gameSummary.player1Score, CellType.NUMERIC)
        populateCell(row, COL_IDX_PLAYER_1_ELO, gameSummary.player1Elo, CellType.NUMERIC)

        populateCell(row, COL_IDX_PLAYER_2_NAME, gameSummary.player2Name, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_2_CORP, gameSummary.player2Corp, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_2_SCORE, gameSummary.player2Score, CellType.NUMERIC)
        populateCell(row, COL_IDX_PLAYER_2_ELO, gameSummary.player2Elo, CellType.NUMERIC)

        populateCell(row, COL_IDX_PLAYER_3_NAME, gameSummary.player3Name ?: "", CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_3_CORP, gameSummary.player3Corp, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_3_SCORE, gameSummary.player3Score ?: "", CellType.NUMERIC)
        populateCell(row, COL_IDX_PLAYER_3_ELO, gameSummary.player3Elo ?: "", CellType.NUMERIC)

        populateCell(row, COL_IDX_PLAYER_4_NAME, gameSummary.player4Name ?: "", CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_4_CORP, gameSummary.player4Corp, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_4_SCORE, gameSummary.player4Score ?: "", CellType.NUMERIC)
        populateCell(row, COL_IDX_PLAYER_4_ELO, gameSummary.player4Elo ?: "", CellType.NUMERIC)

        populateCell(row, COL_IDX_PLAYER_5_NAME, gameSummary.player5Name ?: "", CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_5_CORP, gameSummary.player5Corp, CellType.STRING)
        populateCell(row, COL_IDX_PLAYER_5_SCORE, gameSummary.player5Score ?: "", CellType.NUMERIC)
        populateCell(row, COL_IDX_PLAYER_5_ELO, gameSummary.player5Elo ?: "", CellType.NUMERIC)
    }

    private fun <T> populateCell(row: Row, columnIdx: Int, value: T, cellType: CellType) {
        if (value != null) {
            row
                .createCell(columnIdx, cellType)
                .setCellValue(value.toString())
        }
    }

    private fun applyHeaderCellStyle(row: Row, cellStyle: XSSFCellStyle) {
        row.cellIterator().forEach {
            it.cellStyle = cellStyle
        }
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
        const val COL_WIDTH_TIMESTAMP = 6000
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
        val RANGE_FILTERS = CellRangeAddress(ROW_IDX_BOTTOM_HEADER, ROW_IDX_BOTTOM_HEADER, COL_IDX_TIMESTAMP, COL_IDX_GENERATIONS)

        // border style
        val BORDER_STYLE = BorderStyle.THIN

    }

}