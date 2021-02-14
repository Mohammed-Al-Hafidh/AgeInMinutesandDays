package com.alhafidh.mohammed.ageinminutesanddays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private  lateinit var btnDatePicker : Button
    private lateinit var tvAgeinMinutes: TextView
    private lateinit var tvAgeinDays: TextView
    private lateinit var tvSelectedDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker=findViewById(R.id.btnDatePicker)
        tvAgeinMinutes=findViewById(R.id.txtSelectedDateinMinutes)
        tvAgeinDays=findViewById(R.id.txtSelectedDateinDays)
        tvSelectedDate=findViewById(R.id.txtSelectedDate)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show()
        }

    }
    fun clickDatePicker(view: View){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)


        val dpd=DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->


            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            val dateInMinutes=theDate!!.time/60000
            val dateInDays=theDate!!.time/60000/(60*24)

            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate!!.time/60000
            val currentDateInDays=currentDate!!.time/60000/(60*24)
            val differenceInMinutes=currentDateInMinutes-dateInMinutes
            val differenceInDays=currentDateInDays-dateInDays
            tvAgeinMinutes.setText(differenceInMinutes.toString())
            tvAgeinDays.setText((differenceInDays.toString()))

        }
                ,year
                ,month
                ,day)
        dpd.datePicker.maxDate=(Date().time-86400000)
        dpd.show()
    }
}