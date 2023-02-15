package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellStyleBuilder
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Abstract implementation for creating and managing cell styles for Excel sheets.
 *
 * Author: Tobias Mielke
 */
abstract class AbstractStyleManager(workbook: XSSFWorkbook) : StyleManager {

    // members
    protected var cellStyleBuilder = CellStyleBuilder(workbook)

    private var cellStyles = mutableMapOf<String, XSSFCellStyle>()


    // interface
    override fun registerStyle(name: String, style: XSSFCellStyle) {
        cellStyles[name] = style
    }

    override fun getStyle(name: String): XSSFCellStyle {
        return cellStyles.getOrElse(name) {
            throw Exception("Invalid style name: $name")
        }
    }

    override fun applyStyle(name: String, cellBuilder: CellBuilder): CellBuilder {
        cellBuilder.cellStyle(getStyle(name))

        return cellBuilder
    }

}