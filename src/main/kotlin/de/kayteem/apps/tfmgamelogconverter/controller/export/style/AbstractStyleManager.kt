package de.kayteem.apps.tfmgamelogconverter.controller.export.style

import de.kayteem.apps.tfmgamelogconverter.controller.export.common.CellStyleBuilder
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * Abstract implementation for creating and managing cell styles for Excel sheets.
 *
 * Author: Tobias Mielke
 */
abstract class AbstractStyleManager(workbook: XSSFWorkbook) : StyleManager {

    protected var cellStyleBuilder = CellStyleBuilder(workbook)

}