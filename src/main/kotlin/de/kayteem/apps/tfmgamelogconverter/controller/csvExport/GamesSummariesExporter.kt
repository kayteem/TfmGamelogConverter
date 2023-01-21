package de.kayteem.apps.tfmgamelogconverter.controller.csvExport

import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import java.nio.file.Path

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
 */
interface GamesSummariesExporter {

    fun export(path: Path, gameSummaries: List<GameSummary>)

}