package com.example.aa101.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.aa101.R
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.aa101.data.room.SalesDatabase
import com.example.aa101.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddCustomer.setOnClickListener {
            onAddCustomerClicked()
        }

        binding.btnAddExpense.setOnClickListener {
            onAddNewExpenseClicked()
        }

        binding.btnAddSales.setOnClickListener {
            onAddNewSalesEntryClicked()
        }

        binding.btnAddSupplier.setOnClickListener {
            onAddSupplierClicked()
        }

        binding.btnViewSales.setOnClickListener {
            onViewSalesClicked()
        }

        binding.btnViewExpense.setOnClickListener {
            onViewExpenseClicked()
        }

        binding.btnViewCustomerBalance.setOnClickListener {
            onViewCustomerBalanceClicked()
        }

        binding.btnViewPartyBalance.setOnClickListener {
            onViewPartyBalanceClicked()
        }
        initDao()
    }

    private fun initDao() {
        CoroutineScope(Dispatchers.IO).launch {
            SalesDatabase.getRoomDBInstance(requireContext().applicationContext)
        }
    }


    fun onAddNewSalesEntryClicked() {
        val headerEntryFragment = HeaderEntryFragment.newInstance("param1", "param2")
        goToScreen(headerEntryFragment)
    }

    fun onAddNewExpenseClicked() {
        val RecordPaymentFragment = RecordPaymentFragment.newInstance("param1", "param2")
        goToScreen(RecordPaymentFragment)
    }

    fun onAddCustomerClicked() {
        val addCustomerFragment = AddCustomerFragment.newInstance("param1", "param2")
        goToScreen(addCustomerFragment)
    }

    fun onAddSupplierClicked() {
        val addSupplierFragment = AddSupplierFragment.newInstance("param1", "param2")
        goToScreen(addSupplierFragment)
    }

    fun onViewSalesClicked() {
        Toast.makeText(requireContext(),"To be implemented", Toast.LENGTH_LONG).show()
    }

    fun onViewExpenseClicked() {
        Toast.makeText(requireContext(),"To be implemented", Toast.LENGTH_LONG).show()
    }

    fun onViewPartyBalanceClicked() {
        Toast.makeText(requireContext(),"To be implemented", Toast.LENGTH_LONG).show()
    }

    fun onViewCustomerBalanceClicked() {
        Toast.makeText(requireContext(),"To be implemented", Toast.LENGTH_LONG).show()
    }

    fun goToScreen(newFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frag_container,newFragment).addToBackStack(null).commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}