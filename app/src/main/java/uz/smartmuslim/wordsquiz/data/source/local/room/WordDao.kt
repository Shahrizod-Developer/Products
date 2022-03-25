package uz.smartmuslim.wordsquiz.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word


@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun loadWords(): LiveData<List<Word>>

    @Query("SELECT * FROM word WHERE checked = 1")
    fun loadCompleted(): LiveData<List<Word>>

    @Insert
    suspend fun insertWord(todo: Word)

    @Update
    suspend fun updateWord(todo: Word)

    @Delete
    suspend fun deleteWord(todo: Word)

    @Query("DELETE FROM word WHERE checked = 1")
    suspend fun deleteSelectedWords()

    @Query("DELETE FROM word")
    suspend fun clearWords()
}