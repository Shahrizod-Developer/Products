package uz.smartmuslim.wordsquiz.di

import android.content.Context
import uz.smartmuslim.wordsquiz.data.repository.WordRepository
import uz.smartmuslim.wordsquiz.data.source.local.LocalDataSource
import uz.smartmuslim.wordsquiz.data.source.local.room.WordDatabase
import uz.smartmuslim.wordsquiz.data.source.local.room.WordDatabase_Impl


object Injection {

    fun provideRepository(context: Context): WordRepository {
        val database = WordDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.todoDAO())

        return WordRepository.getInstance(localDataSource)
    }
}