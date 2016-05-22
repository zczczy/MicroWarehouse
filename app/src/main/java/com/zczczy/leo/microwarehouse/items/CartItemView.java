package com.zczczy.leo.microwarehouse.items;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zczczy.leo.microwarehouse.R;
import com.zczczy.leo.microwarehouse.model.BaseModel;
import com.zczczy.leo.microwarehouse.model.CartModel;
import com.zczczy.leo.microwarehouse.prefs.MyPrefs_;
import com.zczczy.leo.microwarehouse.rest.MyErrorHandler;
import com.zczczy.leo.microwarehouse.rest.MyRestClient;
import com.zczczy.leo.microwarehouse.tools.AndroidTool;
import com.zczczy.leo.microwarehouse.tools.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.HashMap;

import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by Leo on 2016/5/21.
 */
@EViewGroup(R.layout.fragment_cart_item)
public class CartItemView extends ItemView<CartModel> implements QuantityView.OnQuantityChangeListener {

    @ViewById
    CheckBox cb_select;

    @ViewById
    ImageView img_cart_goods_img;

    @ViewById
    TextView txt_goods_name, txt_goods_price;

    @StringRes
    String text_goods_price;

    @ViewById
    QuantityView quantityView;

    @RestService
    MyRestClient myRestClient;

    @Bean
    MyErrorHandler myErrorHandler;

    @Pref
    MyPrefs_ pre;

    public CartItemView(Context context) {
        super(context);
    }

    @AfterInject
    void setMyErrorHandler() {
        myRestClient.setRestErrorHandler(myErrorHandler);
    }

    @AfterViews
    void afterView() {
        quantityView.setQuantityClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        quantityView.setOnQuantityChangeListener(this);
    }

    @Override
    protected void init(Object... objects) {
        quantityView.setQuantity(_data.goodsNum);
        txt_goods_name.setText(_data.goodsName);
        txt_goods_price.setText(String.format(text_goods_price, _data.goodsPrice));
        cb_select.setChecked(_data.isChecked);
    }

    @Click
    void cb_select() {
        _data.isChecked = cb_select.isChecked();
        //通知adapter
        baseRecyclerViewAdapter.itemNotify(_data.isChecked);
    }


    @Override
    public void onQuantityChanged(int newQuantity, boolean programmatically) {
        if (!programmatically) {
//            AndroidTool.showLoadDialog(context);
            if (newQuantity > _data.goodsNum) {
                addShoppingCart();
            } else {
                subShoppingCart();
            }
        }
        if (newQuantity == quantityView.getMinQuantity()) {
            quantityView.getChildAt(0).setEnabled(false);
            quantityView.getChildAt(2).setEnabled(true);
        } else if (newQuantity == quantityView.getMaxQuantity()) {
            quantityView.getChildAt(0).setEnabled(true);
            quantityView.getChildAt(2).setEnabled(false);
        } else {
            quantityView.getChildAt(0).setEnabled(true);
            quantityView.getChildAt(2).setEnabled(true);
        }
        //如果选中的的时候 才会通知adapter
        if (_data.isChecked) {
            baseRecyclerViewAdapter.itemNotify(true);
        }
    }

    @Override
    public void onLimitReached() {

    }

    @Background(serial = "subShopping")
    void subShoppingCart() {
        myRestClient.setHeader("Token", pre.token().get());
        myRestClient.setHeader("Kbn", Constants.ANDROID);
        HashMap<String, String> map = new HashMap<>();
//        map.put("GoodsInfoId", _data.GoodsInfoId);
//        afterAubShoppingCart(myRestClient.subShoppingCart(map));
    }

    @UiThread
    void afterAubShoppingCart(BaseModel bm) {
        AndroidTool.dismissLoadDialog();
        if (bm == null) {
            AndroidTool.showToast(context, "修改失败");
        } else if (!bm.Successful) {
            AndroidTool.showToast(context, bm.Error);
        } else {
            _data.goodsNum--;
//            cartActivity.setTotalMoney();
//            cartAdapter.notifyItemChanged(baseViewHolder.getAdapterPosition());
        }
    }

    @Background(serial = "addShopping")
    void addShoppingCart() {
        myRestClient.setHeader("Token", pre.token().get());
        myRestClient.setHeader("Kbn", Constants.ANDROID);
        HashMap<String, String> map = new HashMap<>();
//        map.put("GoodsInfoId", _data.GoodsInfoId);
//        afterAddShoppingCart(myRestClient.addShoppingCart(map));
        afterAddShoppingCart(null);
    }

    @UiThread
    void afterAddShoppingCart(BaseModel bm) {
        AndroidTool.dismissLoadDialog();
        if (bm == null) {
            AndroidTool.showToast(context, "商品添加失败");
            quantityView.setQuantity(_data.goodsNum);
        } else if (!bm.Successful) {
            AndroidTool.showToast(context, bm.Error);
            quantityView.setQuantity(_data.goodsNum);
        } else {
            _data.goodsNum++;
//            cartActivity.setTotalMoney();
//            cartAdapter.notifyItemChanged(baseViewHolder.getAdapterPosition());
        }
    }


    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
