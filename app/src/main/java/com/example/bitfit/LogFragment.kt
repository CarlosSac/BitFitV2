package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogFragment : Fragment() {

    private val entries = mutableListOf<DisplayEntry>()
    private lateinit var itemAdapter: RecyclerView
    private lateinit var itemDisplay : EntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        itemAdapter = view.findViewById<RecyclerView>(R.id.entriesRV)
        itemDisplay = EntryAdapter(requireContext(), entries)
        itemAdapter.adapter = itemDisplay

        itemAdapter.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            itemAdapter.addItemDecoration(dividerItemDecoration)
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            lifecycleScope.launch(Dispatchers.IO) {
                (requireActivity().application as EntryApplication).db.articleDao().getAll()
                    .collect { databaseList ->
                        entries.clear()
                        databaseList.map { entity ->
                            DisplayEntry(
                                entity.item,
                                entity.calories,

                                )
                        }.also { mappedList ->
                            entries.addAll(mappedList)
                            launch(Dispatchers.Main) {
                                Log.d("test", "update")

                                itemDisplay.notifyDataSetChanged()

                            }
                        }


                    }
            }



        val button = view.findViewById<Button>(R.id.itemBttn)
        button.setOnClickListener {
            val intent = Intent(requireActivity(), DataAcivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): LogFragment {
            return LogFragment()
        }
    }


}