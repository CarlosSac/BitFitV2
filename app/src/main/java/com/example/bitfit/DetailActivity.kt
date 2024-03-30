package com.example.bitfit


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var calTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_detail)

//        val itemTv = findViewById<TextView>(R.id.entryTv)
//        calTv = findViewById<TextView>(R.id.numberTv)



//
//        dateTextView.text = note.date
//        durationTextView.text = note.sleepDuration.toString() + " Hours"
//        moodTextView.text = note.mood.toString() + "/10"
//        notesTextView.text = note.notes

    }
}