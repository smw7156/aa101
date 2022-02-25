package com.example.aa101.util.databinding

import androidx.databinding.BindingAdapter
import com.example.aa101.util.toShowDate
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDate

@BindingAdapter("setDateText")
fun MaterialTextView.setText(date: LocalDate) {
    val dateText = date.toShowDate()
    text = dateText
}