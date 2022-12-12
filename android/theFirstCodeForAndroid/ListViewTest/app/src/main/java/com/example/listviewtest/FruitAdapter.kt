package com.example.listviewtest

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class FruitAdapter(activity: Activity, private val resourceId: Int, data: List<Fruit>) : ArrayAdapter<Fruit>(activity, resourceId, data) {

    inner class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)

    // ListView 中的每一项在屏幕中展示出来时，会调用这个方法
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            Log.d("getView", position.toString())

            // 布局填充器：给 ListView 中的每一项加载视图文件
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        if (fruit != null) {
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }
        return view
    }
}