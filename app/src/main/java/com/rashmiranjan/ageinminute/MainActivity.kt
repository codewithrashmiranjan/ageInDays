package com.rashmiranjan.ageinminute

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tv_selectedDate: TextView
    lateinit var tv_inMinutesInDigit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tv_selectedDate = findViewById(R.id.tv_date)
        tv_inMinutesInDigit = findViewById(R.id.tv_inMinutesInDigit)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            Toast.makeText(this, "Button Works...", Toast.LENGTH_SHORT).show()
        }
    }

    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The chosen Date is : $year, the month of $month, the day is $day",
                    Toast.LENGTH_SHORT
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinute = theDate!!.time / 60000
                val selectedDateInDays = theDate!!.time
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val currentDateToDays = currentDate!!.time
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinute
                val differenceInDays = currentDateToDays - selectedDateInDays
                val dayCount: Long = differenceInDays / (24 * 60 * 60 * 1000)

                tv_selectedDate.text = selectedDate
//                tv_inMinutesInDigit.text = differenceInMinutes.toString()
                tv_inMinutesInDigit.text = dayCount.toString()

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}


