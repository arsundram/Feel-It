package com.arsundram.sac_app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class HobbiesAdapter(val context: Context,val thoughts: List<thought>): RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return thoughts.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val th=thoughts[p1]
        p0.setData(th,p1)

    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(th: thought?,pos:Int){
            itemView.tv_username.text=th!!.username
            itemView.tv_thought.text=th!!.data
            itemView.tv_date.text=th!!.date
        }
    }
}