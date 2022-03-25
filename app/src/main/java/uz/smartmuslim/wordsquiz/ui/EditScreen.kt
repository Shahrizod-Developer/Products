package uz.smartmuslim.wordsquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.smartmuslim.wordsquiz.R
import uz.smartmuslim.wordsquiz.databinding.EditScreenBinding
import uz.smartmuslim.wordsquiz.utils.hideKeyboard
import uz.smartmuslim.wordsquiz.utils.shortToast
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModel
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModelFactory


class EditScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mWord = EditScreenArgs.fromBundle(requireArguments()).word
        // Inflate the layout for this fragment
        val binding = EditScreenBinding.inflate(inflater).apply {
            word = mWord
        }

        val viewModelFactory = WordViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[WordViewModel::class.java]

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.update.setOnClickListener {
            val updatedWordEng = binding.wordEng.text.toString()
            val updatedWordUz = binding.wordUz.text.toString()
            val updatedWordOth = binding.wordOth.text.toString()

            if (updatedWordEng.isNotBlank() && updatedWordUz.isNotBlank()) {
                todoViewModel.updateWord(
                    mWord!!.id,
                    updatedWordEng,
                    updatedWordUz,
                    updatedWordOth,
                    mWord.checked
                )
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}
