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
        const val STRING_DATA_STYLE = "STRING_DATA"
        const val STRING_DATA_BOLD_STYLE = "STRING_DATA_BOLD"
        const val INT_DATA_STYLE = "INT_DATA"
        const val TIMESTAMP_DATA_STYLE = "TIMESTAMP_DATA"
    }

}