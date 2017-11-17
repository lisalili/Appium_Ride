package rider;

import org.testng.annotations.Test;

import rider.lib.BaseTest;
import rider.lib.StringCommon;

public class AutoPlaceOrder extends BaseTest{
	@Test
	public void test_1011_Order_Buy(){
		Sleep(2000);
//		WaitForStringShow("餐饮");
		ClickOnViewXpath("//android.widget.LinearLayout[contains(@resource-id,\"com.baidu.lbs.waimai:id/bottom_bar\")]/android.view.View[4]");
		WaitForStringShowAndClick("收藏店铺");
		WaitForStringShowAndClick_Scroll(StringCommon.TestShopName_Food);
		WaitForLoadingBearDisappear();
		DeleteShoppingCart();
		WaitForViewShowAndClick_Scroll("com.baidu.lbs.waimai:id/waimai_shopmenu_dish_plus");
		int Times = 20;
		int i= 0;
  		while(i<=Times){
  			i++;
			WaitForStringShowAndClick("选好了");
			WaitForStringShowAndClick_Scroll("货到付款");
			WaitForLoadingBearDisappear();
			WaitForStringShowAndClick_Scroll("备注");
			WaitForViewShowAndClick("com.baidu.lbs.waimai:id/remarks_inputer");
			driver.getKeyboard().sendKeys("自动下单-可拍小票"+i); 
			ClickOnView("com.baidu.lbs.waimai:id/right");
			WaitForStringShowAndClick("确认下单");
			WaitForStringShowAndClick("确定");
			WaitForLoadingBearDisappear();
			WaitForStringShowAndClick("再来一单");
			
			
		}
	}
}
