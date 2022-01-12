package com.example.aa101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment()
    }

    private fun setFragment() {

        //SalesEntryFragment.newInstance("myHeader", "myBody") , HeaderEntryFragment.newInstance("myHeader", "myBody")
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frag_container,SalesEntryFragment.newInstance("myHeader", "myBody"))
            .commit()
    }


}