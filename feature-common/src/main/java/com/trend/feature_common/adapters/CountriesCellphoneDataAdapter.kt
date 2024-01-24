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
import com.trend.feature_common.models.CountriesCellphoneDataModel

class CountriesCellphoneDataAdapter(ctx: Context, list: List<CountriesCellphoneDataModel>, val isPhone: Boolean = false, val edit: Boolean = false): ArrayAdapter<CountriesCellphoneDataModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyDropDownView(position, convertView, parent)
    }

    private fun getMyDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_country_cellphone_layout,
            parent,
            false
        )
        val txt = view.findViewById<TextView>(R.id.text_country_flag)
        val code = view.findViewById<TextView>(R.id.text_country_code)
        list?.let {
            txt.text = it.flag.toFlagEmoji() + " " + it.name
            if(isPhone) {
                txt.text = it.flag.toFlagEmoji() + " " + it.code
            }
            code.text = it.code
        }
        return view
    }


    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_country_and_flag_layout,
            parent,
            false
        )
        list.let {
            val txt = view.findViewById<TextView>(R.id.text_flag_and_country)
            list?.let {
                txt.text = it.flag.toFlagEmoji() + " " + it.code
                if(edit) txt.setTextColor(Color.parseColor("#ffffff"))
            }
        }
        return view
    }
}