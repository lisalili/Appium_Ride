package rider;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import rider.lib.BaseTest;
import com.ngtest.ext.MyTestListenerAdapter;
import com.ngtest.ext.PowerEmailableReporter;
@Listeners({MyTestListenerAdapter.class,PowerEmailableReporter.class})
public class NewTest extends BaseTest {
	/*----------在岗/离岗切换---------------*/
	/**
	 * @Name test_1000_ZaiGangLiGangQieHuan
	 * @Catalogue 在岗
	 * @Subcatalog 离岗
	 * @Grade P0
	 * 
	 */
	@Test
	public void test_1000_PostState () {
		Goto_UserCenter0();

		if(CheckTextById("tv_work_status","离岗")){
			StatusSwitch("在岗");

			System.out.println("离岗——————》在岗");
		}
		else if(CheckTextById("tv_work_status","在岗")){
			StatusSwitch("离岗");
			System.out.println("在岗——————》离岗");
		}

		Success();
	}


	/*----------判断左边侧边栏的文本是否都全存在---------------*/
	@SuppressWarnings("unchecked")
	/**
	 * @Name test_1001_CenBianXinxi()
	 * @Catalogue 收入业绩、任务中心、我的余额、热力图、吐槽建议、设置
	 * @Subcatalog 
	 * @Grade P0
	 * 
	 */
	//--------------------TextChack-------------
	@Test
	public void test_1001_CenBianXinxi(){
		Goto_UserCenter0();	
		List<WebElement> left = driver.findElementsById("left_cons"); 
		String str[]={"收入业绩","任务中心","我的余额"};
		for(int i=0;i<str.length;i++){
			if(left.get(i).getText().equals(str[i])){
				System.out.println("存在----left"+left.get(i).getText());
			}else{
				System.out.println("不存在----left"+left.get(i).getText());

			}
		}
		List<WebElement> title = driver.findElementsById("tvTitle"); 
		String l[]={"热力图","吐槽建议","设置"};
		for(int i=0;i<3;i++){
			if(title.get(i).getText().equals(l[i])){
				System.out.println("存在----tab"+title.get(i).getText());
			}else{
				System.out.println("不存在----tab"+title.get(i).getText());
			}
		}
		Success();   
	}

	@Test
	public void test_1002_ChackText(){
		Goto_UserCenter0();

		if(CheckStringIsShow("收入业绩","任务中心","我的余额","热力图","吐槽建议","设置")){
			Success();  
		}else{
			failAndsrceen("测试数据不通过");
		}
	}
/*

	//用这个函数滚动搜索关键字
	@Test
	public void test_1003_CheckTextScroll(){
		Goto_UserCenter0();
		WaitForStringShowScroll("新手任务",true);

	}
	//

*/

