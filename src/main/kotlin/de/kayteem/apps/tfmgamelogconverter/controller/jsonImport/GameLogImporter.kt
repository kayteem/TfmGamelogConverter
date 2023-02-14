package de.kayteem.apps.tfmgamelogconverter.controller.jsonImport

import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path

/**
 * Interface for importing GameLog objects from files.
 *
 * Author: Tobias Mielke
 */
interface GameLogImporter {

    fun import(path: Path): GameLog?
    
    fun importAll(path: Path): List<GameLog>

}