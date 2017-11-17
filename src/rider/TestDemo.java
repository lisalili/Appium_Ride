package rider;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import rider.lib.pswConfig;

import rider.lib.BaseTest;
import rider.lib.pswConfig;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class TestDemo extends BaseTest{
	
		@Test
		public void test_example(){
			WaitForViewShow("iv_slip_toggle");
			//WaitForStringShow("未完成");
			ClickOnView("iv_slip_toggle");  //点击头像
			WaitForViewShow("iv_user_head");
			ClickOnView("tv_title");        //点击我的余额
			WaitForViewShow("tv_set");
			ClickOnView("tv_set");         //点击设置
			//Sleep(2000);
			
			WaitForViewShow("tv_bind");
			ClickOnView("tv_bind");       //点击绑定银行卡    et_password
			Sleep(2000);
			
			//AssertThat_DialogIsShow();   //判断对话框是否弹出  ll_loading
			/*if(CheckDialogIsShow()){
				System.out.println("已经弹出对话框");
				txpsw(pswConfig.PASSWORD);
			}*/
			//Sleep(2000);
			
			
			}
	
		
}

