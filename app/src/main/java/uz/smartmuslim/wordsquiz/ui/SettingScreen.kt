package uz.smartmuslim.wordsquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.smartmuslim.wordsquiz.databinding.SettingScreenBinding


class SettingScreen : Fragment() {

    private lateinit var binding: SettingScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SettingScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}