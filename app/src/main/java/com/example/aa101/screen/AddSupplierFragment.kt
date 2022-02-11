package com.example.aa101.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.aa101.R
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aa101.databinding.FragmentAddSupplierBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddSupplierFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddSupplierFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val viewModel by viewModels<AddSupplierViewModel>()
    private lateinit var binding: FragmentAddSupplierBinding

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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_supplier,container,false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tiedSupplierName.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {}
                false -> {
                    if (binding.tiedSupplierName.text?.isNotEmpty() == true) {
                        viewModel.setSupplierName(binding.tiedSupplierName.text.toString())
                    }
                }
            }
        }
        binding.tiedSupplierTrademark.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {}
                false -> {
                    if (binding.tiedSupplierTrademark.text?.isNotEmpty() == true) {
                        viewModel.setSupplierTradeMark(binding.tiedSupplierTrademark.text.toString())
                    }
                }
            }
        }
        binding.tiedSupplierPhoneNo.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {}
                false -> {
                    if (binding.tiedSupplierPhoneNo.text?.isNotEmpty() == true) {
                        viewModel.setSupplierMobile(binding.tiedSupplierPhoneNo.text.toString())
                    }
                }
            }
        }
        binding.tiedSupplierEmail.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {}
                false -> {
                    if (binding.tiedSupplierEmail.text?.isNotEmpty() == true) {
                        viewModel.setSupplierEmail(binding.tiedSupplierEmail.text.toString())
                    }
                }
            }
        }
        binding.tiedSupplierAddress.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {}
                false -> {
                    if (binding.tiedSupplierAddress.text?.isNotEmpty() == true) {
                        viewModel.setSupplierEmail(binding.tiedSupplierAddress.text.toString())
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddSupplierFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSupplierFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}