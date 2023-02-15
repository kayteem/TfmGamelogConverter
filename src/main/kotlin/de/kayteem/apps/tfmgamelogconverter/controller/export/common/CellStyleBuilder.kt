package de.kayteem.apps.tfmgamelogconverter.controller.export.common

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * A Builder for creating cell styles for Excel workbooks.
 *
 * Author: Tobias Mielke
 */
class CellStyleBuilder(private val workbook: XSSFWorkbook) {

    // members
    private var stringFormat = BuiltinFormats.getBuiltinFormat("text").toShort()
    private var intFormat = workbook.createDataFormat().getFormat("0")
    private var dateFormat = workbook.createDataFormat().getFormat("YYYY-MM-DD hh:mm")


    // dependencies
    private var fontSize: Int = 10
    private var bold: Boolean = false
    private var format: Short = stringFormat
    private var horAlignment: HorizontalAlignment = HorizontalAlignment.CENTER
    private var vertAlignment: VerticalAlignment = VerticalAlignment.CENTER
    private var textColor: IndexedColors = IndexedColors.BLACK
    private var cellForegroundColor: IndexedColors = IndexedColors.WHITE
    private var cellBackgroundColor: IndexedColors = IndexedColors.WHITE
    private var cellPattern: FillPatternType = FillPatternType.SOLID_FOREGROUND
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

    fun stringFormat(): CellStyleBuilder {
        this.format = stringFormat

        return this
    }

    fun intFormat(): CellStyleBuilder {
        this.format = intFormat

        return this
    }

    fun dateFormat(): CellStyleBuilder {
        this.format = dateFormat

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

    fun textColor(textColor: IndexedColors): CellStyleBuilder {
        this.textColor = textColor

        return this
    }

    fun cellForegroundColor(cellForegroundColor: IndexedColors): CellStyleBuilder {
        this.cellForegroundColor = cellForegroundColor

        return this
    }

    fun cellBackgroundColor(cellBackgroundColor: IndexedColors): CellStyleBuilder {
        this.cellBackgroundColor = cellBackgroundColor

        return this
    }

    fun cellPattern(cellPattern: FillPatternType): CellStyleBuilder {
        this.cellPattern = cellPattern

        return this
    }

    fun build(): XSSFCellStyle {

        // font
        val font = workbook.createFont()
        font.fontHeightInPoints = fontSize.toShort()
        font.bold = bold
        font.color = textColor.index

        // cell style
        val cellStyle = workbook.createCellStyle()
        with(cellStyle) {
            setFont(font)
            dataFormat = format
            alignment = horAlignment
            verticalAlignment = vertAlignment
            fillForegroundColor = cellForegroundColor.index
            fillBackgroundColor = cellBackgroundColor.index
            fillPattern = cellPattern
            borderTop = borderStyle
            borderLeft = borderStyle
            borderRight = borderStyle
            borderBottom = borderStyle
        }

        return cellStyle
    }

}