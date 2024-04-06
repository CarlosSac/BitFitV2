package com.example.bitfit

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Byte

class DashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calAvg: TextView = activity?.findViewById(R.id.avgCalTv)!!
        val calMin: TextView = activity?.findViewById(R.id.minCalTv)!!
        val calMax: TextView = activity?.findViewById(R.id.maxCalTv)!!

        var num = 0
        var sum = 0
        var avg = 0

        var minVal: Int = Byte.MAX_VALUE.toInt()
        var maxVal: Int = Byte.MIN_VALUE.toInt()

        lifecycleScope.launch(Dispatchers.IO) {
            for (item in (activity?.application as EntryApplication).db.articleDao().getCalories()) {
                num+=1
                sum += Integer.parseInt(item)

                avg = sum / num

                calAvg.text = avg.toString()

                if (Integer.parseInt(item) < minVal){
                    minVal = Integer.parseInt(item)
                }
                if (Integer.parseInt(item) > maxVal){
                    maxVal = Integer.parseInt(item)
                }
                calMin.text = maxVal.toString()
                calMax.text = minVal.toString()

            }
        }
    }

}