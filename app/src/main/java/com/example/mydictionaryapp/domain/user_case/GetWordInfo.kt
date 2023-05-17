package com.example.mydictionaryapp.domain.user_case

import com.example.mydictionaryapp.core.utils.Resource
import com.example.mydictionaryapp.domain.model.WordInfo
import com.example.mydictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(private val repository: WordInfoRepository) {

    // operator fun invoke allows us to call the class as a function
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}