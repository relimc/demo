package com.windfallsheng.componentbasedaction.component_image.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;

public class ImgUtils {

    public static void loadImage(Context context, ImageView imageView, String url, int placeholder) {
        Logger.dl("loadImage#url=" + url);
        Glide.with(context)
                .load(url)
                .error(placeholder)
                .placeholder(placeholder)
                .into(imageView);
    }
}
