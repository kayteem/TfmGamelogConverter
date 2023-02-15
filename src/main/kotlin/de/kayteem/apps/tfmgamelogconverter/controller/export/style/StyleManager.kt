package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellBuilder
import org.apache.poi.xssf.usermodel.XSSFCellStyle

/**
 * Interface for creating and managing cell styles for Excel sheets.
 *
 * Author: Tobias Mielke
 */
interface StyleManager {

    fun registerStyle(name: String, style: XSSFCellStyle)

    fun getStyle(name: String): XSSFCellStyle

    fun applyStyle(name: String, cellBuilder: CellBuilder): CellBuilder


    companion object {
        const val PRIMARY_HEADER_STYLE = "PRIMARY_HEADER"
        const val SECONDARY_HEADER_STYLE = "SECONDARY_HEADER"
        const val CENTERED_STRING_DATA_STYLE = "CENTERED_STRING_DATA"
        const val CENTERED_STRING_DATA_BOLD_STYLE = "CENTERED_STRING_DATA_BOLD"
        const val CENTERED_INT_DATA_STYLE = "CENTERED_INT_DATA"
        const val CENTERED_TIMESTAMP_DATA_STYLE = "CENTERED_TIMESTAMP_DATA"
    }

}