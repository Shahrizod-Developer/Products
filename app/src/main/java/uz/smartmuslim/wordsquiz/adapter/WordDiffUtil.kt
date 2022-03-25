package uz.smartmuslim.wordsquiz.adapter

import androidx.recyclerview.widget.DiffUtil
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word


class WordDiffUtil(
    private val oldList: List<Word>,
    private val newList: List<Word>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].wordEng == newList[newItemPosition].wordEng
                && oldList[oldItemPosition].wordUz == newList[newItemPosition].wordUz
                && oldList[oldItemPosition].wordOth == newList[newItemPosition].wordOth
                && oldList[oldItemPosition].checked == newList[newItemPosition].checked
    }
}