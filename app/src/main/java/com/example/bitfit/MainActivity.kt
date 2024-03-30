package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: RecyclerView
    private val entries = mutableListOf<DisplayEntry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        itemAdapter = findViewById(R.id.entries)
        val itemDisplay = EntryAdapter(this, entries)
        itemAdapter.adapter = itemDisplay

        itemAdapter.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            itemAdapter.addItemDecoration(dividerItemDecoration)
        }


        lifecycleScope.launch (Dispatchers.IO) {
            (application as EntryApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.item,
                        entity.calories,

                    )
                }.also { mappedList ->
//                    notesRecyclerView.adapter = NoteDisplayAdapter(this@MainActivity, mappedList)
                    entries.addAll(mappedList)
                    itemDisplay.notifyDataSetChanged()
                }
            }
        }

        val button = findViewById<Button>(R.id.itemBttn)
        button.setOnClickListener{
            val intent = Intent(this, DataAcivity::class.java)
            this.startActivity(intent)
        }

    }
}