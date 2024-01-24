package com.trend.feature_common.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trend.chevron.R
import com.trend.feature_common.extensiones.toFlagEmoji
import com.trend.feature_common.models.CountriesModel

class CountriesSpinnerAdapter(ctx: Context, list: List<CountriesModel>, val edit: Boolean): ArrayAdapter<CountriesModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyDropDownView(position, convertView, parent)
    }

    private fun getMyDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_country_and_city_black,
            parent,
            false
        )

        val txt = view.findViewById<TextView>(R.id.text_country_name)
        val flag = view.findViewById<TextView>(R.id.text_country_flag)
        list?.let {
            flag.text = it.ch_Flag.toFlagEmoji()
            txt.text = it.ch_Name

        }

        return view
    }

    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_country_and_city_black,
            parent,
            false
        )

        val txt = view.findViewById<TextView>(R.id.text_country_name)
        val flag = view.findViewById<TextView>(R.id.text_country_flag)
        list?.let {
            flag.text = it.ch_Flag.toFlagEmoji()
            txt.text = it.ch_Name
            if(edit) txt.setTextColor(Color.parseColor("#ffffff"))
        }

        return view
    }
}