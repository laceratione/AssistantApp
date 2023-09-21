package ru.android.assistantapp.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.android.assistantapp.R
import ru.android.assistantapp.databinding.FragmentDocumentsBinding
import ru.android.assistantapp.detailstask.TasksViewModel

class DocumentsFragment:Fragment() {
    lateinit var binding: FragmentDocumentsBinding
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.documents_toolbar_title)

        binding.btnCompleteOrder.setOnClickListener {
            tasksViewModel.isOrderCompleted = true
            findNavController().popBackStack()
        }
    }
}