package rider;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import rider.lib.BaseTest;

public class UnfinishOrder extends BaseTest{
	//-------------------------------------批量处理小黑条入口&展示------------------------前6条case为已修改好的case---------------
			/**
			 * @Name test_0000_OrderMessage
			 * @Catalogue 批量处理
			 * @Subcatalog 批量处理小黑条展示
			 * @Grade P0
			 * @Describe 如果批量处理小黑条展示，点击查看是否进入批量处理页面，返回小黑条仍存在
			 * @FunctionPoint 从黑条进入批量处理页面，返回小黑条存在
			 */
			@Test
			public void test_0000_OrderMessage() {
				
				
				if(!CheckViewIsShow("iv_message")){
//					String dispatch_url = "http://gzhxy-waimai-dcloud55.gzhxy.iwm.name:8105//logistics/distributeorder?chinesename=%E9%87%91%E6%B5%B7&userid=49094&order_list=14992242050910";
//			        String dispatch_responseJson = BaseTest.riderGetHttpContent(dispatch_url);
					AutoPlaceOrder(1);
					driver.startActivity("com.baidu.lbs.waimai.baidurider", "com.baidu.lbs.waimai.baidurider.activity.SplashActivity");
				}
				WaitForViewShowAndClick("iv_message");
				if(!CheckStringIsShow("批量处理")){
					failAndsrceen("点击小黑圈后批量处理页面没有弹出");
				}
				ClickOnView("tv_close");
				if(!CheckViewIsShow("iv_message")){
					failAndsrceen("批量处理返回小黑圈消失");
				}
				Success();
			}
		//-------------------------------------批量处理详情页进入&返回---------------------------------------
			/**
			 * @Name test_0001_OrderMessageDetail
			 * @Catalogue 批量处理详情页
			 * @Subcatalog 进入详情页，返回批量处理页
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 批量处理页面卡片点击进入详情页，从详情页原路返回
			 */
			@Test
			public void test_0001_OrderMessageDetail() {
				if(!CheckViewIsShow("iv_message")){
//					String dispatch_url = "http://gzhxy-waimai-dcloud55.gzhxy.iwm.name:8105//logistics/distributeorder?chinesename=%E9%87%91%E6%B5%B7&userid=49094&order_list=14992242050910";
//			        String dispatch_responseJson = BaseTest.riderGetHttpContent(dispatch_url);
			        AutoPlaceOrder(1);
					driver.startActivity("com.baidu.lbs.waimai.baidurider", "com.baidu.lbs.waimai.baidurider.activity.SplashActivity");
				}
				WaitForViewShowAndClick("iv_message");
				WaitForViewShowAndClick("ll_order_info");
				if(CheckViewIsShow("tv_content1")){
					ClickOnView("ok");
				}
				if(!CheckViewIsShow("extern_info")){
					failAndsrceen("批量处理订单未正常进入详情页");
				}
				ClickOnView("bg");
				if(!CheckStringIsShow("批量处理")){
					failAndsrceen("批量处理订单详情页没有返回到批量处理页面");
				}
				Success();
			}
		//-------------------------------------批量处理详情页按钮隐藏---------------------------------------
			/**
			 * @Name test_0002_OrderMessageDetailHideBotton
			 * @Catalogue 批量处理详情页按钮隐藏
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 批量处理详情页按钮隐藏
			 */
			@Test
			public void test_0002_OrderMessageDetailHideBotton() {
				if(!CheckViewIsShow("iv_message")){
//					String dispatch_url = "http://gzhxy-waimai-dcloud55.gzhxy.iwm.name:8105//logistics/distributeorder?chinesename=%E9%87%91%E6%B5%B7&userid=49094&order_list=14992242050910";
//			        String dispatch_responseJson = BaseTest.riderGetHttpContent(dispatch_url);
					AutoPlaceOrder(1);
					driver.startActivity("com.baidu.lbs.waimai.baidurider", "com.baidu.lbs.waimai.baidurider.activity.SplashActivity");
				}
				WaitForViewShowAndClick("iv_message");
				
				WaitForViewShowAndClick("ll_order_info");
				if(CheckViewIsShow("tv_content1")){
					ClickOnView("ok");
				}
				if(CheckViewIsShow("tv_order_status")){
					failAndsrceen("批量处理详情页按钮未隐藏");
				}
				Success();
			}		
			//-------------------------------------取餐卡片详情商户名称对应---------------------------------------
			/**
			 * @Name test_0003_OrderCardDetail_Shopname
			 * @Catalogue 取餐卡片详情商户名称对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片详情页商户名称对应
			 */
			@Test
			public void test_0003_OrderCardDetail_Shopname() {
				if(CheckViewIsShow("tv_shop_name")){
					String shop_name_cardString = GetStringByid("tv_shop_name");
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowScroll("tv_shop_name");
					String shop_name_detailString = GetStringByid("tv_shop_name");
					if(!shop_name_detailString.startsWith(shop_name_cardString)){
						failAndsrceen("取餐卡片列表详情页商户名称不对应");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片详情用户地址对应---------------------------------------
			/**
			 * @Name test_0004_OrderCardDetail_UserAddress
			 * @Catalogue 取餐卡片详情用户地址对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片详情页用户地址对应
			 */
			@Test
			public void test_0004_OrderCardDetail_UserAddress() {
				if(CheckViewIsShow("tv_user_address")){
					String User_Address_cardString = GetStringByid("tv_user_address");
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowScroll("tv_ori_address");
					String User_Address_detailString = GetStringByid("tv_ori_address");
					if(!User_Address_detailString.startsWith(User_Address_cardString)){
						failAndsrceen("取餐卡片列表详情页用户位置不对应");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片详情平台订单号对应---------------------------------------
			/**
			 * @Name test_0005_OrderCardDetail_OrderName
			 * @Catalogue 取餐卡片详情平台订单号对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片详情页平台订单号对应
			 */
			@Test
			public void test_0005_OrderCardDetail_OrderName() {
				if(CheckViewIsShow("tv_order_name")){
					String Order_Name_cardString = GetStringByid("tv_order_name");
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowScroll("tv_order");
					String Order_Name_detailString = GetStringByid("tv_order");
//					System.err.println(Order_Name_detailString);
//					System.err.println(Order_Name_cardString);
					if(!Order_Name_detailString.equals(Order_Name_cardString)){
						failAndsrceen("取餐卡片列表详情页平台订单号不对应");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片详情平台订单号对应---------------------------------------
			/**
			 * @Name test_0006_OrderCardDetail_OrderType
			 * @Catalogue 取餐卡片详情订单类型对应
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片详情页订单类型对应
			 */
			@Test
			public void test_0006_OrderCardDetail_OrderType() {
				if(CheckViewIsShow("tv_order_type")){
					String Order_Name_cardString = GetStringByid("tv_order_type");
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowScroll("tv_order_type");
					String Order_Name_detailString = GetStringByid("tv_order_type");
//					System.err.println(Order_Name_detailString);
//					System.err.println(Order_Name_cardString);
					if(!Order_Name_detailString.equals(Order_Name_cardString)){
						failAndsrceen("取餐卡片列表详情页订单类型不对应");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片标签弹窗&关闭---------------------------------------
			/**
			 * @Name test_0007_OrderCard_content_jump
			 * @Catalogue 取餐卡片标签弹窗&关闭
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片标签正常弹窗&弹窗正常关闭
			 */
			@Test
			public void test_0007_OrderCard_content_jump() {
				if(CheckViewIsShow("content_jump")){
					ClickOnView("content_jump");
					AssertThat_DialogIsShowB();
					ClickOnView("iv_close");
					if(!CheckViewIsShow("content_jump")){
						failAndsrceen("标签弹窗不能正常关闭");
					}
				}
				Success();
			}	
		//-------------------------------------取餐详情页确认到店取消操作---------------------------------------
			/**
			 * @Name test_0008_OrderCard_arrive_shop_Cancle
			 * @Catalogue 取餐详情页确认到店取消操作
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页确认到店取消后没有到店
			 */
			@Test
			public void test_0008_OrderCard_arrive_shop_Cancle() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShow("tv_order_status");
					if(CheckTextById("tv_order_status", "确认到店")){
						ClickOnView("tv_order_status");
						WaitForViewShowAndClick("cancel");
						if(!CheckTextById("tv_order_status", "确认到店")){
							failAndsrceen("详情页确认到店取消按钮有问题");
						}
					}
					
				}
				Success();
			}	
		//-------------------------------------取餐详情页确认到店二次确认到店---------------------------------------
			/**
			 * @Name test_0009_OrderCard_arrive_shop
			 * @Catalogue 取餐详情页确认到店二次确认-到店
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页确认到店后状态改变
			 */
			@Test
			public void test_0009_OrderCard_arrive_shop() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShow("tv_order_status");
					if(CheckTextById("tv_order_status", "确认到店")){
						ClickOnView("tv_order_status");
						WaitForViewShowAndClick("ok");
						WaitForViewShow("tv_order_status");
						if(CheckTextById("tv_order_status", "确认到店")){
							failAndsrceen("详情页确认到店有问题");
						}
					}
					
				}
				Success();
			}	
		//-------------------------------------取餐卡片订单状态流入口&返回---------------------------------------
			/**
			 * @Name test_0010_OrderCard_StatusFeed
			 * @Catalogue 取餐卡片订单状态流入口&返回
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片订单状态流入口正常进入&原路返回
			 */
			@Test
			public void test_0010_OrderCard_StatusFeed() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					AssertThat_TextById("tv_order_id", "状态明细");
					ClickOnView("tv_order_id");
					WaitForStringShow("订单状态");
					ClickOnView("bg");
					WaitForViewShow("extern_info");
					if(!CheckViewIsShow("tv_order_id")){
						failAndsrceen("订单状态流页面返回错误");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片详情页备注发票栏显示---------------------------------------
			/**
			 * @Name test_0011_OrderDetail_remark_invoice
			 * @Catalogue 取餐卡片详情页备注发票栏显示
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片详情页备注发票栏均显示
			 */
			@Test
			public void test_0011_OrderDetail_remark_invoice() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForStringShow("备注：","发票：");
				}
				Success();
			}	
		//-------------------------------------取餐卡片付款变价验证---------------------------------------
			/**
			 * @Name test_0012_OrderDetail_PayEdit
			 * @Catalogue 取餐卡片付款变价验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片显示付款编辑按钮，弹窗各元素正常显示，变价后改变，变价后弹窗编辑框默认价格改变
			 */
//			@Test
//			public void test_0012_OrderDetail_PayEdit() {
//				if(CheckViewIsShow("rl_container")){
//					ClickOnView("rl_container");
//					if(CheckViewIsShow("tv_content1")){
//						ClickOnView("ok");
//					}
//					WaitForViewShowAndClick("iv_pay_edit");
//					WaitForStringShow("付款","四舍五入","包装费变价","商品变价","商品更换","折扣错误","确认修改变价","保存");
//					ClickOnView("et_change_price");
//					deleteEditText();
//					driver.getKeyboard().sendKeys("100");   //填写变后的价格
//					ClickOnText("四舍五入");
//					ClickOnView("ck_confirm");
//					ClickOnText("保存");
//					if(!CheckTextById("tv_real_pay", "￥100")){
//						failAndsrceen("取餐付款变价错误");
//					}
//					ClickOnView("iv_pay_edit");
//					if(!CheckTextById("et_change_price", "100")){
//						failAndsrceen("取餐付款变价后编辑框默认数字未变");
//					}
//				}
//				Success();
//			}	
		//-------------------------------------取餐卡片付款变价验证---------------------------------------
			/**
			 * @Name test_0013_OrderDetail_ReceiveEdit
			 * @Catalogue 取餐卡片收款变价验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片显示收款编辑按钮，弹窗各元素正常显示，不可变价
			 */
			@Test
			public void test_0013_OrderDetail_ReceiveEdit() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick("tv_receive_jump");
					WaitForStringShow("收款");
					if(CheckStringIsShow("保存")){
						failAndsrceen("取餐收款应该不可变价");
					}
				}
				Success();
			}	
		//-------------------------------------取餐卡片菜品详情验证---------------------------------------
			/**
			 * @Name test_0014_OrderDetail_itemsdetail
			 * @Catalogue 取餐卡片菜品详情验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片菜品详情元素展示
			 */
			@Test
			public void test_0014_OrderDetail_itemsdetail() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForStringShowScroll("商品名称");
					WaitForStringShow("数量","付商户","收顾客");
					WaitForStringShowScroll("总额");
				}
				Success();
			}	
		//-------------------------------------取餐详情页商户骑士导航验证---------------------------------------
			/**
			 * @Name test_0015_OrderDetail_ShopNavigation
			 * @Catalogue 取餐详情页商户骑士导航验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页商户骑士导航正常跳转&返回
			 */
			@Test
			public void test_0015_OrderDetail_ShopNavigation() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick_Scroll("shop_navigation");
					WaitForStringShow("百度导航");
					ClickOnView("ivBack");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShow("shop_navigation");
				}
				Success();
			}	
		//-------------------------------------取餐详情页用户骑士导航验证---------------------------------------
			/**
			 * @Name test_0015_OrderDetail_ShopNavigation
			 * @Catalogue 取餐详情页用户骑士导航验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页用户骑士导航正常跳转&返回
			 */
			@Test
			public void test_0016_OrderDetail_UserNavigation() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick_Scroll("user_navigation");
					WaitForStringShow("百度导航");
					ClickOnView("ivBack");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShow("user_navigation");
				}
				Success();
			}	
		//-------------------------------------取餐卡片骑士导航验证---------------------------------------
			/**
			 * @Name test_0017_OrderCard_Navigation
			 * @Catalogue 取餐卡片骑士导航验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐卡片骑士导航正常跳转&返回
			 */
			@Test
			public void test_0017_OrderCard_Navigation() {
				if(CheckViewIsShow("rl_container")){
					WaitForViewShowAndClick("navigation");
					WaitForStringShow("百度导航");
					ClickOnView("ivBack");
					WaitForViewShow("navigation");
				}
				Success();
			}	
		//-------------------------------------取餐详情页调度电话验证---------------------------------------
			/**
			 * @Name test_0018_OrderDetail_DispatchPhone
			 * @Catalogue 取餐详情页调度电话验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页调度电话正常弹窗关闭
			 */
			@Test
			public void test_0018_OrderDetail_DispatchPhone() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick("im_abnormal_remark");
					WaitForStringShowAndClick("调度电话");
					WaitForStringShow("呼叫 调度中心","取消","呼叫");
					ClickOnText("取消");
					WaitForViewShowAndClick("im_abnormal_remark");
				}
				Success();
			}
		//-------------------------------------取餐详情页订单备注入口验证---------------------------------------
			/**
			 * @Name test_0019_OrderDetail_OrderRemark
			 * @Catalogue 取餐详情页订单备注入口验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页订单备注入口&返回路径
			 */
			@Test
			public void test_0019_OrderDetail_OrderRemark() {
				if(CheckViewIsShow("rl_container")){
					ClickOnView("rl_container");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick("im_abnormal_remark");
					WaitForStringShowAndClick("标记异常");
					WaitForStringShow("异常列表");
					ClickOnView("bg");
					if(CheckViewIsShow("tv_content1")){
						ClickOnView("ok");
					}
					WaitForViewShowAndClick("im_abnormal_remark");
				}
				Success();
			}
		//-------------------------------------取餐详情页订单备注提交功能验证---------------------------------------
			/**
			 * @Name test_0020_OrderDetail_RemarkSubmit
			 * @Catalogue 取餐详情页订单备注提交功能验证
			 * @Subcatalog 
			 * @Grade P0
			 * @Describe 
			 * @FunctionPoint 取餐详情页订单备注提交后正常显示状态：已提交审核...
//			 */
//			@Test
//			public void test_0020_OrderDetail_RemarkSubmit() {
//				if(CheckViewIsShow("rl_container")){
//					ClickOnView("rl_container");
//					if(CheckViewIsShow("tv_content1")){
//						ClickOnView("ok");
//					}
//					WaitForViewShowAndClick("im_abnormal_remark");
//					WaitForStringShowAndClick("标记异常");
//					WaitForStringShow("订单备注（多选）");
//					WaitForStringShowAndClick("用户联系不上");
//					ClickOnView("tv_order_remark_submit");
//					if(CheckViewIsShow("tv_content1")){
//						ClickOnView("ok");
//					}
//					ClickOnView("im_abnormal_remark");
//					WaitForStringShowAndClick("订单备注");
//					WaitForStringShow("已提交审核...");
//				}
//				Success();
//			}
			
}
