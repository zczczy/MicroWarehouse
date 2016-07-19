package com.zczczy.leo.microwarehouse.viewgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zczczy.leo.microwarehouse.R;
import com.zczczy.leo.microwarehouse.views.GlideSliderView;

/**
 * @author Created by LuLeo on 2016/6/14.
 *         you can contact me at :361769045@qq.com
 *         <p/>
 *         Only show textview(description)
 * @since 2016/6/14.
 */
public class HornSliderView extends GlideSliderView {

    public HornSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_horn, null);
        ImageView target = (ImageView) v.findViewById(R.id.slider_image);
        TextView description = (TextView) v.findViewById(R.id.description);
        description.setText(getDescription());
        bindEventAndShow(v, target);
        return v;
    }
}
