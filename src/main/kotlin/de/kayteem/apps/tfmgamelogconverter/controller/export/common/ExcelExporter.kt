package de.kayteem.apps.tfmgamelogconverter.controller.export.common

import de.kayteem.apps.tfmgamelogconverter.controller.converters.PlaysToUsernameConverter
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetBuilders.PlaysSheetFactory
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.nio.file.Path

/**
 * Exports Play objects to an Excel file.
 *
 * Excel export tutorials:
 * https://www.developersoapbox.com/basic-read-and-write-excel-using-kotlin/
 * https://www.baeldung.com/apache-poi-background-color
 *
 * Author: Tobias Mielke
 */
class ExcelExporter {

    fun export(path: Path, plays: List<Play>) {

        // determine username
        val playsToUsernameConverter = PlaysToUsernameConverter()
        val username = playsToUsernameConverter.process(plays)

        // build workbook
        val workbook = XSSFWorkbook()

        // build sheets
        PlaysSheetFactory(workbook).create(plays, username)
        
        // write to Excel file
        val fos = FileOutputStream(path.toFile())
        workbook.write(fos)
        workbook.close()
    }

}