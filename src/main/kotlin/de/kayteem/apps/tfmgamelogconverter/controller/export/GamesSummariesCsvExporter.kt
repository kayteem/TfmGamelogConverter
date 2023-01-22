package de.kayteem.apps.tfmgamelogconverter.controller.export

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import java.io.FileOutputStream
import java.nio.file.Path

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 22.01.2023
 */
class GamesSummariesCsvExporter(private val mapper: CsvMapper) : GamesSummariesExporter {

    override fun export(path: Path, gamesSummaries: List<GameSummary>) {
        val schema = CsvSchema.builder()
            .addColumn("Timestamp")
            .addColumn("Board")
            .addColumn("Player1Name")
            .addColumn("Player1Corp")
            .addNumberColumn("Player1Elo")
            .addNumberColumn("Player1Score")
            .addColumn("Player2Name")
            .addColumn("Player2Corp")
            .addNumberColumn("Player2Elo")
            .addNumberColumn("Player2Score")
            .addColumn("Player3Name")
            .addColumn("Player3Corp")
            .addNumberColumn("Player3Elo")
            .addNumberColumn("Player3Score")
            .addColumn("Player4Name")
            .addColumn("Player4Corp")
            .addNumberColumn("Player4Elo")
            .addNumberColumn("Player4Score")
            .addColumn("Player5Name")
            .addColumn("Player5Corp")
            .addNumberColumn("Player5Elo")
            .addNumberColumn("Player5Score")
            .addColumn("Generations")
            .build()

        val fos = FileOutputStream(path.toFile())

        mapper.writer()
            .with(schema.withHeader())
            .writeValues(fos)
            .writeAll(gamesSummaries)

        fos.close()
    }
    
}