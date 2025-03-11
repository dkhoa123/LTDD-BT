package com.example.btvn_tuan2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter( val activity:Activity,val list: List<OutData> ): ArrayAdapter<OutData>(activity,R.layout.list_item) {
    override fun getCount(): Int {
        return list.size //vẽ lênt tất cả các dòng
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val context = activity.layoutInflater
        // layoutInflate là 1 component
        //giúp chuyển đổi layout xml thành view trong android
        val rowView = context.inflate(R.layout.list_item, parent,false)
        val images = rowView.findViewById<ImageView>(R.id.imagelist)
        val title = rowView.findViewById<TextView>(R.id.titlelist)
        val decs = rowView.findViewById<TextView>(R.id.decslist)

        title.text = list[position].title
        decs.text = list[position].dec
        images.setImageResource(list[position].image)

        return rowView
    }
}
