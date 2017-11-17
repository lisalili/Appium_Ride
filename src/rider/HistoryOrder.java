package rider;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import rider.lib.BaseTest;
import rider.lib.XpathCommon;

public class HistoryOrder extends BaseTest{
	//-------------------------------------总览卡片（非自招聘众包兼职骑士）---------------------------------------
		/**
		 * @Name test_0000_ZongLanKaPian
		 * @Catalogue 总览卡片
		 * @Subcatalog 上交补款金额验证
		 * @Grade P0
		 * @Describe 根据总付，总收判断上交还是补款是否正确，根据总付，总收的金额判断上交还是补款的金额是否正确
		 * @FunctionPoint 上交、补款正确，金额正确
		 */
		@Test
		public void test_0000_ZongLanKaPian() {
			Goto_UserCenter1("历史订单");
			String Pay = get_Now_ShuZi("tv_total_pay");
			String Receive = get_Now_ShuZi("tv_total_receive");
			String Totol = GetStringByid("tv_total_money");
			
			BigDecimal Pay1 = new BigDecimal(Pay);
			BigDecimal Receive1= new BigDecimal(Receive);
			BigDecimal Totol1= new BigDecimal(Totol);
		    float ChaZhi = Receive1.subtract(Pay1).floatValue(); 
		    float Totol2 = Totol1.floatValue();
		    if ( Math.abs(ChaZhi-Totol2) >=1e-7 ){      //浮点数的比较，不要直接用ChaZhi!=0.01
		    	System.err.println("总览卡片数据不对");
		    	failAndsrceen("总览卡片功能");
		    }
			Success();
		}
		
		//-------------------------------------详情页调度入口---------------------------------------
		/**
		 * @Name test_0001_DiaoDu_XiaoXi_RuKou
		 * @Catalogue 详情页调度入口
		 * @Subcatalog 调度电话
		 * @Grade P0
		 * @Describe 是否有调度入口，是否有拨打电话的入口,关闭弹窗
		 * @FunctionPoint 有调度入口，有拨打电话的入口，正常关闭
		 */
		@Test
		public void test_0001_DiaoDu_RuKou() {
			Goto_UserCenter1("历史订单");
			if(CheckViewIsShow("ll_order_info")){
				ClickOnView("ll_order_info");
				WaitForViewShowAndClick("tv_right");
				AssertThat_TextById("popupview_phone", "调度电话");
				ClickOnView("popupview_phone");
				AssertThat_DialogIsShow();
				AssertThat_TextById("content", "呼叫 调度中心");
				AssertThat_TextById("cancel", "取消");
				AssertThat_TextById("ok", "呼叫");
				ClickOnView("cancel");
				WaitForViewShow("tv_right");
			}
			Success();
		}	
		
		//-------------------------------------详情页消息公告入口LiShiDingDan_ErJiWenAn---------------------------------------
		/**
		 * @Name test_0002_XiaoXi_RuKou
		 * @Catalogue 详情页公告入口
		 * @Subcatalog 公告入口
		 * @Grade P0
		 * @Describe 是否有公告入口，是否可以进入公告列表，返回路径
		 * @FunctionPoint 有公告入口，可以进入公告列表,返回到详情页
		 */
		@Test
		public void test_0002_XiaoXi_RuKou() {
			Goto_UserCenter1("历史订单");
			if(CheckViewIsShow("ll_order_info")){
				ClickOnView("ll_order_info");
				WaitForViewShowAndClick("tv_right");
				AssertThat_TextById("popupview_message", "消息公告");
				ClickOnView("popupview_message");
				WaitForStringShow("消息公告");
				ClickOnView("bg");
				WaitForViewShow("tv_right");
			}
			Success();
		}	
		
