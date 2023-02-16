package de.kayteem.apps.tfmgamelogconverter.controller.export.common

import de.kayteem.apps.tfmgamelogconverter.controller.converters.PlaysToCorporationsConverter
import de.kayteem.apps.tfmgamelogconverter.controller.converters.PlaysToUsernameConverter
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.CorpsSheetFactory
import de.kayteem.apps.tfmgamelogconverter.controller.export.sheetFactories.PlaysSheetFactory
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
class ExcelExporter(private val path: Path) {

    fun export(plays: List<Play>) {

        // build workbook
        val workbook = XSSFWorkbook()

        // determine entities
        val username = PlaysToUsernameConverter().process(plays)
        val corporations = PlaysToCorporationsConverter(username).process(plays)

        // build sheets
        PlaysSheetFactory(workbook, username).create(plays)
        CorpsSheetFactory(workbook).create(corporations)

        // write to Excel file
        val fos = FileOutputStream(path.toFile())
        workbook.write(fos)
        workbook.close()
    }

}