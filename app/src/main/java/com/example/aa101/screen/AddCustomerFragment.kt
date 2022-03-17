package com.example.aa101.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.aa101.R
import com.example.aa101.databinding.FragmentAddCustomerBinding
import com.example.aa101.util.empty
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "AddCustomerFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddCustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddCustomerBinding
    val viewModel by viewModels<AddCustomerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_customer, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tiedCustomerName.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setCustomerName(binding.tiedCustomerName.text.toString())
            }
        }

        binding.tiedCustomerInitial.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.checkForValidInitial(it.toString())
                viewModel.setCustomerInitial(binding.tiedCustomerInitial.text.toString())
            }
        }

        binding.tiedCustomerPhoneNo.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setCustomerMobNo(binding.tiedCustomerPhoneNo.text.toString())
            }
        }

        binding.tiedCustomerEmail.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setCustomerEmail(binding.tiedCustomerEmail.text.toString())
            }
        }

        binding.tiedCustomerAddress.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setCustomerAddress(binding.tiedCustomerAddress.text.toString())
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            customerInitialErrorMessage.observe(viewLifecycleOwner, Observer {
                when (it.isNullOrBlank()) {
                    true -> {
                        binding.tiedCustomerInitial.error = null
                    }
                    false -> {
                        binding.tiedCustomerInitial.error = it
                    }
                }
            })
            isCustomerAdded.observe(viewLifecycleOwner) {
                if (it) {
                    showCustomerAddedMessage()
                    clearFields()
                    resetCustomerAdded()
                }
            }
        }
    }

    private fun clearFields() {
        binding.tiedCustomerInitial.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedCustomerEmail.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedCustomerPhoneNo.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedCustomerName.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedCustomerAddress.apply {
            setText(String.empty())
            clearFocus()
        }
    }

    private fun showCustomerAddedMessage() {
        Toast.makeText(requireContext(),
            "customer: ${viewModel.customerInitial.value} added ",
            Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddCustomerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}