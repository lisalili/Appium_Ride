package rider.lib;

public class XpathCommon {
	//订单列表
	public final static String Orderlist_ShopName_relative = "/android.widget.RelativeLayout[contains(@resource-id,\"top_lay\")]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[contains(@resource-id,\"name\")]";
	//baiduzhuansong
	public final static String Orderlist_BaiduDelivery_relative  = "/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView";
	//zhuangtai
	public final static String Orderlist_Status_relative = "/android.widget.RelativeLayout[2]/android.widget.TextView[contains(@resource-id,\"status\")]";
	//button
	public final static String Orderlist_Button_relative ="/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button";
	
	//众包骑士评价
	public final static String Comment_ZhongBao_PeiSong_Score_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"baidu_send_score\")]/android.widget.ImageView[1]";//众包骑士评价-配送服务-1星
	public final static String Comment_ZhongBao_PeiSong_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"send_comment_container\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.view.View/android.widget.TextView[1]";//第一标签
	public final static String Comment_ZhongBao_ShangPin_Score_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_score\")]/android.widget.ImageView[5]";//众包骑士评价-商品质量-5星
	public final static String Comment_ZhongBao_ShangPin_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_comment_container\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.view.View/android.widget.TextView[2]";//第二标签
	//百度骑士评价
	public final static String Comment_Baidu_PeiSong_Score_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"baidu_send_score\")]/android.widget.ImageView[1]";
	public final static String Comment_Baidu_PeiSong_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"baidu_send_comment_content\")]/android.widget.RelativeLayout[2]/android.widget.LinearLayout[2]/android.view.View/android.widget.TextView[1]";
	public final static String Comment_Baidu_ShangPin_Score_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_score\")]/android.widget.ImageView[5]";
	public final static String Comment_Baidu_ShangPin_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_comment_container\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.view.View/android.widget.TextView[2]";
	//自配送评价
	public final static String Comment_Self_PeiSong_Score_Xpath ="//android.widget.LinearLayout[contains(@resource-id,\"self_send_score_container\")]/android.widget.LinearLayout[contains(@resource-id,\"self_send_score\")]/android.widget.ImageView[1]";
	public final static String Comment_Self_PeiSong_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"send_comment_container\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.view.View/android.widget.TextView[1]";
	public final static String Comment_Self_ShangPin_Score_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_score_container\")]/android.widget.LinearLayout[contains(@resource-id,\"dish_score\")]/android.widget.ImageView[5]";
	public final static String Comment_Self_ShangPin_Tag_Xpath = "//android.widget.LinearLayout[contains(@resource-id,\"dish_comment_container\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.view.View/android.widget.TextView[2]";


/*
 * 购物车
 */
	//列表元素xpath
	public final static String ShoppingCart_dish_relative = "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout";
	public final static String ShoppingCart_dish_Title_relative = ShoppingCart_dish_relative+"/android.widget.RelativeLayout[contains(@resource-id,\"waimai_shopmenu_info_container\")]/android.widget.TextView";
	public final static  String waimai_shopmenu_leftnum_container = "/android.widget.RelativeLayout[contains(@resource-id,\"waimai_shopmenu_leftnum_container\")]";
	public final static  String waimai_shopmenu_adapter_item_plus_minus_container = "/android.widget.LinearLayout[contains(@resource-id,\"waimai_shopmenu_adapter_item_plus_minus_container\")]";
	public final static  String shopmenu_shopmenu_adapter_item_support_attr_container = "/android.widget.LinearLayout[contains(@resource-id,\"shopmenu_shopmenu_adapter_item_support_attr_container\")]";

	//可选规格-xpath
	public final static String ShoppingCart_dish_plus_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + waimai_shopmenu_adapter_item_plus_minus_container+"/android.widget.ImageButton[contains(@resource-id,\"waimai_shopmenu_dish_plus\")]";
	public final static String ShoppingCart_dish_minus_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + waimai_shopmenu_adapter_item_plus_minus_container+"/android.widget.ImageButton[contains(@resource-id,\"waimai_shopmenu_dish_minus\")]";
	public final static String ShoppingCart_dish_Num_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + waimai_shopmenu_adapter_item_plus_minus_container+"/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_dish_count\")]";
	public final static String ShoppingCart_dish_Optional_pecification_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + shopmenu_shopmenu_adapter_item_support_attr_container+"/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_adapter_item_support_standard\")]";
	//
	public final static String ShoppingCart_dish_Price_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + "/android.widget.LinearLayout[1]/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_adapter_item_price\")]";
	public final static String ShoppingCart_dish_Price_Original_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + "/android.widget.LinearLayout[1]/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_adapter_item_discard_price\")]";
	public final static String ShoppingCart_dish_allStore_relative = ShoppingCart_dish_relative +waimai_shopmenu_leftnum_container + "/android.widget.LinearLayout[1]/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_adapter_item_mini_leftnum\")]";
			
