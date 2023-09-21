package ru.android.assistantapp.detailstask

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import ru.android.assistantapp.R
import ru.android.assistantapp.databinding.FragmentDetailsTaskBinding

class DetailsTaskFragment : Fragment() {
    lateinit var binding: FragmentDetailsTaskBinding
    private val tasksViewModel: TasksViewModel by activityViewModels()
    private val args: DetailsTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.details_toolbar_title)

        val task = args.task
        val pos = args.pos
        when (task.state) {
            TaskState.Accept -> {
                showButtonsAccept()
            }
            TaskState.Refuse -> {
                hideButtonsRefuse()
            }
            TaskState.Default -> {}
            else -> {}
        }

        binding.btnRefuse.setOnClickListener {
            task.state = TaskState.Refuse
            tasksViewModel.setStateTask(task, pos)
            hideButtonsRefuse()
        }

        binding.btnAccept.setOnClickListener {
            task.state = TaskState.Accept
            showButtonsAccept()
        }
    }

    private fun showButtonsAccept(){
        binding.btnAccept.visibility = View.GONE
        binding.btnRefuse.visibility = View.GONE

        initButtonsAccept()
        initWarningMessage()
    }

    private fun hideButtonsRefuse(){
        binding.btnAccept.visibility = View.GONE
        binding.btnRefuse.visibility = View.GONE
        binding.llRulesClient.visibility = View.GONE
    }

    private fun initButtonsAccept() {
        with(binding) {
            llAcceptButtons.addView(addButtonWithIcon(getString(R.string.btn_incident_text)).apply {
                setOnClickListener {
                    findNavController().navigate(R.id.action_detailsTaskFragment_to_incidentFragment)
                }
            })
            llAcceptButtons.addView(addButtonWithIcon(getString(R.string.btn_error_text)).apply {
                setOnClickListener {
                    findNavController().navigate(R.id.action_detailsTaskFragment_to_incidentFragment)
                }
            })
            llAcceptButtons.addView(addButtonWithIcon(getString(R.string.btn_pin_documents_text)).apply {
                setOnClickListener {
                    findNavController().navigate(R.id.action_detailsTaskFragment_to_documentsFragment)
                }
            })

            llAcceptButtons.addView(addButton(getString(R.string.btn_order_complete_text), R.color.middle_gray_blue, R.color.gray).apply {
                isEnabled = false
                if (tasksViewModel.isOrderCompleted == true){
                    isEnabled = true
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bt))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.dark))
                }
            })
            llAcceptButtons.addView(addButton(getString(R.string.btn_refuse_task_text), R.color.dark, R.color.bt))
        }
    }

    private fun addButtonWithIcon(name: String): MaterialButton {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.bottomMargin = 12

        val iconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_right)

        val btn = MaterialButton(requireContext()).apply {
            text = name
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            layoutParams = params
            isClickable = true
//            typeface = Typeface.createFromAsset(requireContext().assets, "font/stolzl_regular.otf")
            setTextColor(ContextCompat.getColor(requireContext(), R.color.dark))
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, iconDrawable, null)
        }
        return btn
    }

    private fun addButton(name: String, textColor: Int, backColor: Int): MaterialButton {
        val btn = MaterialButton(requireContext()).apply {
            text = name
            setTextColor(ContextCompat.getColor(requireContext(), textColor))
            setBackgroundColor(ContextCompat.getColor(requireContext(), backColor))
        }
        return btn
    }

    private fun initWarningMessage() {
        val textView = TextView(requireContext()).apply {
            text = context.getString(R.string.details_task_warning_message)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.middle_gray_blue))
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0, 12, 0, 32)
                }
        }
        binding.llAcceptButtons.addView(textView)
    }
}

enum class TaskState {
    Accept, Refuse, Default
}