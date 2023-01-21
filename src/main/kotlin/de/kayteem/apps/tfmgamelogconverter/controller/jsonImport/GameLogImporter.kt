package de.kayteem.apps.tfmgamelogconverter.controller.jsonImport

import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
interface GameLogImporter {

    fun import(path: Path): GameLog?
    
    fun importAll(path: Path): List<GameLog>

}