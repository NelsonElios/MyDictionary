package com.example.mydictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.example.mydictionaryapp.data.database.Converters
import com.example.mydictionaryapp.data.database.WordInfoDatabase
import com.example.mydictionaryapp.data.remote.DictionaryApi
import com.example.mydictionaryapp.data.repository.WordInfoRepositoryImpl
import com.example.mydictionaryapp.data.utils.GsonParser
import com.example.mydictionaryapp.domain.repository.WordInfoRepository
import com.example.mydictionaryapp.domain.user_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    // Provides returns exactly the type of the stuff we want to provide

    // Provides the database
    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "wordInfo_Db")
            .addTypeConverter(Converters(GsonParser(gson = Gson())))
            .build()
    }

    //Provides repository
    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, db: WordInfoDatabase): WordInfoRepository {
        return WordInfoRepositoryImpl(api = api, dao = db.dao)
    }


    //Provides the api for remote calls
    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder().baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(DictionaryApi::class.java)
    }

    // Provides the user case
    @Provides
    @Singleton
    fun provideUserCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }
}