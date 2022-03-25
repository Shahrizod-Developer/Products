package uz.smartmuslim.wordsquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.smartmuslim.wordsquiz.R
import uz.smartmuslim.wordsquiz.databinding.AddScreenBinding
import uz.smartmuslim.wordsquiz.utils.hideKeyboard
import uz.smartmuslim.wordsquiz.utils.shortToast
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModel
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModelFactory

class AddScreen : Fragment() {

    private lateinit var binding: AddScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = AddScreenBinding.inflate(layoutInflater, container, false)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        val viewModelFactory = WordViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[WordViewModel::class.java]

        binding.save.setOnClickListener {
            val wordEng: String = binding.wordEng.text.toString().trim()
            val wordUz: String = binding.wordUz.text.toString().trim()
            val wordOth: String = binding.wordOth.text.toString().trim()

            if (wordEng.isNotBlank() && wordUz.isNotBlank()) {
                todoViewModel.addWord(wordEng, wordUz, wordOth)
                activity?.hideKeyboard()
                Toast.makeText(requireContext(), "$wordEng Saved", Toast.LENGTH_SHORT).show()
                binding.wordEng.text = null
                binding.wordUz.text = null
                binding.wordOth.text = null
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }
        return binding.root
    }
}