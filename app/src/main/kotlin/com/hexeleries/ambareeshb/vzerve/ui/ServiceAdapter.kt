package com.hexeleries.ambareeshb.vzerve.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexeleries.ambareeshb.vzerve.R
import com.hexeleries.ambareeshb.vzerve.Service
import kotlinx.android.synthetic.main.service_layout.view.*

/**
 * Created by ambareeshb on 10/12/17.
 * Adapter services.
 */
class ServiceAdapter : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    var services: List<Service>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return services?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindService(services?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.service_layout, parent, false))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindService(service: Service?) {
            itemView.textServiceName?.text = service?.servicename
        }


    }
}

