package de.kayteem.apps.tfmgamelogconverter.controller.xlsxExport

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import java.time.LocalDateTime

/**
 * Created by Tobias Mielke
 * Created on 12.02.2023
 * Changed on 12.02.2023
 *
 * A builder for creating cells in an Excel workbook.
 */
class CellBuilder(private val row: Row, defaultCellStyle: XSSFCellStyle) {

    // dependencies
    private var columnIdx: Int = 0
    private var cellStyle: XSSFCellStyle = defaultCellStyle
    

    // interface
    fun columnIdx(columnIdx: Int): CellBuilder {
        this.columnIdx = columnIdx

        return this
    }

    fun cellStyle(cellStyle: XSSFCellStyle): CellBuilder {
        this.cellStyle = cellStyle

        return this
    }

    fun build(value: String?): Cell {
        val cell = row.createCell(columnIdx)
        cell.cellStyle = cellStyle
        cell.setCellValue(value ?: "")

        return cell
    }

    fun build(value: Number?): Cell {
        val cell = row.createCell(columnIdx)
        cell.cellStyle = cellStyle
        cell.setCellValue(value?.toDouble() ?: 0.0)

        return cell
    }

    fun build(value: LocalDateTime): Cell {
        val cell = row.createCell(columnIdx)
        cell.cellStyle = cellStyle
        cell.setCellValue(value)

        return cell
    }

}