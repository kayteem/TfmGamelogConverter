package de.kayteem.apps.tfmgamelogconverter.controller.csvExport

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import java.io.FileOutputStream
import java.nio.file.Path

class GamesSummariesExporterImpl(private val mapper: CsvMapper) : GamesSummariesExporter {

    override fun export(path: Path, gameSummaries: List<GameSummary>) {
        val schema = CsvSchema.builder()
            .addColumn("Timestamp")
            .addColumn("Board")
            .addColumn("Player1Name")
            .addNumberColumn("Player1Score")
            .addColumn("Player2Name")
            .addNumberColumn("Player2Score")
            .addColumn("Player3Name")
            .addNumberColumn("Player3Score")
            .addColumn("Player4Name")
            .addNumberColumn("Player4Score")
            .addColumn("Player5Name")
            .addNumberColumn("Player5Score")
            .addColumn("Generations")
            .build()

        val fos = FileOutputStream(path.toFile())

        mapper.writer()
            .with(schema.withHeader())
            .writeValues(fos)
            .writeAll(gameSummaries)

        fos.close()
    }
    
}