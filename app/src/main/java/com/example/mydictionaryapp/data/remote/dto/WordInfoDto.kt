package com.example.mydictionaryapp.data.remote.dto

import com.example.mydictionaryapp.data.database.dao.WordInfoDao
import com.example.mydictionaryapp.data.database.entity.WordInfoEntity
import com.example.mydictionaryapp.domain.model.Meaning
import com.example.mydictionaryapp.domain.model.Phonetic
import com.example.mydictionaryapp.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val word: String
) {

    fun toWordInfoEntity(): WordInfoEntity = WordInfoEntity(
        meanings = meanings.map { it.toMeaning() },
        origin = origin,
        phonetic = phonetic,
        word = word
    )
}