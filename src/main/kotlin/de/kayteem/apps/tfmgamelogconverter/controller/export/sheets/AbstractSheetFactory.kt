package de.kayteem.apps.tfmgamelogconverter.controller.export.sheets

import org.apache.poi.xssf.usermodel.XSSFSheet

/**
 * Abstract implementation for creating sheets in the Excel workbook.
 *
 * Author: Tobias Mielke
 */
abstract class AbstractSheetFactory : SheetFactory {

    protected lateinit var sheet: XSSFSheet

}