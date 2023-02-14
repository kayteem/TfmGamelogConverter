package de.kayteem.apps.tfmgamelogconverter.controller.xlsxExport

import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.GameSummary
import java.nio.file.Path

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
 */
interface GamesSummariesExporter {

    fun export(path: Path, gamesSummaries: List<GameSummary>)

}