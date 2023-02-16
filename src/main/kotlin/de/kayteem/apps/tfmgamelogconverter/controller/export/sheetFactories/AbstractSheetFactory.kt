package de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet

/**
 * Abstract implementation for creating sheets in the Excel workbook.
 *
 * Author: Tobias Mielke
 */
abstract class AbstractSheetFactory : SheetFactory {

    // members
    protected lateinit var sheet: XSSFSheet


    // interface
    protected fun <COL : Enum<COL>> setColumnsWidths(columnWidths: Map<COL, Int>) {
        columnWidths.forEach { (column, width) ->
            sheet.setColumnWidth(column.ordinal, width)
        }
    }

    protected fun <COL: Enum<COL>> populateHeaderRow(rowIdx: Int, columnNames: Map<COL, String>, cellStyle: XSSFCellStyle) {
        val headerRow = sheet.createRow(rowIdx)
        val cellBuilder = CellBuilder(headerRow)

        columnNames.forEach { (column, columnName) ->
            cellBuilder
                .columnIdx(column.ordinal)
                .cellStyle(cellStyle)
                .build(columnName)
        }
    }

    protected fun mergeAndBorderCells(range: CellRangeAddress, borderStyle: BorderStyle) {
        sheet.addMergedRegion(range)

        RegionUtil.setBorderTop(borderStyle, range, sheet)
        RegionUtil.setBorderLeft(borderStyle, range, sheet)
        RegionUtil.setBorderRight(borderStyle, range, sheet)
        RegionUtil.setBorderBottom(borderStyle, range, sheet)
    }

}