package ru.android.assistantapp.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import ru.android.assistantapp.R
import ru.android.assistantapp.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    lateinit var binding: FragmentAuthBinding
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoginMask()
    }

    private fun initLoginMask() {
        installOn(
            binding.etPhone,
            PHONE_MASK,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                    authViewModel.setLogin(extractedValue)
                    colorBtnChanged(extractedValue)
                }
            }
        )
    }

    private fun colorBtnChanged(value: String) {
        if (value.length == 10) {
            binding.btnContinueMaterial.apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark))
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        } else {
            binding.btnContinueMaterial.apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
                setTextColor(ContextCompat.getColor(requireContext(),R.color.middle_gray_blue))
            }
        }
    }

    companion object {
        private const val PHONE_MASK = "+7 ([000]) [000]-[00]-[00]"
    }
}