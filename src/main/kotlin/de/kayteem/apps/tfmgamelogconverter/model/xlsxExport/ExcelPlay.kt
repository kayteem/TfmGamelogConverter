package de.kayteem.apps.tfmgamelogconverter.model.xlsxExport

/**
 * Data class required for exporting play data to excel.
 *
 * Author: Tobias Mielke
 */
data class ExcelPlay(
    val timestamp: String,
    val board: String,
    val generations: Int,

    val player1Name: String,
    val player1Corp: String,
    val player1Elo: Int? = null,
    val player1Score: Int,

    val player2Name: String,
    val player2Corp: String,
    val player2Elo: Int? = null,
    val player2Score: Int,

    val player3Name: String? = null,
    val player3Corp: String? = null,
    val player3Elo: Int? = null,
    val player3Score: Int? = null,

    val player4Name: String? = null,
    val player4Corp: String? = null,
    val player4Elo: Int? = null,
    val player4Score: Int? = null,

    val player5Name: String? = null,
    val player5Corp: String? = null,
    val player5Elo: Int? = null,
    val player5Score: Int? = null
)
