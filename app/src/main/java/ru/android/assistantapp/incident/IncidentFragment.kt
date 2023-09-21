package ru.android.assistantapp.incident

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.android.assistantapp.R
import ru.android.assistantapp.databinding.FragmentIncidentBinding

class IncidentFragment : Fragment() {
    lateinit var binding: FragmentIncidentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.incident_toolbar_title)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            setClickableBtn()
        }
        binding.btnSave.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setClickableBtn() {
        binding.btnSave.apply {
            isEnabled = true
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }
}