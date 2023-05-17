package com.example.mydictionaryapp.data.remote.dto

import com.example.mydictionaryapp.domain.model.Phonetic

data class PhoneticDto(
    val audio: String,
    val text: String
) {

    fun toPhonetics(): Phonetic = Phonetic(
        audio = audio,
        text = text
    )
}