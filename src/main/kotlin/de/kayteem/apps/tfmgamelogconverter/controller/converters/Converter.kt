package de.kayteem.apps.tfmgamelogconverter.controller.converters

/**
 * Interface for converting an input format into an output format.
 *
 * Author: Tobias Mielke
 */
interface Converter<IN, OUT> {

    fun process(input: IN): OUT

}