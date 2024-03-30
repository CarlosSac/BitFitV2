package com.example.bitfit

import android.content.Context
import android.content.Intent
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EntryAdapter(private val context: Context, private val entries: List<DisplayEntry>) :
    RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.entries_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryAdapter.ViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount() = entries.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val itemTv = itemView.findViewById<TextView>(R.id.entryTv)
        private val numberTv = itemView.findViewById<TextView>(R.id.numberTv)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // Get selected article
            val entry = entries[adapterPosition]

            // Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("ENTRY_EXTRA", entry)
            context.startActivity(intent)
        }

        fun bind(entry: DisplayEntry) {
            itemTv.text = entry.item.toString()
            numberTv.text = entry.calories.toString()
        }
    }

}