	/*--------------飞侠主页内容查看-------------------*/
	/**
	 * @Name test_1100_CheckText
	 * @Catalogue 主页内容查看
	 * @Subcatalog 飞侠
	 * @Grade P0
	 * @Describe "健康证","保证金","我的保险","帮助中心","考试培训","装备商城"
	 * @FunctionPoint 显示正常返回
	 */
	@Test
	public void test_1100_CheckText(){
		Goto_UserCenter0();
		//rl_level_info

		WaitForViewShowAndClick("rl_level_info");
		WaitForStringShow("飞侠主页");	
		System.out.println("进入飞侠主页");
		//html5页面——>InweBview
		WaitForStringShowInWebView("健康证","保证金","我的保险","帮助中心","考试培训","装备商城");	
		ClickOnView("bg");   //点击返回
		Sleep(200);
		//iv_user_head
		WaitForViewShow("iv_user_head");
	}
	/*---------------------收入业绩--------------------*/
	/**
	 * @Name test_1100_CheckText
	 * @Catalogue 收入业绩
	 * @Subcatalog 飞侠
	 * @Grade P0
	 * @Describe 历史订单（全部，已取消，违规单）
	 * @FunctionPoint 显示正常返回
	 */
	@Test
	public void test_1200_CheckText(){
		Goto_UserCenter0();
		if(CheckStringIsShow("收入业绩")){
			ClickOnText("收入业绩");
			if(CheckStringIsShow("历史订单")){
				ClickOnText("历史订单");
				System.out.println("进入历史订单页面");
				//WaitForStringShowInWebView("text");
				List<WebElement> id = driver.findElementsById("text");
				for(int i=0;i<id.size();i++){
					String text = id.get(i).getText();
					ClickOnText(text);
				}

			}
			if(CheckStringIsShow("业绩概览")){
				ClickOnText("业绩概览");
				System.out.println("进入历史订单页面");
				Sleep(1000);
				ClickOnView("tv_back");
				if(CheckViewIsShow("iv_user_head")){
					Success();
				}else{
					failAndsrceen("任务中心——>个人页面返回失败");
				}
			}			
		}else{
			failAndsrceen("没有找到任务中心"); 
		}	
	}
	@Test
	public void test_1201_CheckText(){
		Goto_UserCenter0();
		if(CheckStringIsShow("收入业绩")){
			ClickOnText("收入业绩");
			if(CheckStringIsShow("历史订单")){
				ClickOnText("历史订单");
				System.out.println("进入历史订单页面");
				String xpath = "//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]";
				driver.findElementByXPath(xpath).click();
				for(int i = 0; i < 4; i++){
					Scroll(3.0/4, 1.0/4);
					Sleep(500);
				}

				System.out.println(xpath);
			}
		}
	}

	/*---------------------任务中心内容查看----------------*/
	/**
	 * @Name test_1200_CheckText
	 * @Catalogue 任务中心内容查看
	 * @Subcatalog 飞侠
	 * @Grade P0
	 * @Describe 
	 * @FunctionPoint
	 */
	@Test
	public void test_1200_ClickText(){
		Goto_UserCenter0();
		List<WebElement> ids = driver.findElementsById("left_cons"); 
		String id = ids.get(1).getText();
		System.out.println(id);
		System.out.println("已找到“任务中心”->"+id);
		ClickOnText(id); 
		if(CheckStringIsShow("任务中心")){
			System.out.println("进入任务中心页面");
			if(CheckStringIsShow("历史任务")){
				Sleep(1000);
				ClickOnText("历史任务");
				System.out.println("进入历史任务页面");
				Sleep(1000);
				ClickOnView("bg");
				if(CheckStringIsShow("任务中心")){
					Sleep(1000);
					System.out.println("返回页面正确");
					ClickOnView("bg");
					Sleep(1000);
					if(CheckViewIsShow("iv_user_head")){
						Success();
					}else{
						failAndsrceen("任务中心——>个人页面返回失败");
					}
				}else{
					failAndsrceen("历史任务——>任务中心"); 
				} 
			}else{
				failAndsrceen("历史任务不存在"); 
			}
		}else{
			failAndsrceen("任务中心不存在"); 
		}
	}


