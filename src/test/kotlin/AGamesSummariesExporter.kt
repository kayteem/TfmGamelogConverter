import com.fasterxml.jackson.dataformat.csv.CsvMapper
import de.kayteem.apps.tfmgamelogconverter.controller.export.GamesSummariesExporter
import de.kayteem.apps.tfmgamelogconverter.controller.export.GameSummariesCsvExporter
import org.junit.Assert
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
 */
class AGamesSummariesExporter {

    // unit under test
    private lateinit var exporter: GamesSummariesExporter


    // test cases
    @Test
    fun canExportGameLogsToCsv() {

        // setup
        val gameSummaries = listOf(LOG_GAME_1, LOG_GAME_2)
        exporter = GameSummariesCsvExporter(CsvMapper())

        // execution
        exporter.export(PATH_CSV, gameSummaries)

        // post-condition
        Assert.assertTrue(PATH_CSV.toFile().exists())
        Assert.assertTrue(PATH_CSV.toFile().readText().isNotEmpty())
    }


    // companion
    companion object {

        private val PATH_ARTIFACT: Path = Paths.get("out/artifacts/TfmGamelogConverter_jar/")
        val PATH_CSV: Path = PATH_ARTIFACT.resolve("TfmGamesOverview.csv")

        val LOG_GAME_1 = TestDataFactory.buildSummaryGame1()
        val LOG_GAME_2 = TestDataFactory.buildSummaryGame2()
    }

}