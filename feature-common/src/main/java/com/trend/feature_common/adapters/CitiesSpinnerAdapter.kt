package com.trend.feature_common.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trend.feature_common.R
import com.trend.feature_common.models.CitiesModel

class CitiesSpinnerAdapter(ctx: Context, list: ArrayList<CitiesModel>, val edit: Boolean): ArrayAdapter<CitiesModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyDropDownView(position, convertView, parent)
    }

    private fun getMyDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_city_layout_black,
            parent,
            false
        )

        list.let {
            val txt = view.findViewById<TextView>(R.id.text_city)
            list?.let {
                txt.text = it.ch_Name
            }
        }

        return view
    }

    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_city_layout_white,
            parent,
            false
        )

        list.let {
            val txt = view.findViewById<TextView>(R.id.text_city)
            list?.let {
                txt.text = it.ch_Name
                if(edit) txt.setTextColor(Color.parseColor("#ffffff"))
            }
        }

        return view
    }
}