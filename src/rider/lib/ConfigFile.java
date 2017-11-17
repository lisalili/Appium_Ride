package rider.lib;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;




import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

import com.ngtest.ext.MyRetryAnalyzer;   
public class ConfigFile {
	public static AndroidDriver driver;
	private static Logger logger = Logger.getLogger(ConfigFile.class);
//	@BeforeTest
//	public void set(){
//		try {
//			System.setOut(new PrintStream(new File(CaseConfig.ConsoleLogSavePath)));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace(); 
//		}
//	}
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) {
	     for (ITestNGMethod method : context.getAllTestMethods()) {
	         method.setRetryAnalyzer(new MyRetryAnalyzer());
	     }                                //失败重跑    
	}
	@BeforeMethod
	public void setUp(Method method) throws MalformedURLException {
		System.out.println("Begin >>>>>>>>>>>>>>>>>>>>>>> Test case: "
				+ method.getName());
		BaseTest.currentCasename = method.getName();
		DesiredCapabilities caps = new DesiredCapabilities();
          
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");//测试平台
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "Build.MODEL");//手机版本6.0
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Build.VERSION.RELEASE");//手机型号dcclc285
		
		caps.setCapability("noReset", "True");//混合应用并且想直接进入webview内容中
		caps.setCapability("unicodeKeyboard", "True");//可以输入中文和特殊字符
		caps.setCapability("resetKeyboard", "True");//键盘设置为原来的状态

		caps.setCapability(MobileCapabilityType.APP_PACKAGE,"com.baidu.waimai.crowdsourcing");//根据文件报名设置众包：crowdsourcing                                                                                       //小度骑士，baidurider
		caps.setCapability(MobileCapabilityType.APP_ACTIVITY,"com.baidu.waimai.crowdsourcing.activity.SplashActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				caps);
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown(Method method) {
//		driver.closeApp();// CloseApp() function is used to close the mobile
		System.out.println("Finish <<<<<<<<<<<<<<<<<<<<<<<<< Test case: "
				+ method.getName());
		System.out.println();
		driver.quit();
	}
    
}