		//-------------------------------------订单数对应---------------------------------------
		/**
		 * @Name test_0003_DingDanShu_DuiYing
		 * @Catalogue 历史订单二级文案对应
		 * @Subcatalog 二级文案与历史订单页对应
		 * @Grade P0
		 * @Describe 二级文案与历史订单页对应
		 * @FunctionPoint 二级文案的单数与历史订单页全部tab的单数相同
		 */
		@Test
		public void test_0003_DingDanShu_DuiYing() {
			Goto_UserCenter0();
			String HistoryOrderNum = GetStringByXpath(XpathCommon.LiShiDingDan_ErJiWenAn);
			String Order_number = get_Nowstring_ShuZi(HistoryOrderNum);
			ClickOnView("tvSubTitle");
			WaitForLoadingBearDisappear();
			String HistoryAllOrderNum = GetStringByXpath(XpathCommon.History_NowDate);
			String All_OrderNum = get_Nowstring_ShuZi(HistoryAllOrderNum);
			if(!Order_number.equals(All_OrderNum)){
				System.err.println("历史订单二级文案与全部tab数据不对应");
		    	failAndsrceen("test_0003_DingDanShu_DuiYing历史订单二级文案与全部tab");
			}
			Success();
		}	
		
		//-------------------------------------title与日期校验---------------------------------------
		/**
		 * @Name test_0004_Title_Date_Check
		 * @Catalogue 历史订单title和当前日期校验
		 * @Subcatalog title文案，当前日期
		 * @Grade P0
		 * @Describe title文案，当前日期
		 * @FunctionPoint title文案为“历史订单”，当前日期为今天所在日期，切换为其他日期后再次进入历史订单页面会重新加载为今天日期
		 */
		@Test
		public void test_0004_Title_Date_Check() {
			Goto_UserCenter1("历史订单");
			CheckTextById("mid", "历史订单");
			CheckTextById("text", GetNowDate_md());
			if(!GetNowDate_d().equals("01")){
				ClickOnText("1");
				CheckTextById("text", GetNowDate_m()+"-01");
			}
			ClickOnView("bg");
			Goto_UserCenter1("历史订单");
			CheckTextById("text", GetNowDate_md());
			Success();
		}		
		
		//-------------------------------------30天前日期选择校验---------------------------------------
		/**
		 * @Name test_0005_DateBefore30_Check
		 * @Catalogue 历史订单30天日期选择校验
		 * @Subcatalog 
		 * @Grade P0
		 * @Describe 历史订单30天前日期选择
		 * @FunctionPoint 30天前的日期无法选择
		 */
		@Test
		public void test_0005_DateBefore30_Check() {
			Goto_UserCenter1("历史订单");
			ClickOnView("text");
			ClickOnView("calendar_title_left_tv");
			ClickOnText("1");
			if(CheckStringIsShow("历史订单")){
				System.err.println("30天之前的数据还可以查看！");
		    	failAndsrceen("test_0005_DateBefore30_Check历史订单可查看30天之前数据");
			}
			Success();
		}			
		
