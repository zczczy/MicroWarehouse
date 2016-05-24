package com.zczczy.leo.microwarehouse.tools;

/**
 * Created by Leo on 2016/5/21.
 */
public class Constants {


    public static final String PAY_URL = "http://116.228.21.162:9127/umsFrontWebQmjf/umspay";
    //正式环境
//    public static final String PAY_URL = "https://mpos.quanminfu.com:8018/umsFrontWebQmjf/umspay";


    public static final String ANDROID = "1";//请求类型  android

    public static final int PAGE_COUNT = 10;

    public static final int MOBILE_LOGIN = 1; //登录类型（1：手机验证码登录，2：账号密码登录）
    public static final int NORMAL_LOGIN = 2; //登录类型（1：手机验证码登录，2：账号密码登录）

    public static final int LOING_CODE = 2; //1：用户注册，2：用户登录
    public static final int REGISTER_CODE = 1; //1：用户注册，2：用户登录

    public static final int UM_PAY = 1; //支付方式（1网银，2货到付款）
    public static final int CASH = 2; //支付方式（1网银，2货到付款）

    public static final String HOME_AD = "1";

    public static final String GOODS_TYPE = "1";  //跳转标识(1:商品类别页，2：商品明细)
    public static final String GOODS_DETAIL = "2"; //跳转标识(1:商品类别页，2：商品明细)


    public static final String ZONG_HE = "1"; //1:综合排序，2：销量降序，3：价格降序，4：价格升序
    public static final String SELL_COUNT = "2"; //1:综合排序，2：销量降序，3：价格降序，4：价格升序
    public static final String PRICE_DESC = "3"; //1:综合排序，2：销量降序，3：价格降序，4：价格升序
    public static final String PRICE_ASC = "4"; //1:综合排序，2：销量降序，3：价格降序，4：价格升序


    public static final int Goods_UP = 1; //1：上架，0：下架
    public static final int Goods_DOWN = 0; //1：上架，0：下架



    public static final int DUEPAYMENT = 0; //0:待支付
    public static final int PAID = 1;   //1：已支付
    public static final int CANCEL = 2; //2:已取消,
    public static final int SEND = 3; //3：已发货
    public static final int CONFIRM = 4; //4:确认收货
    public static final int FINISH = 5; //5:交易完成
    public static final int ALL_ORDER = 9; //5:交易完成

}
