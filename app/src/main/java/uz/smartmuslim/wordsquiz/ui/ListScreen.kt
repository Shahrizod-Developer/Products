package uz.smartmuslim.wordsquiz.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator
import uz.smartmuslim.wordsquiz.R
import uz.smartmuslim.wordsquiz.adapter.ListAdapter
import uz.smartmuslim.wordsquiz.databinding.ListScreenBinding
import uz.smartmuslim.wordsquiz.data.source.local.entity.Word
import uz.smartmuslim.wordsquiz.data.source.local.room.WordDatabase
import uz.smartmuslim.wordsquiz.utils.hideKeyboard
import uz.smartmuslim.wordsquiz.utils.shortToast
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModel
import uz.smartmuslim.wordsquiz.viewmodel.WordViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class ListScreen : Fragment() {

    private lateinit var binding: ListScreenBinding
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var viewModel: WordViewModel

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ListScreenBinding.inflate(inflater)
        setHasOptionsMenu(true)
        activity?.hideKeyboard()
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = WordViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[WordViewModel::class.java]

        mLayoutManager = LinearLayoutManager(requireContext())
        adapter = ListAdapter(viewModel)
        binding.rv.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        val mDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEEE, MMM dd yyyy")
        val currentDate = formatter.format(mDate)

        binding.apply {
            date.text = currentDate
            addTaskBtn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_listScreen_to_addScreen)
            )
        }
        binding.delete.setOnClickListener {

            val delete = "selected"
            showDialog(delete)
        }
        binding.deleteForever.setOnClickListener {
            val delete = "all"
            showDialog(delete)
        }

        // Setup RecyclerView
        setupRecyclerview()

        viewModel.getAllWords().observe(viewLifecycleOwner) { list ->
            adapter.setData(list)

            if (list.isEmpty()) {
                binding.noDataImage.visibility = View.VISIBLE
                binding.noDataText.visibility = View.VISIBLE
                binding.totalTask.text = "0"
            } else {
                binding.noDataImage.visibility = View.GONE
                binding.noDataText.visibility = View.GONE
                binding.totalTask.text = list.size.toString()
            }
        }

        viewModel.getAllCompleted().observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) binding.completed.text = list.size.toString()
            else binding.completed.text = "0"
        }
    }

    private fun setupRecyclerview() {
        with(binding.rv) {
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = FadeInUpAnimator().apply {
                addDuration = 100
            }
        }
    }


    private fun showDialog(delete: String) {
        when (delete) {
            "selected" -> {
                AlertDialog.Builder(requireContext(), R.style.MyDialogTheme).apply {
                    setTitle(getString(R.string.delete_checked_lists))
                    setMessage(getString(R.string.all_delete_are_you_sure))
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.deleteSelected()

                        context.shortToast(getString(R.string.toast_all_deleted))
                    }
                    setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.cancel()
                    }
                }.create().show()
            }
            "all" -> {
                AlertDialog.Builder(requireContext(), R.style.MyDialogTheme).apply {
                    setTitle(getString(R.string.clear_lists))
                    setMessage(getString(R.string.all_list_deleted_warning))
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.clearWords()

                        requireContext().shortToast(getString(R.string.all_deleted_add_todo))
                    }
                    setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.cancel()
                    }
                }.create().show()
            }
        }
    }

}