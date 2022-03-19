package com.example.aa101.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aa101.R
import com.example.aa101.databinding.FragmentRecordPaymentBinding
import com.example.aa101.screen.payment.PaymentType
import com.example.aa101.util.datePickerDialog
import com.example.aa101.util.empty
import com.example.aa101.util.toShowDate
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "RecordPaymentFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [RecordPaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RecordPaymentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel by viewModels<RecordPaymentViewModel>()
    private lateinit var binding: FragmentRecordPaymentBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_record_payment,container,false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tblayoutCreditDebit.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Log.i(TAG,"credit tab selected")
                        onCreditTabSelected()
                    }
                    1 -> {
                        Log.i(TAG,"debit tab selected")
                        onDebitTabSelected()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Log.i(TAG,"onTabReselected: credit tab selected")
                        onCreditTabSelected()
                    }
                    1 -> {
                        Log.i(TAG,"onTabReselected: debit tab selected")
                        onDebitTabSelected()
                    }
                }
            }
        })

        GlobalScope.async (Dispatchers.Main) {
            delay(500L)
            onCreditTabSelected()
        }

        binding.tilPaymentDate.setEndIconOnClickListener {
            datePickerDialog(requireContext()) {
                viewModel.setPaymentDate(it)
            }
        }

        binding.tiedPaymentToFrom.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setBenef(it.toString())
            }
        }

        binding.tiedPaymentAmount.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setPaymentAmount(it.toString().toDouble())
            }
        }

        binding.tiedPaymentDetail.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setPaymentDetail(it.toString())
            }
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.apply {
            beneficiaryList.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    val benefiList = it.toTypedArray()
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        benefiList
                    )
                    binding.tiedPaymentToFrom.setAdapter(adapter)
                }
            }

            benefactorList.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    val benefaList = it.toTypedArray()
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        benefaList
                    )
                    binding.tiedPaymentToFrom.setAdapter(adapter)
                }
            }

            paymentDate.observe(viewLifecycleOwner){
                binding.tiedPaymentDate.setText(it.toShowDate())
            }

            paymentType.observe(viewLifecycleOwner) { type ->
                when (type) {
                    PaymentType.CREDIT-> {
                        binding.tvPageTitle.text = getString(R.string.record_credit_payment)
                        binding.tilPaymentToFrom.hint = getString(R.string.received_from)
                        getBenefactorList()
                    }
                    PaymentType.DEBIT -> {
                        binding.tvPageTitle.text = getString(R.string.record_debit_payment)
                        binding.tilPaymentToFrom.hint = getString(R.string.paid_to)
                        getBeneficiaryList()
                    }
                }
            }

            isPaymentAdded.observe(viewLifecycleOwner) {
                if (it) {
                    showPaymentAddedMessage()
                    clearFields()
                    resetPaymentAdded()
                }
            }
        }
    }

    private fun clearFields() {
        binding.apply {
            tiedPaymentDate.setText(String.empty())
            tiedPaymentDate.clearFocus()
            tiedPaymentAmount.setText(String.empty())
            tiedPaymentAmount.clearFocus()
            tiedPaymentDetail.setText(String.empty())
            tiedPaymentDetail.clearFocus()
            tiedPaymentToFrom.setText(String.empty())
            tiedPaymentToFrom.clearFocus()
        }
    }

    private fun showPaymentAddedMessage() {
        Toast.makeText(requireContext(), "Payment added", Toast.LENGTH_SHORT).show()
    }
    private fun onCreditTabSelected() = viewModel.setPaymentType(PaymentType.CREDIT)

    private fun onDebitTabSelected() = viewModel.setPaymentType(PaymentType.DEBIT)

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecordPaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecordPaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}