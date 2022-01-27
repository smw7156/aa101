package com.example.aa101

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aa101.screen.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    @Inject
    lateinit var randomString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG,"the random string is: $randomString")
        setFragment()
    }

    private fun setFragment() {

        //SalesEntryFragment.newInstance("myHeader", "myBody"),
        //HeaderEntryFragment.newInstance("myHeader", "myBody"),
        //RecordPaymentFragment.newInstance("myHeader", "myBody"),
        //HomeFragment.newInstance("myHeader", "myBody")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frag_container,HomeFragment.newInstance("myHeader", "myBody"))
            .commit()
    }


}