		//-------------------------------------日期切换校验---------------------------------------
		/**
		 * @Name test_0006_DateSwitch_Check
		 * @Catalogue 日期切换校验
		 * @Subcatalog 
		 * @Grade P0
		 * @Describe 日期切换校验
		 * @FunctionPoint 正常切换日期，切换后历史订单页的时间刷选为切换后的时间
		 */
		@Test
		public void test_0006_DateSwitch_Check() {
			Goto_UserCenter1("历史订单");
			
			if(!GetNowDate_d().equals("01")){
				ClickOnView("text");
				ClickOnText("1");
				WaitForStringShow("历史订单");
				CheckTextById("text", GetNowDate_m()+"-01");
			}else{
				ClickOnView("text");
				ClickOnView("calendar_title_left_tv");
				ClickOnText("2");
				WaitForStringShow("历史订单");
				int Month = Integer.parseInt(GetNowDate_m());
				int LastMonth = Month -1 ;
				String LastMonth1 = String.valueOf(LastMonth);
				CheckTextById("text", LastMonth1+"-01");
			}
			
			Success();
		}		
		//-------------------------------------取消单类型检查---------------------------------------
			/**
			 * @Name test_0007_CardType_Check
			 * @Catalogue 取消单类型检查
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 取消单类型检查
			 * @FunctionPoint 取消单tab下的订单全部为取消单
			 */
			@Test
			public void test_0007_CardType_Cancle_Check() {
				Goto_UserCenter1("历史订单");
				ClickOnViewXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[3]");
				for(int i = 0; i < 4; i++){
					Scroll(3.0/4, 1.0/4);
					Sleep(500);
				}
				String Cancle_Num_String;
				Cancle_Num_String = GetStringByXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView");
				String CancleNum = get_Nowstring_ShuZi(Cancle_Num_String);
				int cancle_num = Integer.parseInt(CancleNum);
				int i = 1;
				while(i<=cancle_num){
					Boolean flag = CheckTextByXpath_Scroll("//android.widget.ListView/android.widget.RelativeLayout["+i+"]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView","已取消");
					if(!flag){
						System.err.println("取消单tab下并不都是取消单！");
						failAndsrceen("取消单不符");
					}
				}
				
				Success();
			}			
			//-------------------------------------限时达类型检查---------------------------------------
			/**
			 * @Name test_0008_CardType_Limit_Check
			 * @Catalogue 限时达订单类型检查
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 限时达订单类型检查
			 * @FunctionPoint 限时达tab下的订单全部为限时达订单
			 */
			@Test
			public void test_0008_CardType_Limit_Check() {
				Goto_UserCenter1("历史订单");
				ClickOnViewXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[4]");
				for(int i = 0; i < 4; i++){
					Scroll(3.0/4, 1.0/4);
					Sleep(500);
				}
				String Limit_Num_String;
				Limit_Num_String = GetStringByXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[5]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView");
				String LimitNum = get_Nowstring_ShuZi(Limit_Num_String);
				int Limit_num = Integer.parseInt(LimitNum);
				int i = 1;
				while(i<=Limit_num){
					Boolean flag = CheckTextByXpath_Scroll("//android.widget.ListView/android.widget.RelativeLayout["+i+"]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView","限时达");
					if(!flag){
						System.err.println("限时达tab下并不都是限时达订单！");
						failAndsrceen("限时达订单不符");
					}
				}
				
				Success();
			}		
			
