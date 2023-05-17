package com.example.mydictionaryapp.domain.repository

import com.example.mydictionaryapp.core.utils.Resource
import com.example.mydictionaryapp.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


// it's our repository which retrieve the flow used by our presentation
interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}