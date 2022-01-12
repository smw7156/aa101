package com.example.aa101

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.aa101.data.room.SalesDatabase
import com.example.aa101.databinding.FragmentHeaderEntryBinding
import com.example.aa101.util.getShortMonthNameFromMonthNumber
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "HeaderEntryFragment"


class HeaderEntryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated called")
        binding.tilReceivedDate.setEndIconOnClickListener { v ->
            showDatePickerDialog(binding.tilReceivedDate)
        }

        binding.tilSaleDate.setEndIconOnClickListener { v ->
            showDatePickerDialog(binding.tilSaleDate)
        }
        initDao()
    }

    private fun initDao() {
        val salesHeaderDao =
            SalesDatabase.getRoomDBInstance(requireContext().applicationContext).salesHeaderDao()
    }

    /**
     * @param v : the view that need to affected by the date selection
     */
    private fun showDatePickerDialog(v: View?) {

        val cal = Calendar.getInstance()
        val thisYear = cal.get(Calendar.YEAR)
        val thisMonth = cal.get(Calendar.MONTH)
        val thisDay = cal.get(Calendar.DAY_OF_MONTH)

        Log.i(TAG, "year is $thisYear, month is $thisMonth, day is $thisDay")
        val datePickerDialog = DatePickerDialog(
            requireContext(), { view, year, month, dayOfMonth ->
                val mon = getShortMonthNameFromMonthNumber(month)
                when (v) {
                    binding.tilReceivedDate -> {
                        val dateText = "$dayOfMonth-$mon-$year"
                        binding.edReceivedDate.setText(dateText)
                        Log.i(TAG, "view on date listener is tilReceivedDate")
                    }
                    binding.tilSaleDate -> {
                        val dateText = "$dayOfMonth-$mon-$year"
                        binding.edSalesDate.setText(dateText)
                        Log.i(TAG, "view on date listener is tilSaleDate")
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