package com.trend.feature_content.ui.home

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.models.ParadasModel
import com.trend.feature_common.utils.DrawableUtils
import com.trend.feature_common.utils.LayoutUtils

class MainStopsAdapter(val ctx: Context,
                       val click: HomeFragment.OnClickListener,
                       var type: TypeAccount,
                       val clickRoute: HomeFragment.OnClickOpenRouteTest,
                       val roadMap: Int
): RecyclerView.Adapter<MainStopsAdapter.ViewHolder>(), ApplyUIMainContent by ApplyUIMainContentImpl() {

    private lateinit var dataStops: ArrayList<ParadasModel>
    fun setData(dataStops: ArrayList<ParadasModel>){
        this.dataStops = dataStops
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(when(type) {
                TypeAccount.HAVOLINE -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemStopHavolineLayout(), parent, false)
                TypeAccount.TEXACO -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemStopTexacoLayout(), parent, false)
                TypeAccount.DELO -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemStopDeloLayout(), parent, false)
                TypeAccount.HAVOLINE4T -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemStopHavoline4tLayout(), parent, false)
            }
        )
    }

    override fun onBindViewHolder(holder: MainStopsAdapter.ViewHolder, position: Int) {
        val item = dataStops[position]
        if(item.ch_Type == 0){
            holder.lockImage.visibility = View.GONE

            holder.textTitle.text = item.ch_NameStop
            holder.textSubtitle.text = item.ch_SubtitleStop
            holder.textText1.text = item.ch_Title1
            holder.textText2.text = item.ch_Title2
            holder.textAudio.text = item.ch_Title3

            applyUIStopsAdapter(
                context = holder.lockImage.context,
                typeAccount = type,
                mainStopModel = item,
                viewHolder = holder
            )

            holder.text_1.setOnClickListener {
                click.onClick(dataStops[position], 1, item.ch_IdStop, item.ch_StatusLesson1, roadMap)
            }
            holder.text_2.setOnClickListener {
                click.onClick(dataStops[position], 2, item.ch_IdStop, item.ch_StatusLesson2, roadMap)
            }
            holder.audio.setOnClickListener {
                click.onClick(dataStops[position], 3, item.ch_IdStop, item.ch_StatusLesson3, roadMap)
            }
        } else {
            holder.text_1.visibility = View.GONE
            holder.text_2.visibility = View.GONE
            holder.audio.visibility = View.GONE
            holder.textSubtitle.visibility = View.INVISIBLE
            holder.lockImage.visibility = View.VISIBLE

            holder.textTitle.updateLayoutParams<RelativeLayout.LayoutParams> {
               bottomMargin = 8
            }

            if(item.ch_IdStatus == "A") {
                holder.lockImage.setImageDrawable(AppCompatResources.getDrawable(holder.textTitle.context, DrawableUtils.getLock()))
            }

            when(type) {
                TypeAccount.HAVOLINE -> {
                    holder.textTitle.setTextColor(Color.parseColor("#FFD200"))
                }
                TypeAccount.TEXACO -> {
                    holder.textTitle.setTextColor(Color.parseColor("#FFFFFF"))
                }
                else -> {
                    holder.textTitle.setTextColor(Color.parseColor("#808080"))
                }
            }

            holder.textTitle.text = item.ch_NameStop + " " + roadMap

            holder.textTitle.setOnClickListener {
                clickRoute.onClick(roadMap, item.ch_Type, item.ch_IdStop)
            }

        }
    }

    override fun getItemCount(): Int {
        return dataStops.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textTitle: TextView
        val textSubtitle: TextView
        val textText1: TextView
        val textText2: TextView
        val textAudio: TextView
        val text_1: LinearLayout
        val text_2: LinearLayout
        val audio: LinearLayout
        val statusLesson1: ImageView
        val statusLesson2: ImageView
        val statusLesson3: ImageView
        val lockImage: ImageView

        init {
            textTitle = view.findViewById(DrawableUtils.getTitleStop())
            textSubtitle = view.findViewById(DrawableUtils.getSubtitleStop())
            textText1 = view.findViewById(DrawableUtils.getTitleText1())
            textText2 = view.findViewById(DrawableUtils.getTitleText2())
            textAudio = view.findViewById(DrawableUtils.getTitleAudio())
            text_1 = view.findViewById(DrawableUtils.getText1())
            text_2 = view.findViewById(DrawableUtils.getText2())
            audio = view.findViewById(DrawableUtils.getAudio())
            statusLesson1 = view.findViewById(DrawableUtils.getStatusLesson1())
            statusLesson2 = view.findViewById(DrawableUtils.getStatusLesson2())
            statusLesson3 = view.findViewById(DrawableUtils.getStatusLesson3())
            lockImage = view.findViewById(DrawableUtils.getLockImage())
        }
    }

}