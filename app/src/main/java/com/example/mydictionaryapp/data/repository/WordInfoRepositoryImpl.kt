package com.example.mydictionaryapp.data.repository

import com.example.mydictionaryapp.core.utils.Resource
import com.example.mydictionaryapp.data.database.dao.WordInfoDao
import com.example.mydictionaryapp.data.remote.DictionaryApi
import com.example.mydictionaryapp.data.remote.dto.WordInfoDto
import com.example.mydictionaryapp.domain.model.WordInfo
import com.example.mydictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(private val api: DictionaryApi, private val dao: WordInfoDao) :
    WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> =
        flow {
            emit(Resource.Loading())
            val wordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
            emit(Resource.Loading(data = wordInfo))

            try {
                val remoteWordInfo: List<WordInfoDto> = api.getWordInfo(word)
                dao.deleteWordInfo(remoteWordInfo.map { it.word })
                dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })


            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error, Something went wrong", data = wordInfo))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Could not reach server , check your internet connection",
                        data = wordInfo
                    )
                )
            }
            val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
            emit(Resource.Success(newWordInfo))
        }


}
