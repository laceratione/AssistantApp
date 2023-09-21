package ru.android.assistantapp.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.android.assistantapp.R
import ru.android.assistantapp.databinding.FragmentTasksBinding
import ru.android.assistantapp.detailstask.TasksViewModel
import ru.android.assistantapp.domain.Task

class TasksFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    private val tasksViewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.tasks_toolbar_title)

        val taskClickListener = object : TasksAdapter.OnItemTaskClickListener {
            override fun onItemClick(task: Task, position: Int) {
                val action =
                    TasksFragmentDirections.actionTasksFragmentToDetailsTaskFragment(task, position)
                findNavController().navigate(action)
            }
        }
        val tasksAdapter = TasksAdapter(taskClickListener)
        binding.rvTasks.adapter = tasksAdapter

        tasksViewModel.tasks.observe(viewLifecycleOwner, {
            tasksAdapter.update(it)
        })
    }

}