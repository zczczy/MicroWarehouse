package com.zczczy.leo.microwarehouse.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.zczczy.leo.microwarehouse.items.CartItemView_;
import com.zczczy.leo.microwarehouse.items.CartRecommendItemView_;
import com.zczczy.leo.microwarehouse.listener.OttoBus;
import com.zczczy.leo.microwarehouse.model.BaseModelJson;
import com.zczczy.leo.microwarehouse.model.CartModel;
import com.zczczy.leo.microwarehouse.tools.AndroidTool;
import com.zczczy.leo.microwarehouse.tools.Constants;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by Leo on 2016/5/21.
 */
@EBean
public class CartAdapter extends BaseRecyclerViewAdapter<CartModel> {

    @Bean
    OttoBus bus;


    @Override
    public void getMoreData(Object... objects) {
        BaseModelJson<List<CartModel>> result;
        myRestClient.setHeader("Token", pre.token().get());
        myRestClient.setHeader("Kbn", Constants.ANDROID);
        result = myRestClient.getBuyCartInfo();
        afterGetMoreData(result);
    }

    @Override
    protected void afterGetMoreData(BaseModelJson<List<CartModel>> result) {
        AndroidTool.dismissLoadDialog();
        if (result == null) {
            AndroidTool.showToast(context, no_net);
        } else if (!result.Successful) {
            AndroidTool.showToast(context, result.Error);
        } else {
            clear();
            insertAll(result.Data, isFooter ? getItemCount() - 1 : getItemCount());
            if (result.Data.size() == 0) {
                itemNotify(false);
            }
        }
    }

    public View createFooterView(ViewGroup parent, int viewType) {
        return CartRecommendItemView_.build(context);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return CartItemView_.build(context);
    }

    /**
     * 通知 fragment
     *
     * @param objects 可变参数
     */
    @Override
    public void itemNotify(Object... objects) {
        bus.post(objects[0]);
    }
}
