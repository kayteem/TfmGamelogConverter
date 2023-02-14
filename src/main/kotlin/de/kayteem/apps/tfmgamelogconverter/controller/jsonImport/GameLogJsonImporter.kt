package de.kayteem.apps.tfmgamelogconverter.controller.jsonImport

import com.fasterxml.jackson.databind.ObjectMapper
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import kotlin.io.path.absolutePathString

/**
 * Implementation for importing GameLog objects from json files.
 *
 * JSON import tutorial:
 * https://stackabuse.com/reading-and-writing-json-in-kotlin-with-jackson/
 *
 * Author: Tobias Mielke
 */
class GameLogJsonImporter(private val mapper: ObjectMapper) : GameLogImporter {

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