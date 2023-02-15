package de.kayteem.apps.tfmgamelogconverter.controller.export.common

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import java.time.LocalDateTime

/**
 * A builder for creating cells in an Excel workbook.
 *
 * Author: Tobias Mielke
 */
class CellBuilder(private val row: Row) {

    // dependencies
    private var columnIdx: Int = 0
    private var cellStyle: XSSFCellStyle? = null
    

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
        if (value == null) {
            return build("")
        }

        val cell = row.createCell(columnIdx)
        cell.cellStyle = cellStyle
        cell.setCellValue(value.toDouble())

        return cell
    }

    fun build(value: LocalDateTime?): Cell {
        if (value == null) {
            return build("")
        }
        
        val cell = row.createCell(columnIdx)

        if (cellStyle != null) {
            cell.cellStyle = cellStyle
        }
        
        cell.setCellValue(value)

        return cell
    }

}