package de.kayteem.apps.tfmgamelogconverter.controller.export

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Created by Tobias Mielke
 * Created on 12.02.2023
 * Changed on 12.02.2023
 *
 * A Builder for creating cell styles for Excel workbooks.
 */
class CellStyleBuilder(private val workbook: XSSFWorkbook) {

    // dependencies
    private var fontSize: Int = 12
    private var bold: Boolean = false
    private var horAlignment: HorizontalAlignment = HorizontalAlignment.CENTER
    private var vertAlignment: VerticalAlignment = VerticalAlignment.CENTER
    private var backgroundColor: IndexedColors = IndexedColors.WHITE
    private var borderStyle: BorderStyle = BorderStyle.THIN


    // interface
    fun fontSize(fontSize: Int): CellStyleBuilder {
        this.fontSize = fontSize

        return this
    }

    fun bold(bold: Boolean): CellStyleBuilder {
        this.bold = bold

        return this
    }

    fun horAlignment(horAlignment: HorizontalAlignment): CellStyleBuilder {
        this.horAlignment = horAlignment

        return this
    }

    fun vertAlignment(vertAlignment: VerticalAlignment): CellStyleBuilder {
        this.vertAlignment = vertAlignment

        return this
    }

    fun backgroundColor(backgroundColor: IndexedColors): CellStyleBuilder {
        this.backgroundColor = backgroundColor

        return this
    }

    fun build(): XSSFCellStyle {

        // font
        val font = workbook.createFont()
        font.fontHeightInPoints = fontSize.toShort()
        font.bold = bold

        // cell style
        val cellStyle = workbook.createCellStyle()
        with(cellStyle) {
            setFont(font)
            alignment = horAlignment
            verticalAlignment = vertAlignment
            fillForegroundColor = backgroundColor.index     // called fillForegroundColor, but sets cell background color
            fillPattern = FillPatternType.SOLID_FOREGROUND
            borderTop = borderStyle
            borderLeft = borderStyle
            borderRight = borderStyle
            borderBottom = borderStyle
        }

        return cellStyle
    }

}