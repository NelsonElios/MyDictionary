package com.example.mydictionaryapp.data.remote.dto

import com.example.mydictionaryapp.domain.model.Definition
import com.example.mydictionaryapp.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {

    fun toMeaning(): Meaning = Meaning(
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech
    )
}