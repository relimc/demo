package com.example.databindingtest1

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class ItemBind {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["android:imgUrl"])
        fun setUserPhoto(iView: ImageView, imageUri: String) {
            Picasso.get().load(imageUri).into(iView)
        }
    }

}