package uz.smartmuslim.wordsquiz.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import uz.smartmuslim.wordsquiz.data.source.local.LocalDataSource
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word
import uz.smartmuslim.wordsquiz.data.source.local.room.WordDatabase


class WordRepository(private val localDataSource: LocalDataSource) : IWordRepository {

    companion object {
        @Volatile
        private var instance: WordRepository? = null

        fun getInstance(localData: LocalDataSource): WordRepository =
            instance ?: synchronized(this) {
                instance ?: WordRepository(localData)
            }
    }

    override fun getAllWords(): LiveData<List<Word>> {
        return localDataSource.getAllWords()
    }

    override fun getAllCompleted(): LiveData<List<Word>> {
        return localDataSource.getAllCompleted()
    }

    override suspend fun insert(word: Word) {
        localDataSource.insert(word)
    }

    override suspend fun update(word: Word) {
        localDataSource.update(word)
    }

    override suspend fun deleteSelectedWords() {
        localDataSource.deleteSelectedWords()
    }

    override suspend fun clearWords() {
        localDataSource.clearWords()
    }
}