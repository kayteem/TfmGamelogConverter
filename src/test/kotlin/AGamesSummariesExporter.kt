import com.fasterxml.jackson.dataformat.csv.CsvMapper
import de.kayteem.apps.tfmgamelogconverter.controller.csvExport.GamesSummariesExporter
import de.kayteem.apps.tfmgamelogconverter.controller.csvExport.GamesSummariesExporterImpl
import org.junit.Assert
import org.junit.Before
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

        // execution
        exporter.export(PATH_CSV, gameSummaries)

        // post-condition
        Assert.assertTrue(PATH_CSV.toFile().exists())
        Assert.assertTrue(PATH_CSV.toFile().readText().isNotEmpty())
    }


    // helpers
    @Before
    fun setUp() {
        val csvMapper = CsvMapper()

        exporter = GamesSummariesExporterImpl(csvMapper)
    }


    companion object {

        val PATH_CSV: Path = Paths.get("out/artifacts/TfmGamelogConverter_jar/TfmGamesOverview.csv").toAbsolutePath()

        val LOG_GAME_1 = TestDataFactory.buildSummaryGame1()
        
        val LOG_GAME_2 = TestDataFactory.buildSummaryGame2()
    }

}