package com.example.aa101.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aa101.R
import com.example.aa101.databinding.FragmentAddCustomerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "AddCustomerFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddCustomerBinding
    private lateinit var viewModel: AddCustomerViewModel

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_customer, container, false)
        viewModel = ViewModelProvider(this).get(AddCustomerViewModel::class.java)
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tiedCustomerName.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {

                }
                false -> {
                    viewModel.setCustomerName(binding.tiedCustomerName.text.toString())
                }
            }
        }

        binding.tiedCustomerInitial.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {

                }
                false -> {
                    viewModel.setCustomerInitial(binding.tiedCustomerInitial.text.toString())
                }
            }
        }
        binding.tiedCustomerPhoneNo.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {

                }
                false -> {
                    viewModel.setCustomerMobNo(binding.tiedCustomerPhoneNo.text.toString())
                }
            }
        }
        binding.tiedCustomerEmail.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {

                }
                false -> {
                    viewModel.setCustomerEmail(binding.tiedCustomerEmail.text.toString())
                }
            }
        }
        binding.tiedCustomerAddress.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {

                }
                false -> {
                    viewModel.setCustomerAddress(binding.tiedCustomerAddress.text.toString())
                }
            }
        }
        binding.tiedCustomerInitial.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(p0: Editable?) {
                    Log.i(TAG,"afterTextChanged for customerInitial: ${p0.toString()})")
                    viewModel.checkForValidInitial(p0.toString())
                }

            }
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            customerInitialErrorMessage.observe(viewLifecycleOwner, Observer {
                when (it.isNullOrBlank()) {
                    true -> {

                    }
                    false -> {
                        binding.tiedCustomerInitial.error = it
                    }
                }
            })
        }
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