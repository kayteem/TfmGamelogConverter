package de.kayteem.apps.tfmgamelogconverter.controller.jsonImport

import com.fasterxml.jackson.databind.ObjectMapper
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import kotlin.io.path.absolutePathString

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
class GameLogImporterImpl(private val mapper: ObjectMapper) : GameLogImporter {

    override fun import(path: Path): GameLog? {
        val absolutePathStr = path.absolutePathString()

        return try {
            println("importing $absolutePathStr")
            mapper.readValue(path.toFile(), GameLog::class.java)

        } catch (e: Exception) {
            println("Cannot import json file $absolutePathStr\n ${e.message}")
            null
        }
    }

    override fun importAll(path: Path): List<GameLog> {
        return path.toFile()
            .walk()
            .filter { it.isFile }
            .filter { it.extension.lowercase() == "json" }
            .mapNotNull { import(it.toPath()) }
            .toList()
    }

}