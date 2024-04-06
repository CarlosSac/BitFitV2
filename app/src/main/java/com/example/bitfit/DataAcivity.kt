package com.example.bitfit;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.entry_detail)


        val food = findViewById<EditText>(R.id.foodEt)
        val calories = findViewById<EditText>(R.id.caloriesEt)
        val submit = findViewById<Button>(R.id.recordBttn)





        submit.setOnClickListener{

            lifecycleScope.launch(Dispatchers.IO) {
                (application as EntryApplication).db.articleDao().insert(

                    EntryEntity(

                        item = food.text.toString(),
                        calories = calories.text.toString()
                    )
                )
            }

            val intent = Intent(this@DataAcivity, MainActivity::class.java)
            this.startActivity(intent)
        }









    }
}

