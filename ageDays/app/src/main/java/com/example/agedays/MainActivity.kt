package com.example.agedays

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        btnDatePicker.setOnClickListener { view->
            clickDataPicker(view)


        }


    }

    fun clickDataPicker(view: View){

        val myCalendar= Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate= "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yy", Locale.US)

                val theDate =sdf.parse(selectedDate)

                val selectedDateToDays = theDate!!.time/86400000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToDays = currentDate!!.time/86400000

                val differenceInDays= currentDateToDays - selectedDateToDays
                val differenceInDaysInt: Int = differenceInDays.toInt()

                tvSelectedDateInDays.text = differenceInDaysInt.toString()

            }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time -86400000)
        dpd.show()

    }
}