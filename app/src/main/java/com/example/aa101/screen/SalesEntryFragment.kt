package com.example.aa101.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aa101.R
import com.example.aa101.data.room.model.SalesHeaders
import com.example.aa101.databinding.FragmentSalesEntryNewBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SalesEntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class SalesEntryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val TAG = "SalesEntryFragment"
    private var param1: SalesHeaders? = null
    private var param2: String? = null

    private val viewModel by viewModels<SalesViewModel>()
    private lateinit var binding: FragmentSalesEntryNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sales_entry_new, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Sample to how to use autocomplete textView setting adapter. Doesn't work through xml */
//        val countries: Array<out String> = resources.getStringArray(R.array.sample_autocomplete_text_list)
//        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,countries)
//        binding.autoCompleteTextView.setAdapter(adapter)

        param1.let { binding.header = it }
        if (param1 != null) {
            Log.i(TAG, "header detail is: ${param1.toString()}")
        } else {
            Log.i(TAG, "header detail is NOT AVAILABLE")
        }
    }

    private fun initCustomerList() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SalesEntryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: SalesHeaders, param2: String) =
            SalesEntryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}