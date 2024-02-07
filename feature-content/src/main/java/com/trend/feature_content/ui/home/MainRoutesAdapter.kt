package com.trend.feature_content.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trend.chevron.R
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.models.MainContentModel
import com.trend.feature_common.utils.DrawableUtils
import com.trend.feature_common.utils.LayoutUtils

class MainRoutesAdapter(val context: Context,
                        val click: HomeFragment.OnClickListener,
                        var type: TypeAccount,
                        val openTest: HomeFragment.OnClickOpenRouteTest
): RecyclerView.Adapter<MainRoutesAdapter.ViewHolder>(), ApplyUIMainContent by ApplyUIMainContentImpl() {

    private lateinit var dataRoutes: ArrayList<MainContentModel>
    fun setData(dataRoutes: ArrayList<MainContentModel>){
        this.dataRoutes = dataRoutes
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(when(type) {
            TypeAccount.HAVOLINE -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemHavolineLayout(), parent, false)
            TypeAccount.TEXACO -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemTexacoLayout(), parent, false)
            TypeAccount.DELO -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemDeloLayout(), parent, false)
            TypeAccount.HAVOLINE4T -> LayoutInflater.from(parent.context).inflate(LayoutUtils.getItemHavoline4tLayout(), parent, false)
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textRoute.text = dataRoutes[position].ch_NameRoute.uppercase().substring(0,4)
        applyUIRoutesAdapter(
            holder.textRoute.context,
            holder,
            type,
            dataRoutes[position],
            openTest
        )

        /** Para aumentar la barra de progreso **/
        var countStatus = 0
        dataRoutes[position].ch_Paradas.forEach {
            if(it.ch_StatusLesson1 == 1) {
                countStatus++
            }
            if(it.ch_StatusLesson2 == 1) {
                countStatus++
            }
            if(it.ch_StatusLesson3 == 1) {
                countStatus++
            }
        }

        holder.progressBar.progress = countStatus

        if(dataRoutes[position].ch_IdRoadmap < 6) {
            holder.relativeLayout.setOnClickListener {
                if(holder.recycler.visibility == View.VISIBLE){
                    holder.recycler.visibility = View.GONE
                } else {
                    holder.recycler.visibility = View.VISIBLE
                }
            }
        }

        val adapter = MainStopsAdapter(HomeFragment.OnClickListener { parada, valor, idStop, statusLesson, IdRoad ->
                click.onClick(parada, valor, idStop, statusLesson, IdRoad)
            }, type,
            HomeFragment.OnClickOpenRouteTest { IdRoute, IdTest, IdStop ->
                openTest.onClick(IdRoute, IdTest, IdStop)
            }, dataRoutes[position].ch_IdRoadmap)
        adapter.setData(dataRoutes[position].ch_Paradas)
        holder.recycler.adapter = adapter
    }

    override fun getItemCount(): Int {
        return dataRoutes.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textRoute: TextView
        val imgRoute: ImageView
        val relativeLayout: RelativeLayout
        val recycler: RecyclerView
        val imgLock: ImageView
        val progressBar: ProgressBar

        init {
            textRoute = view.findViewById(DrawableUtils.getTextRoute())
            imgRoute = view.findViewById(DrawableUtils.getImgRoute())
            relativeLayout = view.findViewById(DrawableUtils.getRelativeLayout())
            recycler = view.findViewById(DrawableUtils.getRvStops())
            imgLock = view.findViewById(DrawableUtils.getLockImage())
            progressBar = view.findViewById(DrawableUtils.getProgress())
        }
    }

}
