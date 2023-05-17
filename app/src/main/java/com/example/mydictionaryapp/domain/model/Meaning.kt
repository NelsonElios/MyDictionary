package com.example.mydictionaryapp.domain.model

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)