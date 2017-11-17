package rider;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import rider.lib.BaseTest;
import rider.lib.XpathCommon;


public class HistoryZhongBao extends BaseTest {
	

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
		String HistoryOrderNum = GetStringByXpath(XpathCommon.ZhangBao_DingDan);
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

}
