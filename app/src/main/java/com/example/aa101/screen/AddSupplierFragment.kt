package com.example.aa101.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.aa101.R
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aa101.databinding.FragmentAddSupplierBinding
import com.example.aa101.util.empty
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

        binding.tiedSupplierName.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setSupplierName(it.toString())
            }
        }

        binding.tiedSupplierTrademark.addTextChangedListener{
            if (!it.isNullOrEmpty()) {
                viewModel.setSupplierTradeMark(it.toString())
            }
        }

        binding.tiedSupplierPhoneNo.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setSupplierMobile(it.toString())
            }
        }
        binding.tiedSupplierEmail.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setSupplierEmail(it.toString())
            }
        }
        binding.tiedSupplierAddress.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setSupplierAddress(it.toString())
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.apply {
            isSupplierAdded.observe(viewLifecycleOwner) {
                if (it) {
                    showSupplierAddedMessage()
                    clearFields()
                    resetSupplierAdded()
                }
            }
        }
    }

    private fun clearFields() {
        binding.tiedSupplierAddress.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedSupplierName.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedSupplierTrademark.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedSupplierEmail.apply {
            setText(String.empty())
            clearFocus()
        }
        binding.tiedSupplierPhoneNo.apply {
            setText(String.empty())
            clearFocus()
        }
    }

    private fun showSupplierAddedMessage() {
        Toast.makeText(requireContext(),
            "Supplier: ${viewModel.partyTrademark.value} added ",
            Toast.LENGTH_LONG).show()
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