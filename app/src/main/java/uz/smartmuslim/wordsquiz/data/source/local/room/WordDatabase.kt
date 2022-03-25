package uz.smartmuslim.wordsquiz.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word


@Database(version = 1, entities = [Word::class])
abstract class WordDatabase : RoomDatabase() {

    abstract fun todoDAO(): WordDao

    companion object { // singleton
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getInstance(context: Context): WordDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java,
                        "word_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}