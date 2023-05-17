package com.example.mydictionaryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mydictionaryapp.data.database.dao.WordInfoDao
import com.example.mydictionaryapp.data.database.entity.WordInfoEntity


@Database(entities = [WordInfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    // in this abstract class we put abstract val that represents all the Dao we have in your database
    abstract val dao: WordInfoDao
}