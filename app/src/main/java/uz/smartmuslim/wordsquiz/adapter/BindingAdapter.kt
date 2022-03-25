package uz.smartmuslim.wordsquiz.adapter

import android.graphics.Paint
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word
import uz.smartmuslim.wordsquiz.ui.HomeScreenDirections
import uz.smartmuslim.wordsquiz.ui.ListScreenDirections
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModel

@BindingAdapter(value = ["word", "vm"])
fun isChecking(checkBox: CheckBox, word: Word, viewModel: WordViewModel) {
    checkBox.setOnCheckedChangeListener(null)
    checkBox.isChecked = word.checked

    checkBox.setOnCheckedChangeListener { _, b ->
        if (b) {
            viewModel.updateWord(
                word.id,
                word.wordEng,
                word.wordUz,
                word.wordOth,
                true
            )
        } else {
            viewModel.updateWord(
                word.id,
                word.wordEng,
                word.wordUz,
                word.wordOth,
                false
            )
        }
        Log.i("checked", "checked " + word.checked)
    }
}

@BindingAdapter("android:strikeThrough")
fun isStriked(textView: TextView, isCheck: Boolean) {
//    if (isCheck) {
//        //textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//    } else {
////        textView.paintFlags =
////            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//    }
}

@BindingAdapter("android:goToEdit")
fun goToEditFragment(imageView: ImageView, word: Word) {
    imageView.setOnClickListener { view ->
        view.findNavController()
            .navigate(ListScreenDirections.actionListScreenToEditScreen(word))
    }
}