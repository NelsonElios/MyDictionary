package com.example.mydictionaryapp.data.remote.dto

import com.example.mydictionaryapp.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
) {
    fun toDefinition(): Definition =
        Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
}