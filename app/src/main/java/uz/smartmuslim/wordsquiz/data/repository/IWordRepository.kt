package uz.smartmuslim.wordsquiz.data.repository

import androidx.lifecycle.LiveData
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word


interface IWordRepository {

    fun getAllWords(): LiveData<List<Word>>

    fun getAllCompleted(): LiveData<List<Word>>

    suspend fun insert(todo: Word)

    suspend fun update(todo: Word)

    suspend fun deleteSelectedWords()

    suspend fun clearWords()
}