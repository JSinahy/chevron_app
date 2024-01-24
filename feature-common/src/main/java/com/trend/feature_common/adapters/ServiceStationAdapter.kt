package com.trend.feature_common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trend.chevron.R
import com.trend.feature_common.models.SSModel


class ServiceStationAdapter(ctx: Context, list: ArrayList<SSModel>): ArrayAdapter<SSModel>(ctx, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    private fun getMyView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_service_station_layout,
            parent,
            false
        )
        list.let {
            val txt = view.findViewById<TextView>(R.id.text_service_station)
            list?.let {
                txt.text = it.ch_ServiceStationName
            }
        }
        return view
    }
}