			//-------------------------------------24小时隐藏商户用户信息校验---------------------------------------
			/**
			 * @Name test_0009_HideMsg_Check
			 * @Catalogue 历史订单24小时后隐藏商户用户信息
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 每个月3号之后都看前两天的数据，商户用户信息时隐藏的
			 */
			@Test
			public void test_0009_HideMsg_Check() {
				Goto_UserCenter1("历史订单");
				CheckTextById("text", GetNowDate_md());
				String dateString = GetNowDate_d();
				int date = Integer.parseInt(dateString);
				String date_before_two = String.valueOf(date - 2);
				if(!(GetNowDate_d().equals("01")||GetNowDate_d().equals("02"))){
					ClickOnText(date_before_two);
					if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
						ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
						WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
						for(int i = 0; i < 2; i++){
							Scroll(3.0/4, 1.0/4);
							Sleep(500);
						}
						if(CheckStringIsShow("商户","导航","顾客","短信","导航")){
							failAndsrceen("24小时后商户用户信息未隐藏");
						}
					}
				}
				Success();
			}		
			//-------------------------------------取消单隐藏商户用户信息校验---------------------------------------
			/**
			 * @Name test_0010_Cancle_HideMsg_Check
			 * @Catalogue 历史订单取消单隐藏商户用户信息
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取消单的商户用户信息时隐藏的
			 */
			@Test
			public void test_0010_Cancle_HideMsg_Check() {
				Goto_UserCenter1("历史订单");
				ClickOnViewXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[3]");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					for(int i = 0; i < 2; i++){
						Scroll(3.0/4, 1.0/4);
						Sleep(500);
					}
					if(CheckStringIsShow("商户","导航","顾客","短信","导航")){
						failAndsrceen("取消单商户用户信息未隐藏");
					}
				}
			
				Success();
			}		
			//-------------------------------------取消单隐藏吐槽商户入口校验---------------------------------------
			/**
			 * @Name test_0010_Cancle_HideMsg_Check
			 * @Catalogue 历史订单取消单隐藏吐槽商户入口
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取消单吐槽商户入口是隐藏的
			 */
			@Test
			public void test_0011_Cancle_HideCorrect_Check() {
				Goto_UserCenter1("历史订单");
				ClickOnViewXpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[3]");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					if(CheckViewIsShow("btn_evaluate_merchant")){
						failAndsrceen("取消单吐槽商户按钮未隐藏");
					}
				}
			
				Success();
			}	
			//-------------------------------------付款简卡详情对应校验---------------------------------------
			/**
			 * @Name test_0012_Pay_Check
			 * @Catalogue 历史订单付款简卡详情对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 付款简卡详情对应
			 */
			@Test
			public void test_0012_Pay_Check() {
				Goto_UserCenter1("历史订单");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					String payCardString = GetStringByid("tv_real_pay");
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					String PayDetail = GetStringByid("tv_real_pay");
					if(!payCardString.equals(PayDetail.substring(1))){
						failAndsrceen("付款简卡详情不对应");
					}
				}
			
				Success();
			}	
			//-------------------------------------收款简卡详情对应校验---------------------------------------
			/**
			 * @Name test_0013_Receive_Check
			 * @Catalogue 历史订单收款简卡详情对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 收款简卡详情对应
			 */
			@Test
			public void test_0013_Receive_Check() {
				Goto_UserCenter1("历史订单");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					String payCardString = GetStringByid("tv_real_receive");
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					String PayDetail = GetStringByid("tv_real_receive");
					if(!payCardString.equals(PayDetail.substring(1))){
						failAndsrceen("收款简卡详情不对应");
					}
				}
			
				Success();
			}	
			//-------------------------------------取消单隐藏吐槽商户入口校验---------------------------------------
			/**
			 * @Name test_0010_Cancle_HideMsg_Check
			 * @Catalogue 历史订单取消单隐藏吐槽商户入口
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取消单吐槽商户入口是隐藏的
			 */
			@Test
			public void test_0014_Pay_Receive_Edit_Check() {
				Goto_UserCenter1("历史订单");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					if(!(CheckViewIsShow("iv_pay_edit")&&CheckViewIsShow("iv_receive_edit"))){
						failAndsrceen("收付款编辑框未显示");
					}
				}
			
				Success();
			}	
			//-------------------------------------商户名称简卡详情对应校验---------------------------------------
			/**
			 * @Name test_0015_shop_name_Check
			 * @Catalogue 历史订单商户名称简卡详情对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 商户名称简卡详情对应
			 */
			@Test
			public void test_0015_shop_name_Check() {
				Goto_UserCenter1("历史订单");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					String ShopNameCardString = GetStringByid("tv_shop_name");
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					for(int i = 0; i < 2; i++){
						Scroll(3.0/4, 1.0/4);
						Sleep(500);
					}
					String ShopNameDetail = GetStringByid("tv_shop_name");
					if(!ShopNameCardString.equals(ShopNameDetail)){
						failAndsrceen("商户简卡详情不对应");
					}
				}
			
				Success();
			}	
			//-------------------------------------用户地址简卡详情对应校验---------------------------------------
			/**
			 * @Name test_0016_user_address_Check
			 * @Catalogue 历史订单用户地址简卡详情对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 用户地址简卡详情对应
			 */
			@Test
			public void test_0016_user_address_Check() {
				Goto_UserCenter1("历史订单");
				if(CheckViewIsShow("com.baidu.lbs.waimai.baidurider:id/ll_order_info")){
					String ShopNameCardString = GetStringByid("tv_user_address");
					ClickOnView("com.baidu.lbs.waimai.baidurider:id/ll_order_info");
					WaitForViewShow("com.baidu.lbs.waimai.baidurider:id/im_abnormal_remark");
					for(int i = 0; i < 2; i++){
						Scroll(3.0/4, 1.0/4);
						Sleep(500);
					}
					String ShopNameDetail = GetStringByid("tv_user_address");
					if(!ShopNameCardString.equals(ShopNameDetail)){
						failAndsrceen("用户地址简卡详情不对应");
					}
				}
			
				Success();
			}	
}
