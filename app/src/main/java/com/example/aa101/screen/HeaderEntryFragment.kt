package com.example.aa101.screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.example.aa101.R
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aa101.databinding.FragmentHeaderEntryBinding
import com.example.aa101.util.getShortMonthNameFromMonthNumber
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "HeaderEntryFragment"

@AndroidEntryPoint
class HeaderEntryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel by viewModels<SalesViewModel>()

    private lateinit var binding: FragmentHeaderEntryBinding
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_header_entry, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tilReceivedDate.setEndIconOnClickListener { v ->
            showDatePickerDialog(binding.tilReceivedDate)
        }

        binding.tilSaleDate.setEndIconOnClickListener { v ->
            showDatePickerDialog(binding.tilSaleDate)
        }

        binding.trademarkSpinner.setOnFocusChangeListener { view, b ->
            when (b) {
                true -> {}
                false -> {
                    if (binding.trademarkSpinner.text.isNotEmpty()) {
                        viewModel.setSelectedParty(binding.trademarkSpinner.text.toString())
                    }
                }
            }
        }

        binding.autoCompNoOfBox.setOnFocusChangeListener { view, b ->
            when (b) {
                false -> {
                    if (binding.autoCompNoOfBox.text.toString().isNotEmpty()) {
                        viewModel.setNoOfBox(binding.autoCompNoOfBox.text.toString().toInt())
                    }
                }
                else -> {}
            }
        }

        binding.autoCompTypeOfBox.setOnFocusChangeListener { view, b ->
            when (b) {
                false -> {
                    if (binding.autoCompTypeOfBox.text.isNotEmpty()) {
                        viewModel.setTypeOfBox(binding.autoCompTypeOfBox.text.toString())
                    }
                }
                else -> {}
            }
        }

        binding.autoCompTransportMedium.setOnFocusChangeListener { view, b ->
            when (b) {
                false -> {
                    if (binding.autoCompTransportMedium.text.isNotEmpty()) {
                        viewModel.setTransportMedium(binding.autoCompTransportMedium.text.toString())
                    }
                }
                else -> {}
            }
        }

        binding.tiedTransportDetail.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                viewModel.setTransportDetail(binding.tiedTransportDetail.text.toString())
            }
        }

        observeViewModel()
        initBoxTypeField()
        initTransportMedium()
        initPartyField()
    }

    private fun observeViewModel() {
        viewModel.apply {
            supplierList.observe(viewLifecycleOwner, {
                if (it != null) {
                    Log.i(TAG, "Got supplier List")
                    val supplierList: Array<out String> = it.toTypedArray()
                    val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        supplierList
                    )
                    binding.trademarkSpinner.setAdapter(adapter)
                }
            })

            moveToSalesEntry.observe(viewLifecycleOwner,  { header ->
                if (header != null && header.id > 0) {
                    val detailFragment = SalesEntryFragment.newInstance(header,"someParam")
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frag_container,detailFragment)
                        .addToBackStack(null)
                        .commit()
                }
                resetMoveToSalesEntry()
            })
        }
    }

    /**
     * @param v : the view that need to affected by the date selection
     * HINT: can make this a global funct in util and pass the selected date through closure function
     */
    private fun showDatePickerDialog(v: View?) {

        var cal = Calendar.getInstance()
        val thisYear = cal.get(Calendar.YEAR)
        val thisMonth = cal.get(Calendar.MONTH)
        val thisDay = cal.get(Calendar.DAY_OF_MONTH)

        Log.i(TAG, "year is $thisYear, month is $thisMonth, day is $thisDay")
        val datePickerDialog = DatePickerDialog(
            requireContext(), { view, year, calendarMonth, dayOfMonth ->
                val mon = getShortMonthNameFromMonthNumber(calendarMonth,true)
                when (v) {
                    binding.tilReceivedDate -> {
                        val dateText = "$dayOfMonth-$mon-$year"
                        binding.edReceivedDate.setText(dateText)
                        /** Calendar month start from 0 */
                        viewModel.setReceivedDate(LocalDate.of(year,calendarMonth+1,dayOfMonth))
                        Log.i(TAG, "view on date listener is ReceivedDate")
                    }
                    binding.tilSaleDate -> {
                        val dateText = "$dayOfMonth-$mon-$year"
                        binding.edSalesDate.setText(dateText)
                        /** Calendar month start from 0 */
                        viewModel.setSalesDate(LocalDate.of(year,calendarMonth+1,dayOfMonth))
                        Log.i(TAG, "view on date listener is SaleDate")
                    }
                }
            },
            thisYear,
            thisMonth,
            thisDay
        );

        datePickerDialog.show()

        /* After setting date in respective field pass focus to next field*/
    }

    private fun initBoxTypeField() {
        val boxType: Array<out String> = resources.getStringArray(R.array.box_type)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, boxType)
        binding.autoCompTypeOfBox.setAdapter(adapter)

    }

    private fun initPartyField() {
        viewModel.getSupplierTMList()
    }

    private fun initTransportMedium() {
        val transportType: Array<out String> = resources.getStringArray(R.array.transportation_mode)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            transportType
        )
        binding.autoCompTransportMedium.setAdapter(adapter)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HeaderEntryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeaderEntryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}