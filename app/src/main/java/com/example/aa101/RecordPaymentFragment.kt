package com.example.aa101

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.aa101.databinding.FragmentRecordPaymentBinding
import com.google.android.material.tabs.TabLayout
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
class RecordPaymentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_record_payment,container,false)
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
//                        binding.tvPageTitle.text = "Record a Credit Payment"
                    }
                    1 -> {
                        Log.i(TAG,"debit tab selected")
                        onDebitTabSelected()
//                        binding.tvPageTitle.text = "Record a Debit Payment"
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Log.i(TAG,"onTabReselected: credit tab selected")
                        onCreditTabSelected()
//                        binding.tvPageTitle.text = "Record a Credit Payment"
                    }
                    1 -> {
                        Log.i(TAG,"onTabReselected: debit tab selected")
                        onDebitTabSelected()
//                        binding.tvPageTitle.text = "Record a Debit Payment"
                    }
                }
            }
        })

        GlobalScope.async (Dispatchers.Main) {
            delay(500L)
            onCreditTabSelected()
//            binding.tblayoutCreditDebit.selectTab(binding.tblayoutCreditDebit.getTabAt(0), true)
        }
    }

    private fun onCreditTabSelected() {
        binding.tvPageTitle.text = "Record a Credit Payment"
        binding.tilPaymentToFrom.hint = "Received from"
        //reset Benefactor autocomplete adapter list
    }

    private fun onDebitTabSelected() {
        binding.tvPageTitle.text = "Record a Debit Payment"
        binding.tilPaymentToFrom.hint = "Paid to"
        // reset Beneficiary autocomplete adapter list
    }

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