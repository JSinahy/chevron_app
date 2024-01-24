package com.trend.feature_common.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trend.chevron.R
import com.trend.feature_common.models.StudyTimeModel

class StudyTimeAdapter(val ctx: Context, list: ArrayList<StudyTimeModel>, val edit: Boolean): ArrayAdapter<StudyTimeModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyDropDownView(position, convertView, parent)
    }

    private fun getMyDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_study_time_black,
            parent,
            false
        )

        list.let {
            list?.let {
                val txt = view.findViewById<TextView>(R.id.text_study_time)
                txt.text = it.Name

            }
        }
        return view
    }

    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_study_time_white,
            parent,
            false
        )

        list.let {
            list?.let {
                val txt = view.findViewById<TextView>(R.id.text_study_time)
                txt.text = it.Name
                if(edit){
                    txt.setTextColor(Color.parseColor("#ffffff"))
                }
            }
        }
        return view
    }
}