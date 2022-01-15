package com.example.aa101

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aa101.screen.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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