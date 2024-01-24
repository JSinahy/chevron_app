package com.trend.feature_common.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trend.chevron.R
import com.trend.feature_common.models.MonthsModel

class MonthsLocalAdapter(val ctx: Context, list: List<MonthsModel>, val edit: Boolean): ArrayAdapter<MonthsModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyDropDownView(position, convertView, parent)
    }

    private fun getMyDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_month_black,
            parent,
            false
        )
        list.let {
            val txt = view.findViewById<TextView>(R.id.text_country_name)
            list?.let {
                txt.text = it.name
            }
        }

        return view
    }

    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_month_white,
            parent,
            false
        )

        list.let {
            val txt = view.findViewById<TextView>(R.id.text_country_name)
            list?.let {
                if(edit) txt.setTextColor(Color.parseColor("#ffffff"))
                txt.text = it.name
            }

        }

        return view
    }
}