	/*---------------------我的余额----------------*/
	/**
	 * @Name test_1200_CheckText
	 * @Catalogue 我的余额
	 * @Subcatalog 我的余额
	 * @Grade P0
	 * @Describe 
	 * @FunctionPoint 显示正常返回
	 */
	@Test
	public void test_1300_ClickText(){
		Goto_UserCenter0();
		List<WebElement> ids = driver.findElementsById("left_cons"); 
		String text = ids.get(2).getText();
		System.out.println("已找到“我的余额”->"+text);
		ClickOnText(text); 
		if(CheckStringIsShow("查看保证金")){
			ClickOnText("查看保证金");
			String ts =  GetStringByid("mid");
			if(ts.equals("保证金")){
				System.out.println("进入保证金页面");
				ClickOnView("bg");
				if(CheckStringIsShow("查看保证金")){
					System.out.println("返回到——>我的余额页面");
				}else{
					failAndsrceen("返回余额失败");
				}
			}else{
				failAndsrceen("进入页面错误");
			}

		}else{
			failAndsrceen("查看保证金");
		}

		if(CheckStringIsShow("更多")){
			ClickOnText("更多");
			if(GetStringByid("mid").equals("更多设置")){
				System.out.println("更多设置");
				ClickOnView("bg");
				if(CheckStringIsShow("查看保证金")){
					System.out.println("返回到——>我的余额页面");
				}else{
					failAndsrceen("返回余额失败");
				}
			}else{
				failAndsrceen("进入页面错误");
			}

		}else{
			failAndsrceen("没有发现——>更多");
		}
		ClickOnView("tv_balance_title");
		if(CheckViewIsShow("iv_user_head")){
			Success();
		}else{
			failAndsrceen("页面返回失败");
		}	
	}


	
	
	
	
/*	
	@Test
	public void test_sunli_ClickUserList(){
		Goto_UserCenter0();
		List<WebElement> ids = driver.findElementsById("left_cons"); 
		for(int i=0;i<3;i++){
			String id = ids.get(i).getText(); 
			System.out.println("已找到View->"+id);
			ClickOnText(id);   //通过文字实现点击
			Sleep(5000);
			if(i == 0){
				if(CheckStringIsShow("界面存在的文字就行")){
					System.out.println("收入业绩点击成功，正在退出。。。");
					ClickOnView("left");   //点击返回
				}else{
					//failAndsrceen("收入业绩 ：" + "->点击失败");
					System.out.println("收入业绩 ：" + "->点击失败");
				}
			}else if(i == 1){
				if(CheckStringIsShow("历史任务")){   //判断进入的界面是否有该文字
					System.out.println("任务中心点击成功，正在退出。。。");
					ClickOnView("left");   //点击返回
				}else{
					//failAndsrceen("任务中心 ：" + "->点击失败");
					System.out.println("任务中心 ：" + "->点击失败");
				}
			}else if(i == 2){
				if(CheckStringIsShow("随便写的")){
					System.out.println("我的余额点击成功，正在退出。。。");
					ClickOnView("left");   //点击返回

				}else{
					//failAndsrceen("我的余额 ：" + "->点击失败");
					System.out.println("我的余额 ：" + "->点击失败");
				}
			}
			Sleep(5000);
		}
		Success();

	}

*/


	/*
	 * 多String
	 * */
	public static Boolean CheckStringIsShow(String... strings) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		boolean isvisible = true;
		String Output = "";
		try {
			for (String string : strings) {
				Output = string;
				WebElement webElement = driver.findElementByAndroidUIAutomator(strBefore + string + strBehind);

			}
			isvisible = true;
		} catch (Exception e) {
			isvisible = false;
			System.out.println(Output + "is a wrong string");
			failAndsrceen(Output + "is a wrong string");

		}
		if (isvisible) {
			return true;
		}
		return false;
	}


	/**
	 * 切换在岗状态并且判断当前在岗状态
	 */
	public static void ZaiGangZhuangTai_Status(String ZaiGangZhuangTai_Status){
		ClickOnView("tv_work_status");
		ClickOnText(ZaiGangZhuangTai_Status);
		WaitForLoadingBearDisappear();
		AssertThat_TextBy("tv_work_status", ZaiGangZhuangTai_Status);
	}



	/*
	 * 
	 */
	public static void StatusSwitch(String status){
		ClickOnView("tv_work_status");

	}


	/**
	 * 断言，判断id 文案正确是否显示正确
	 */
	public static void  AssertThat_TextBy(String id ,String expectStr){
		//WaitForViewShow(id);

		String StrInFact = driver.findElementById(id).getText();
		System.out.println(StrInFact);
		if(!expectStr.equals(StrInFact)){
			System.out.println("11");
			failAndsrceen("实际文案为："+expectStr+";切换文案为:"+StrInFact);
		}else{
			System.out.println("21");
			failAndsrceen("ID未找到");
		}
	}


}
