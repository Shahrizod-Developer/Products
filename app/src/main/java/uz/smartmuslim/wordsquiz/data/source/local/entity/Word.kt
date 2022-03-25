package uz.smartmuslim.wordsquiz.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Word(

    @PrimaryKey(autoGenerate = true) var id: Int,
    var wordEng: String,
    var wordUz: String,
    var wordOth: String,
    var checked: Boolean

) : Parcelable