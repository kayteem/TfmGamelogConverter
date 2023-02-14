package de.kayteem.apps.tfmgamelogconverter.controller.export.plays

import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.Play
import java.nio.file.Path

/**
 * Interface for exporting Play objects to a file.
 *
 * Author: Tobias Mielke
 */
interface PlaysExporter {

    fun export(path: Path, plays: List<Play>)

}