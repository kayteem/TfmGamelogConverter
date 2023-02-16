package de.kayteem.apps.tfmgamelogconverter.controller.export.styleManagers

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellStyleBuilder
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Abstract implementation for creating and managing cell styles for Excel sheets.
 *
 * Author: Tobias Mielke
 */
abstract class AbstractStyleManager(workbook: XSSFWorkbook) : StyleManager {

    // members
    protected var cellStyleBuilder = CellStyleBuilder(workbook)


    // interface
    protected fun getBgColor(shadowed: Boolean): IndexedColors {
        return if (shadowed) IndexedColors.LIGHT_GREEN else IndexedColors.WHITE
    }

    protected fun getPattern(shadowed: Boolean): FillPatternType {
        return if (shadowed) FillPatternType.SPARSE_DOTS else FillPatternType.SOLID_FOREGROUND
    }

}