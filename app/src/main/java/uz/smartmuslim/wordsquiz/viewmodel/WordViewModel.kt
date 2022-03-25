package uz.smartmuslim.wordsquiz.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.smartmuslim.wordsquiz.data.repository.WordRepository
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word
import uz.smartmuslim.wordsquiz.di.Injection


class WordViewModel(private val repository: WordRepository) : ViewModel() {

    fun getAllWords(): LiveData<List<Word>> = repository.getAllWords()

    fun getAllCompleted(): LiveData<List<Word>> = repository.getAllCompleted()

    fun addWord(wordEng: String, wordUz: String, wordOth: String) {
        viewModelScope.launch {
            repository.insert(Word(0, wordEng, wordUz, wordOth, false))
        }
    }

    fun updateWord(id: Int, wordEng: String, wordUz: String, wordOth: String, checked: Boolean) {
        viewModelScope.launch {
            repository.update(Word(id, wordEng, wordUz, wordOth, checked))
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            repository.deleteSelectedWords()
        }
    }

    fun clearWords() {
        viewModelScope.launch {
            repository.clearWords()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class WordViewModelFactory(private val mWordRepository: WordRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: WordViewModelFactory? = null

        fun getInstance(context: Context): WordViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: WordViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(mWordRepository) as T
    }
}