	public final static String ShoppingCart_dish_Picture_relative = ShoppingCart_dish_relative + "/android.widget.LinearLayout[contains(@resource-id,\"waimai_shoplist_adapter_item_shop_image_container\")]/android.widget.ImageView[contains(@resource-id,\"waimai_shopmenu_adapter_item_image\")]";
	public final static String ShoppingCart_dish_Tag_relative = ShoppingCart_dish_relative+"/android.widget.LinearLayout[contains(@resource-id,\"waimai_shopmenu_adapter_item_tag_desc_layout\")]/android.view.View[contains(@resource-id,\"waimai_shopmenu_adapter_item_tag\")]/android.widget.TextView";
	public final static String ShoppingCart_dish_seal_relative = ShoppingCart_dish_relative+"/android.widget.LinearLayout[contains(@resource-id,\"waimai_shopmenu_sold_container\")]/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_adapter_item_sold\")]";
	public final static String ShoppingCart_dish_discount_info_relative = ShoppingCart_dish_relative+"/android.widget.TextView[contains(@resource-id,\"discount_info\")]";

	
	
	//总价-bottom-弹窗--xpath;PS:相对路径需到View
	public final static String ShoppingCart_Bottom_List = "//android.widget.ListView[contains(@resource-id,\"waimai_shopmenu_buydish_listview\")]";
	public final static String ShoppingCart_Bottom_plus_relative = "/android.widget.RelativeLayout/android.widget.RelativeLayout[contains(@resource-id,\"dish_drag\")]/android.widget.LinearLayout[contains(@resource-id,\"waimai_shopmenu_dish_item_plug_container\")]/android.widget.ImageButton[contains(@resource-id,\"waimai_shopmenu_dish_plus\")]";
	public final static String ShoppingCart_Bottom_minus_relative ="/android.widget.RelativeLayout/android.widget.RelativeLayout[contains(@resource-id,\"dish_drag\")]/android.widget.LinearLayout[contains(@resource-id,\"waimai_shopmenu_dish_item_plug_container\")]/android.widget.ImageButton[contains(@resource-id,\"waimai_shopmenu_dish_minus\")]";
	public final static String ShoppingCart_Bottom_Dish_Title_relative ="/android.widget.RelativeLayout/android.widget.RelativeLayout[contains(@resource-id,\"dish_drag\")]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_dish_item_name\")]";
	public final static String ShoppingCart_Bottom_Price_relative = "/android.widget.RelativeLayout/android.widget.RelativeLayout[contains(@resource-id,\"dish_drag\")]/android.widget.TextView[contains(@resource-id,\"waimai_shopmenu_dish_item_total_price\")]";

	
	//骑士侧栏xpath
	public final static String WODEJIFEN_CHAKANMINGXI_STRING = "//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView";
	public final static String JIXIAOKAOHE_KAOHEZHIBIAO_ZHUNSHILV ="//android.widget.LinearLayout[@resource-id='com.baidu.lbs.waimai.baidurider:id/ll_check_standard']/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
	public final static String JIXIAOKAOHE_KAOHEZHIBIAO_PINGJUNSONGDA ="//android.widget.LinearLayout[@resource-id='com.baidu.lbs.waimai.baidurider:id/ll_check_standard']/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
	public final static String WODEJIFEN_DANGQIANDENGJI ="//android.view.View[@content-desc='当前等级']/parent::android.webkit.WebView/android.view.View[4]";
	public final static String ChangJianWenTi_LieBiao ="//android.widget.ListView/";
	public final static String ChangJianWenTi_XiangQing ="//android.webkit.WebView[@content-desc='常见问题解答']/android.view.View";
	public final static String JIXIAOKAOHE_WEIGUIJILU ="//android.widget.LinearLayout[@resource-id='com.baidu.lbs.waimai.baidurider:id/ll_check_data']/android.widget.RelativeLayout[5]/android.widget.RelativeLayout/android.widget.TextView[2]";
	public final static String JIXIAOKAOHE_YINGXIONGBANG_WO ="//android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
	public final static String JIXIAOKAOHE_YINGXIONGBANG_WOPaiMing ="//android.widget.LinearLayout[@resource-id='com.baidu.lbs.waimai.baidurider:id/ll_herolist_container']/android.widget.LinearLayout/android.widget.TextView[2]";
	public final static String JIXIAOKAOHE_YINGXIONGBANG_Name ="//android.widget.LinearLayout[@resource-id='com.baidu.lbs.waimai.baidurider:id/ll_herolist_container']/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView";
	public final static String LiShiDingDan_ErJiWenAn ="//android.widget.ScrollView/android.widget.LinearLayout/android.widget.GridView/android.widget.RelativeLayout[4]/android.widget.TextView[2]";
	public final static String History_NowDate ="//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView";

	
	//众包xpth
	public final static String ZhangBao_DingDan ="//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]";
	
}
