package uz.smartmuslim.wordsquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.smartmuslim.wordsquiz.databinding.GameScreenBinding

class GameScreen : Fragment() {

    private lateinit var binding: GameScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GameScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}