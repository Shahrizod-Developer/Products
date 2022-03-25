package uz.smartmuslim.wordsquiz.data.source.local

import androidx.lifecycle.LiveData
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word
import uz.smartmuslim.wordsquiz.data.source.local.room.WordDao

class LocalDataSource private constructor(private val wordDao: WordDao){


    companion object{

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(wordDao: WordDao): LocalDataSource = INSTANCE ?:LocalDataSource(wordDao)
    }

    fun getAllWords(): LiveData<List<Word>> = wordDao.loadWords()

    fun getAllCompleted(): LiveData<List<Word>> = wordDao.loadCompleted()

    suspend fun insert(word: Word) = wordDao.insertWord(word)

    suspend fun update(word: Word) = wordDao.updateWord(word)

    suspend fun deleteSelectedWords() = wordDao.deleteSelectedWords()

    suspend fun clearWords() = wordDao.clearWords()
}