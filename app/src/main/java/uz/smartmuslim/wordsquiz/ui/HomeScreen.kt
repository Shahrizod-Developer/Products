package uz.smartmuslim.wordsquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import uz.smartmuslim.wordsquiz.R
import uz.smartmuslim.wordsquiz.databinding.HomeScreenBinding

class HomeScreen : Fragment() {

    private lateinit var binding: HomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenBinding.inflate(layoutInflater, container, false)

        binding.listOfWords.setOnClickListener {
            val navBuilder = NavOptions.Builder()
            navBuilder.setEnterAnim(R.anim.enter_from_right)
                .setExitAnim(R.anim.exit_to_left)
                .setPopEnterAnim(R.anim.enter_from_left)
                .setPopExitAnim(R.anim.exit_to_right)
            NavHostFragment.findNavController(this@HomeScreen)
                .navigate(R.id.action_homeScreen_to_listScreen, null, navBuilder.build())
        }


        binding.addWord.setOnClickListener {
            val navBuilder = NavOptions.Builder()
            navBuilder.setEnterAnim(R.anim.enter_from_right)
                .setExitAnim(R.anim.exit_to_left)
                .setPopEnterAnim(R.anim.enter_from_left)
                .setPopExitAnim(R.anim.exit_to_right)
            NavHostFragment.findNavController(this@HomeScreen)
                .navigate(R.id.action_homeScreen_to_addScreen, null, navBuilder.build())
        }


        return binding.root
    }
}