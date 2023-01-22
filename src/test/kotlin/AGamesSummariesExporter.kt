import com.fasterxml.jackson.dataformat.csv.CsvMapper
import de.kayteem.apps.tfmgamelogconverter.controller.export.GamesSummariesExporter
import de.kayteem.apps.tfmgamelogconverter.controller.export.GamesSummariesCsvExporter
import de.kayteem.apps.tfmgamelogconverter.controller.export.GamesSummariesExcelExporter
import org.junit.Assert
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 22.01.2023
 */
class AGamesSummariesExporter {

    // unit under test
    private lateinit var exporter: GamesSummariesExporter


    // test cases
    @Test
    fun canExportGameLogsToCsv() {

        // setup
        val gameSummaries = listOf(SUMMARY_GAME_1, SUMMARY_GAME_2)
        exporter = GamesSummariesCsvExporter(CsvMapper())

        // execution
        exporter.export(PATH_CSV, gameSummaries)

        // post-condition
        Assert.assertTrue(PATH_CSV.toFile().exists())
        Assert.assertTrue(PATH_CSV.toFile().readText().isNotEmpty())
    }

    @Test
    fun canExportGameLogsToExcel() {

        // setup
        val gameSummaries = listOf(SUMMARY_GAME_1, SUMMARY_GAME_2)
        exporter = GamesSummariesExcelExporter()

        // execution
        exporter.export(PATH_EXCEL, gameSummaries)

        // post-condition
        Assert.assertTrue(PATH_EXCEL.toFile().exists())
    }


    // companion
    companion object {

        // paths
        private val PATH_ARTIFACT: Path = Paths.get("out/artifacts/TfmGamelogConverter_jar/")
        val PATH_CSV: Path = PATH_ARTIFACT.resolve("TfmGamesOverview.csv")
        val PATH_EXCEL: Path = PATH_ARTIFACT.resolve("TfmGamesOverview.xlsx")

        // game summaries
        val SUMMARY_GAME_1 = TestDataFactory.buildSummaryGame1()
        val SUMMARY_GAME_2 = TestDataFactory.buildSummaryGame2()
    }

}