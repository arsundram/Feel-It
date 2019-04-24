package com.arsundram.sac_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.share_post.*

class ListAdapter(val mCtex:Context,val layoutResId:Int,val thoughtlist:List<ThoughtDatabase>):ArrayAdapter<ThoughtDatabase>(mCtex,layoutResId,thoughtlist){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtex)
        val view: View=layoutInflater.inflate(layoutResId,null)


        val textViewname = view.findViewById<TextView>(R.id.tv_thought)
        val textViewUsername= view.findViewById<TextView>(R.id.tv_username)
        val textViewDate= view.findViewById<TextView>(R.id.tv_date)
        val imageViewProfile= view.findViewById<ImageView>(R.id.profilepic)


        val thoughtofday = thoughtlist[position]
        textViewname.text= thoughtofday.thought
        textViewUsername.text=thoughtofday.username
        textViewDate.text=thoughtofday.date
        var urival=thoughtofday.uri
        val picasso = Picasso.get()


        picasso.load(urival)
                .fit()
                .into(imageViewProfile)


        return view

    }
}