package rider.lib;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import rider.lib.pswConfig;

import static org.hamcrest.MatcherAssert.assertThat;
//import sun.security.provider.JavaKeyStore.CaseExactJKS;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL; 
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public  class BaseTest extends ConfigFile {

	public static  String currentCasename = null;

	/**
	 * 等待时间，以毫秒为单位
	 */
	public static  void Sleep(int millseconds) {

		try {
			Thread.sleep(millseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static WebElement GetElementByText(String text) {
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		WebElement temp = null;
		try{
			temp = driver.findElementByAndroidUIAutomator(strBefore + text + strBehind);
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}
	
	public static ArrayList<WebElement> GetElementsByText(String text) {
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		ArrayList<WebElement> temp = null;
		try{
			temp = (ArrayList<WebElement>) driver.findElementsByAndroidUIAutomator(strBefore + text + strBehind);
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}

	public static ArrayList<WebElement> GetElementsById(String id) {
		ArrayList<WebElement> temp = null;
		try{
			temp = (ArrayList<WebElement>) driver.findElementsById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 为了拼接此格式，供UIAutomator识别文案，{"new UiSelector().text(\"餐饮\")"}
	 */
	public static void ClickOnText(String...text) {

		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		String printString = "";
		try{
			for(String str:text){	
				printString = str;
				driver.findElementByAndroidUIAutomator(strBefore + str + strBehind).click();
				System.out.println("点击->"+str);
				Sleep(500);
			}
		}catch(Exception e){
			failAndsrceen(printString + "->点击失败");
		}
	}
	/**
	 * 为了拼接此格式，供UIAutomator识别文案，{"new UiSelector().text(\"餐饮\")"}
	 */
	public static void ClickOnTextInWebView(String...text) {

		String strBefore = "new UiSelector().description(\"";
		String strBehind = "\")";
		String printString = "";
		for(String str:text){
			try{	
				printString = str;
				driver.findElementByAndroidUIAutomator(strBefore + str + strBehind).click();
				System.out.println("点击->"+str);
				Sleep(500);
			}catch(Exception e){
				failAndsrceen(printString+"->点击失败");
			}
		}
	}

	/**
	 * 点击View，通过ID
	 */
	public static void ClickOnView(String id) {
		try{
			driver.findElementById(id).click();
			Sleep(500);
			System.out.println("点击->"+id);
		}catch(Exception e){
			failAndsrceen("点击失败->"+id);
		}
	}

	/**
	 * 点击View，通过ID
	 */
	public static void ClickOnView(String id,int times) {
		for(int i = 0; i < times; i++){
			ClickOnView(id);
		}
	}
	/**
	 * 点击View，通过Xpath
	 */
	public static void ClickOnViewXpath(String xpath) {
		try{
			driver.findElementByXPath(xpath).click();
			Sleep(500);
			System.out.println("点击->"+xpath);
		}catch(Exception e){
			failAndsrceen("点击失败->"+xpath);
		}
	}
	/**
	 * 点击View，通过Xpath
	 */
	public static void ClickOnViewXpath(String xpath,int times) {
		for(int i = 0; i < times; i++){
			ClickOnViewXpath(xpath);
		}
	}

	/**
	 * 当前页面中查找文案 
	 */
	public static void WaitForStringShow(String... strings) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";

		boolean isvisible = false;
		String notShowString = "";
		for (String string : strings) {
			long starttimestamp = System.currentTimeMillis();
			while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
				isvisible = true;
				try {
					notShowString = string;
					WebElement webElement = driver.findElementByAndroidUIAutomator(strBefore + string + strBehind);
					System.out.println("已找到->"+string);
				}catch (Exception e) {
					// TODO: handle exception
					isvisible = false;
				}
				if (isvisible) {
					break;
				}	
			}
			if(!isvisible &&(System.currentTimeMillis() > starttimestamp + 30 * 1000)){
				failAndsrceen("所有文案不是全部展现的 -> " + notShowString);
			}
		}
	}

	/**
	 * 当前页面中查找文案 并点击
	 */
	public static void WaitForStringShowAndClick( String... strings) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";

		boolean isvisible = false;
		String notShowString = "";
		for (String string : strings) {
			long starttimestamp = System.currentTimeMillis();
			while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
				isvisible = true;
				try {
					notShowString = string;
					driver.findElementByAndroidUIAutomator(strBefore + string + strBehind).click();
					System.out.println("已找到->"+string);
				}catch (Exception e) {
					// TODO: handle exception
					isvisible = false;
				}
				if (isvisible) {
					break;
				}	
			}
			if(!isvisible &&(System.currentTimeMillis() > starttimestamp + 30 * 1000)){
				failAndsrceen("所有文案不是全部展现的 -> " + notShowString);
			}
		}
	}

	/**
	 * 循环在页面中查找文案 并点击
	 */
	public static void WaitForStringShowAndClick_Scroll( String strings,Boolean DownOrUp) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		boolean isvisible = false;
		long starttimestamp = System.currentTimeMillis();
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				driver.findElementByAndroidUIAutomator(
						strBefore + strings + strBehind).click();
				System.out.println("已找到->" + strings);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
				if(DownOrUp){
					Scroll(3.0/4, 1.0/4);
				}else{
					Scroll(1.0/4, 3.0/4);
				}
			}
			if (isvisible) {
				break;
			}
		}
		if (!isvisible
				&& (System.currentTimeMillis() > starttimestamp + 30 * 1000)) {
			failAndsrceen("所有文案不是全部展现的 -> " + strings);
		}
	}

	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForStringShowAndClick_Scroll(String str){
		WaitForStringShowAndClick_Scroll(str,true);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForStringShowAndClick_Scroll_UP(String str){
		WaitForStringShowAndClick_Scroll(str,false);
	}



	/**
	 * 判断文案是否显示，显示则返回true
	 */
	public static Boolean CheckStringIsShow(String... strings) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = true;
		String notShowString = "";
		try {
			for (String string : strings) {
				WebElement webElement = driver.findElementByAndroidUIAutomator(strBefore + string + strBehind);
			}
			isvisible = true;
		} catch (Exception e) {
			// TODO: handle exception
			isvisible = false;
		}
		if (isvisible) {
			return true;
		}
		return false;
	}

	/**
	 * 判断View是否显示，显示则返回true
	 */
	public static Boolean CheckViewIsShow(String id) {
		try{	
			driver.findElementById(id);
			System.out.println("已找到View->"+id);
			return true;
		}catch(Exception e){
			return false;
		}
	}


	/**
	 * 删除输入框中的文案
	 */
	public static void deleteEditText(){
		Sleep(500);
		driver.pressKeyCode(29,28672);   //ctrl+a
		driver.longPressKeyCode(67);     //删除键
		Sleep(500);
	}


	public static WebElement findElementByName(String name) {
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		long starttime = System.currentTimeMillis();
		WebElement element = null;
		while (System.currentTimeMillis() < starttime + 20 * 1000) {
			try {
				element = driver.findElementByAndroidUIAutomator(strBefore + name +strBehind);
				System.out.println("已找到 ->" + name);
				return element;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		failAndsrceen("根据Name<" + name + ">没有找到元素");
		return null;
	}
	public static void failAndsrceen(String msg) {
		System.out.println(msg);
		System.out.println(".........."+"运行失败点.........");

		//		msg = msg.replaceAll("[^a-zA-Z0-9]", "");
		if(msg.contains("//")){
			msg ="xpath";
		}
		msg = msg.replaceAll("\\s+", "");
		msg = msg.replaceAll("\\<\\-\\>", "");
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p= Pattern.compile(regEx);
		Matcher m=p.matcher(msg);
		msg = m.replaceAll("").trim();

		TakeScreenPicture("FailedPIC-"+msg);

		Sleep(500);
		assertThat(msg, false);
	}

	public static void TakeScreenPicture(String name){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date());
		File mFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(mFile, new File(pswConfig.caseScreenSavePath+BaseTest.currentCasename+File.separator
					+ name +"-"+ mDateTime+".png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("takescreenPicture 失败");
		}
	}


	/**
	 * 物理键返回
	 */
	public static void GoBack_Physical(){
		driver.pressKeyCode(4);
		System.out.println("物理键返回");
	}
	/**
	 * 物理键返回
	 */
	public static void GoBack_Physical(int n){
		for(int i = 0; i < n; i++){
			driver.pressKeyCode(4);
			System.out.println("物理键返回");
			Sleep(500);
		}
	}
	/**
	 * id返回
	 */
	public static void GoBack(String id){
		WaitForViewShow(id);
		try{
			driver.findElementById(id).click();
			System.out.println("点击返回->"+id);
		}catch(Exception e){
			failAndsrceen("返回键点击失败->"+id);
		}
	}
	/**
	 * 返回次数
	 */
	public static void GoBack(int times){
		Sleep(500);
		for(int i = 0; i < times; i++){
			GoBack();
			Sleep(200);
		}
	}
	/**
	 * 获取当前case名称 
	 */
	public static String GetCaseName() {
		if (currentCasename == null) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				if (stackTraceElement.getMethodName().contains("test_")) {
					currentCasename = stackTraceElement.getMethodName();
					return currentCasename;
				}
			}
			return "allResultPic";
		}else{
			return currentCasename;
		}
	}

	/**
	 * 通过id，获取文案
	 */
	public static String GetStringByid(String id) {
		return driver.findElementById(id).getText();
	}
	/**
	 * 通过xpath，获取文案
	 */
	public static String GetStringByXpath(String xpath) {
		System.out.println(xpath);
		return driver.findElementByXPath(xpath).getText();
	}

	/**
	 *  Y轴上下拖动
	 */
	public static void Scroll(Double startY, Double endY) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		driver.swipe(width / 2, (int) (height * startY),
				width / 2, (int) (height * endY), 800);
	}

	/**
	 *  下拉一页
	 */
	public static void ScrollPage_Down(int n) {
		for (int i = 0; i < n; i++) {
			Scroll((3.0 / 4), (1.0 / 4));
			Scroll((3.0 / 4), (1.0 / 4));
		}
	}

	/**
	 *  下拉一页
	 */
	public static void ScrollPage_Up(int n) {
		for (int i = 0; i < n; i++) {
			Scroll((1.0 / 4), (3.0 / 4));
			Scroll((1.0 / 4), (3.0 / 4));
		}
	}
	/**
	 *  Y轴上下拖动
	 */
	public static void Scroll(Double startY, Double endY, int time) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		driver.swipe(width / 2, (int) (height * startY),
				width / 2, (int) (height * endY), time);
	}
	/**
	 * 纵向滑动View
	 */
	public static void ScrollView_Y (String id, double startY, double EndY,int time) {
		WebElement view = driver.findElementById(id);
		Point point = view.getLocation();
		int height = view.getSize().getHeight();
		int width = view.getSize().getWidth();
		driver.swipe(point.x + width / 2, point.y+(int) (height * startY), point.x + width / 2, point.y + (int) (height * EndY), time);
	}
	//baidude
	public static void swipeToUp_half(int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
		// wait for page loading
	}
	/**
	 * 纵向滑动View
	 */
	public static void ScrollViewAndClick_Y (String strNeedClick,String id, double startY, double EndY,int time) {
		WebElement view = driver.findElementById(id);
		Point point = view.getLocation();
		int height = view.getSize().getHeight();
		int width = view.getSize().getWidth();
		long starttime = System.currentTimeMillis();
		while(  System.currentTimeMillis() <starttime + 30*1000){
			if(CheckStringIsShow(strNeedClick)){
				ClickOnText(strNeedClick);
				return;
			}
			driver.swipe(point.x + width / 2, point.y+(int) (height * startY), point.x + width / 2, point.y + (int) (height * EndY), time);
			Sleep(500);
		}
	}

	/**
	 * 横向滑动View
	 */
	public static void ScrollView_X (String id, double startX, double EndX,int time) {
		WebElement view = driver.findElementById(id);
		Point point = view.getLocation();
		int height = view.getSize().getHeight();
		int width = view.getSize().getWidth();
		driver.swipe(point.x+(int) (width * startX), point.y + height / 2, point.x+(int) (width * EndX),point.y + height / 2, time);
	}
	/**
	 * 将view-needMove_id滑至指定View-TargetTopid顶部
	 * @param id
	 * @param startY
	 * @param EndY
	 * @param times
	 */
	public static void ScrollToViewTop(String needMove_id,String TargetTopid,int time) {
		//view坐标
		WebElement view_needMove_id = driver.findElementById(needMove_id);
		Point point_needMove_id = view_needMove_id.getLocation();
		int height_needMove_id = view_needMove_id.getSize().getHeight();
		int width_needMove_id = view_needMove_id.getSize().getWidth();
		//content控件顶部
		WebElement view_TargetTopid = driver.findElementById(TargetTopid);
		Point point_TargetTopid = view_TargetTopid.getLocation();
		int height_TargetTopid = view_TargetTopid.getSize().getHeight();
		int width_TargetTopid = view_TargetTopid.getSize().getWidth();
		//content控件顶部

		driver.swipe(point_needMove_id.x+width_needMove_id / 2, point_needMove_id.y+(int) (height_needMove_id /2), point_needMove_id.x+width_needMove_id / 2, point_TargetTopid.y -(int) (height_needMove_id /2) , time);

	}
	/**
	 * 将VIew滑至屏幕中央
	 * @param id
	 * @param startY
	 * @param EndY
	 * @param times
	 */

	public static void ScrollViewToMiddle(String id, int time) {
		//屏幕尺寸
		int width_screen = driver.manage().window().getSize().width;
		int height_screen = driver.manage().window().getSize().height;
		//view坐标
		WebElement view = driver.findElementById(id);
		Point point = view.getLocation();
		int height = view.getSize().getHeight();
		int width = view.getSize().getWidth();

		driver.swipe(width_screen / 2, point.y+(int) (height /2), width_screen / 2, point.y + (int) (height /2), time);
	}
	/**
	 * 将VIew滑至屏幕中央
	 * @param id
	 * @param startY
	 * @param EndY
	 * @param times
	 */

	public static void ScrollTextToMiddle(String text, int time) {
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		//屏幕尺寸
		int width_screen = driver.manage().window().getSize().width;
		int height_screen = driver.manage().window().getSize().height;
		//view坐标
		WebElement view = driver.findElementByAndroidUIAutomator(strBefore + text + strBehind);
		Point point = view.getLocation();
		int height = view.getSize().getHeight();
		int width = view.getSize().getWidth();

		driver.swipe(width_screen / 2, point.y+(int) (height /2), width_screen / 2, (int) (height_screen /2), time);
	}


	/**
	 * 向下拖拽刷新
	 * PS:仅限出现骑士骑车动画的页面
	 */
	public static void DragToFresh() {
		Sleep(500);
		Scroll(2.0 / 5, 4.0 / 5,4000);
		WaitForViewHide("pull_rider");
	}

	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShow(String id){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
			isvisible = true;
			try {
				WebElement webElement = driver.findElementById(id);
				System.out.println("已找到->"+id);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("30秒后仍未显示-> " + id);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShowScroll(String id){
		WaitForViewShowScroll(id,true);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShowScroll_UP(String id){
		WaitForViewShowScroll(id,false);
	}
	/**
	 * 通过id判断View是否显示，Down为true
	 */
	public static void WaitForViewShowScroll(String id,Boolean DownOrUP){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				WebElement webElement = driver.findElementById(id);
				System.out.println("已找到->"+id);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
				if(DownOrUP){
					Scroll(3.0/4, 1.0/4);
				}else{
					Scroll(1.0/4, 3.0/4);
				}
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("30秒后仍未显示-> " + id);
	}


	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShowAndClick_Scroll(String id){
		WaitForViewShowAndClick_Scroll(id,true);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForXpathShowAndClick_Scroll(String xpath){
		WaitForViewShowAndClick_Scroll(xpath,true);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShowAndClick_Scroll_UP(String id){
		WaitForViewShowAndClick_Scroll(id,false);
	}
	/**
	 * 通过id判断View是否显示,Down为true
	 */
	public static void WaitForViewShowAndClick_Scroll(String id,Boolean DownOrUp){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				driver.findElementById(id).click();
				System.out.println("已找到->"+id);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
				if(DownOrUp){
					Scroll(3.0/4, 1.0/4);
				}else{
					Scroll(1.0/4, 3.0/4);
				}
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("30秒后仍未显示-> " + id);
	}
	/**
	 * 通过id判断View是否显示,Down为true
	 */
	public static void WaitForXpathShowAndClick_Scroll(String xpath,Boolean DownOrUp){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				driver.findElementByXPath(xpath).click();
				System.out.println("已找到->"+xpath);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
				if(DownOrUp){
					Scroll(3.0/4, 1.0/4);
				}else{
					Scroll(1.0/4, 3.0/4);
				}
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("30秒后仍未显示-> " + xpath);
	}
	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewShowAndClick(String id){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
			isvisible = true;
			try {
				driver.findElementById(id).click();
				System.out.println("已找到并点击->"+id);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("30秒后仍未显示->" + id);
	}
	/**
	 * 滚动寻找单一文案
	 */
	public static void WaitForStringShowScroll( String string) {
		WaitForStringShowScroll( string,true);
	}
	/**
	 * 滚动寻找单一文案
	 */
	public static void WaitForStringShowScroll_UP( String string) {
		WaitForStringShowScroll( string,false);
	}
	/**
	 * 滚动寻找单一文案,true为向下滑动
	 */
	public static void WaitForStringShowScroll( String string,Boolean DownOrUP) {
		Sleep(200);
		String strBefore = "new UiSelector().text(\"";
		String strBehind = "\")";
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				WebElement webElement = driver.findElementByAndroidUIAutomator(strBefore + string + strBehind);
				System.out.println("已找到->"+string);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
				if(DownOrUP){
					Scroll(3.0/4, 1.0/4);
				}else{
					Scroll(1.0/4, 3.0/4);
				}
			}
			if (isvisible) {
				return;
			}
		}
		failAndsrceen("所有文案不是全部展现的 -> " + string);
	}

	/**
	 * 当前页面中查找文案 html5
	 */
	public static void WaitForStringShowInWebView( String... strings) {
		Sleep(200);
		String strBefore = "new UiSelector().description(\"";
		String strBehind = "\")";
		boolean isvisible = false;
		String notShowString = "";
		for (String string : strings) {
			long starttimestamp = System.currentTimeMillis();
			while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
				isvisible = true;
				try {
					notShowString = string;
					WebElement webElement = driver.findElementByAndroidUIAutomator(strBefore + string + strBehind);
					System.out.println("已找到->"+string);
				}catch (Exception e) {
					// TODO: handle exception
					isvisible = false;
				}
				if (isvisible) {
					break;
				}	
			}
			if(!isvisible &&(System.currentTimeMillis() > starttimestamp + 30 * 1000)){
				failAndsrceen("所有文案不是全部展现的 -> " + notShowString);
			}
		}
	}

	/**
	 * 通过id判断View是否显示
	 */
	public static void WaitForViewHide(String id){
		Sleep(200);
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
			isvisible = true;
			try {
				WebElement webElement = driver.findElementById(id);
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
			}
			if (!isvisible) {
				System.out.println("id已消失->"+id);
				return;
			}
		}
		failAndsrceen("30秒后仍显示-> " + id);
	}



	/**
	 * 断言，判断id 文案正确是否显示正确
	 */
	public static void  AssertThat_TextById(String id ,String expectStr){
		WaitForViewShow(id);
		try{
			String StrInFact = driver.findElementById(id).getText();
			if(!expectStr.equals(StrInFact)){
				failAndsrceen("预期文案为："+expectStr+";实际文案为:"+StrInFact);
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("ID未找到");
		}
	}
	/**
	 * 判断id 文案正确是否显示正确
	 */
	public static Boolean  CheckTextById(String id ,String expectStr){
		WaitForViewShow(id);
		try{
			String StrInFact = driver.findElementById(id).getText();
			if(!expectStr.equals(StrInFact)){
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("ID未找到");
		}
		return true;
	}

	/**
	 * 断言，判断id 文案正确是否显示正确
	 */
	public static void  AssertThat_TextByXpath(String xpath ,String expectStr){
		try{
			String StrInFact = driver.findElementByXPath(xpath).getText();
			if(!expectStr.equals(StrInFact)){
				failAndsrceen("预期文案为："+expectStr+";实际文案为:"+StrInFact);
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("ID未找到");
		}
	}

	/**
	 * 断言，判断id 文案正确是否显示正确
	 */
	public static void  AssertThat_TextByXpathinWebView(String xpath ,String expectStr){
		try{
			String StrInFact = driver.findElement(By.xpath(xpath)).getAttribute("name");
			//			System.out.println(StrInFact);

			if(!expectStr.equals(StrInFact)){
				failAndsrceen("预期文案为："+expectStr+";实际文案为:"+StrInFact);
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("ID未找到");
		}
	}

	/**
	 * 判断id 文案正确是否显示正确
	 */
	public static Boolean CheckTextByXpath(String xpath ,String expectStr){
		try{
			String StrInFact = driver.findElementByXPath(xpath).getText();
			if(!expectStr.equals(StrInFact)){
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("ID未找到");
		}
		return true;
	}

	/**
	 * 判断xpath 文案正确是否显示正确  向下滚动
	 */
	public static Boolean CheckTextByXpath_Scroll(String xpath ,String expectStr){
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 50 * 1000) {
			isvisible = true;
			try {
				String StrInFact = driver.findElementByXPath(xpath).getText();
				if(!expectStr.equals(StrInFact)){
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;

				Scroll(3.0/4, 1.0/4);

			}
			if (isvisible) {
				return true;
			}
		}
		failAndsrceen("ID未找到");
		return false;

	}
	/** 
	 * 获取当前页面布局信息并存储到临时文件 
	 */
	public static void GetCurrentPageSourseToSave(String xmlname) {
		long starttimestamp = System.currentTimeMillis();
		Sleep(500);
		System.out.print("尝试获取当前页面信息");
		String currentPageSourse = driver.getPageSource();
		FileWriter mFileWriter;
		BufferedWriter mBufferedWriter = null;
		try {
			mFileWriter = new FileWriter(new File(getXMLPath(xmlname)), false);
			mBufferedWriter = new BufferedWriter(mFileWriter);
			mBufferedWriter.write(currentPageSourse);
			mBufferedWriter.close();
			System.out.println("  -- OK(耗时"
					+ (System.currentTimeMillis() - starttimestamp) / 1000
					+ ")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("  -- Fail");
		}
	}

	private static String getXMLPath(String xmlname) {
		return "CaseRunTempFiles" + File.separator + xmlname + ".xml";
	}


	/**
	 * case未完全检查，需人工check
	 */
	public static void Skipped() {
		System.out.println("case未完全检查，请人工check");
	}
	/**
	 * 判断元素是否显示
	 * @param id
	 * @return
	 */
	public static boolean isElementPresent(String id) {
		try {
			driver.findElementById(id);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	//
	public static boolean judgeElement(final String classid) {
		boolean b = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id(classid)));
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	//滑动
	public static void scroll(int times)
	{
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for(int i = 0; i < times; i++)
		{
			driver.swipe(width / 2, height * 3 / 4, width / 2,
					height / 4, 1000);
		}
	}
	//打印页面元素
	public static void OuputPagesource()
	{
		System.out.println(driver.getPageSource());
	}
	/**
	 * StringToDouble，去掉末尾不必要的0，如30.0-->30
	 * @param doub_number
	 * @return
	 */
	public static String DoubleToString(Double doub_number) {
		System.out.println(doub_number);
		String strNum= "" + doub_number;
		System.out.println(strNum);
		if(strNum.length() - strNum.indexOf(".") > 3){
			strNum = strNum.substring(0, strNum.indexOf(".") + 2);//精确到小数点后两位
			System.out.println(strNum);
		}else if(strNum.length() - strNum.indexOf(".") == 2){
			if(strNum.substring(strNum.indexOf(".") + 1).equals("0")){
				strNum = strNum.substring(0, strNum.indexOf("."));
				System.out.println(strNum);
			}
		}
		return strNum;
	}
	/**
	 * 用于标记CASE运行成功
	 */
	public static void Success() {
		System.out.println("恭喜你！case执行成功~~");
	}


	//////////////////////////////////////////////我是分割线  -- 外卖专用//////////////////////////////////////////////
	/**
	 * GotoCate函数
	 */
	public static void Gotocate(int index) {
		Sleep(2000);
		String xpathBefore = "//android.widget.LinearLayout[contains(@resource-id,\"com.baidu.lbs.waimai:id/bottom_bar\")]/android.view.View["+index+"]";
		long timestart = System.currentTimeMillis();
		while(System.currentTimeMillis() < timestart + 50 * 1000){
			try{	
				driver.findElementByXPath(xpathBefore).click();
				WaitForLoadingBearDisappear();
				if(index == 1){
					if(CheckStringIsShow("餐饮")){
						return;
					}else if(CheckStringIsShow("重新加载")){
						ClickOnText("重新加载");
						WaitForLoadingBearDisappear();
					}else if(CheckStringIsShow("刷新重试")){
						ClickOnText("刷新重试");
						WaitForLoadingBearDisappear();
					}else{
						while(!CheckStringIsShow("餐饮")){
							GoBack_Physical();
						}
					}
					if(CheckStringIsShow("餐饮")){
						return;
					}
				}else if(index == 2){
					if(CheckStringIsShow("吃啥")){
						return;
					}
				}else if(index == 3){
					if(CheckStringIsShow("订单")){
						return;
					}
				}else if(index == 4){
					if(CheckViewIsShow("setting_btn")){
						return;
					}
				}
				Sleep(500);
			}catch(Exception e){

			}
		}
		failAndsrceen("首页底部tab点击失败");

	}
	/**
	 * 确认dialog是否弹出，弹出返回true
	 */
	public static boolean CheckDialogIsShow(){
		try{
			driver.findElement(By.id("com.baidu.lbs.waimai.baidurider:id/ll_loading"));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * 断言对话框是否显示，不显示直接调用failandscreen
	 * @return
	 */
	public static void AssertThat_DialogIsShow(){
		try{
			driver.findElement(By.id("com.baidu.lbs.waimai.baidurider:id/ll_loading"));
		}catch(Exception e){
			failAndsrceen("对话框未显示");
		}
	}
	/**
	 * 断言对话框是否显示，不显示直接调用failandscreen
	 * @return
	 */
	public static void AssertThat_DialogIsShowB(){
		try{
			driver.findElement(By.id("com.baidu.lbs.waimai.baidurider:id/lv_detail_tips_desc"));
		}catch(Exception e){
			failAndsrceen("对话框未显示");
		}
	}
	/**
	 * 确认dialog是否弹出,并点击;0表示left;1表示right
	 */
	public static boolean CheckDialogIsShow(int button){
		try{
			driver.findElement(By.id("dialog_button_container"));
		}catch(Exception e){
			return false;
		}
		Sleep(500);
		try{
			if(button == 0){
				driver.findElement(By.id("dialog_button_left")).click();
			}else if(button == 1){
				driver.findElement(By.id("dialog_button_right")).click();
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	/**
	 * 断言对话框是否显示，不显示直接调用failandscreen
	 * @return
	 */
	public static void AssertThat_DialogIsShow(int button){
		try{
			driver.findElement(By.id("content"));
		}catch(Exception e){
			failAndsrceen("对话框未展示");
		}
		Sleep(500);
		try{
			if(button == 0){
				driver.findElement(By.id("cancel")).click();
			}else if(button == 1){
				driver.findElement(By.id("ok")).click();
			}
		}catch(Exception e){
			failAndsrceen("对话框button展示不正确");
		}
	}

	/**
	 * 确认dialog是否弹出,并点击button（0表示left;1表示right），校验对话框文案
	 */
	public static boolean CheckDialogIsShow(int button,String text){
		try{
			if(!driver.findElement(By.id("content")).getText().equals(text)){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return CheckDialogIsShow(0);
	}
	/**
	 * 断言对话框是否显示，不显示直接调用failandscreen
	 * @return
	 */
	public static void AssertThat_DialogIsShow(int button,String text){
		try{
			if(!driver.findElement(By.id("content")).getText().equals(text)){
				failAndsrceen("对话框文案错误");
			}
		}catch(Exception e){
			failAndsrceen("对话框未弹出");
		}
		AssertThat_DialogIsShow(1);
	}



	/**
	 * 确认dialog是否弹出
	 */
	public static void GoBack(){
		Sleep(500);
		try{
			driver.findElementById("back").click();
		}catch(Exception e){
			driver.findElementById("actionbar_left").click();
		}
	}

	/**
	 * 判断title是否显示正确
	 * @return 
	 */
	public static void CheckTitleBar(String expectTitle){
		WaitForStringShow(expectTitle);
		try{
			String titleInFact = driver.findElementById("com.baidu.lbs.waimai:id/title").getText();
			if(!expectTitle.equals(titleInFact)){
				failAndsrceen("预期title为："+expectTitle+";实际title为:"+titleInFact);
			}
		}catch (Exception e) {
			// TODO: handle exception
			failAndsrceen("title ID错误");
		}
	}
	/**
	 * 判断loading bear消失
	 */
	public static void WaitForLoadingBearDisappear(){
		long starttimestamp = System.currentTimeMillis();
		boolean isvisible = false;
		while (System.currentTimeMillis() < starttimestamp + 30 * 1000) {
			isvisible = true;
			try {
				WebElement webElement = driver.findElementById("img");
			} catch (Exception e) {
				// TODO: handle exception
				isvisible = false;
			}
			if (!isvisible) {
				System.out.println("已消失->"+"loading_bear_iv");
				return;
			}
		}
		failAndsrceen("30秒后仍显示-> " + "loading_bear_iv");
	}
	/**
	 * 登录
	 */
	public static void  login(String account,String password ) {
		Sleep(1000);
		driver.findElementByXPath("//android.widget.EditText[contains(@index,0)]").click();
		deleteEditText();
		driver.getKeyboard().sendKeys(account);
		driver.findElementByXPath("//android.widget.EditText[contains(@index,1)]").click();
		driver.getKeyboard().sendKeys(password);
		driver.findElementByAndroidUIAutomator("new UiSelector().description(\"登录\")").click();
		Sleep(1000);
	}
	/**
	 * 输入提现密码，使用pswConfig中的密码
	 */
	public static void  txpsw(String PASSWORD) {
		Sleep(1000);
		//		deleteEditText();
		driver.getKeyboard().sendKeys(PASSWORD);
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"下一步\")").click();	
	}

	/**
	 * 输入提现金额，点击提交
	 */
	public static void  Input_TiXianJinE_TiJiao(String PASSWORD) {
		Sleep(1000);
		//		deleteEditText();
		driver.getKeyboard().sendKeys(PASSWORD);
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"提现\")").click();	
	}

	/**
	 * 输入提现金额，查看编辑框实际输入内容
	 */
	public static void  Input_TiXianJinE_BianJi(String sendString,String expString) {
		ClickOnView("et_balance");
		driver.getKeyboard().sendKeys(sendString);
		Sleep(500);

		if(!CheckTextById("et_balance", expString)){
			System.err.println("没有对直接输入"+sendString+"这种情况进行处理");
		}
		ClickOnView("et_balance");
		deleteEditText();
	}

	/**
	 * 输入提现密码，点击确认
	 */
	public static void  Input_TiXianMiMa_QueRen(String PASSWORD) {
		Sleep(1000);
		//		deleteEditText();
		driver.getKeyboard().sendKeys(PASSWORD);
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认\")").click();	
	}

	/**
	 * 获取当前日期 年月日
	 */
	public static String GetNowDate(){   
		String temp_str="";   
		Date dt = new Date();   
		//最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   // HH:mm:ss aa
		temp_str=sdf.format(dt);   
		return temp_str;   
	} 

	/**
	 * 获取当前日期 月日
	 */
	public static String GetNowDate_md(){   
		String temp_str="";   
		Date dt = new Date();   
		//最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");   // HH:mm:ss aa
		temp_str=sdf.format(dt);   
		return temp_str;   
	} 

	/**
	 * 获取当前日期 日
	 */
	public static String GetNowDate_d(){   
		String temp_str="";   
		Date dt = new Date();   
		//最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		SimpleDateFormat sdf = new SimpleDateFormat("dd");   // HH:mm:ss aa
		temp_str=sdf.format(dt);   
		return temp_str;   
	} 

	/**
	 * 获取当前日期 月
	 */
	public static String GetNowDate_m(){   
		String temp_str="";   
		Date dt = new Date();   
		//最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		SimpleDateFormat sdf = new SimpleDateFormat("MM");   // HH:mm:ss aa
		temp_str=sdf.format(dt);   
		return temp_str;   
	} 
	/**
	 * 显示当前在岗状态
	 */
	public static void show_zaigangzhuangtai(){
		if(CheckTextById("tv_work_current_status","在岗")){
			System.out.println("当前为在岗状态");
		}else if(CheckTextById("tv_work_current_status","小休")){
			System.out.println("当前为小休状态");
		}else{
			System.out.println("当前为离岗状态");
		}
	}

	/**
	 * 点击头像进入侧栏
	 */
	public static void Goto_UserCenter0(){
		WaitForLoadingBearDisappear();
		WaitForViewShowAndClick("iv_slip_toggle"); //点击头像
		WaitForLoadingBearDisappear();

	}
	/**
	 * 点击头像进入侧栏，通过文案点击进入某模块
	 */
	public static void Goto_UserCenter1(String which_Model){
		WaitForLoadingBearDisappear();
		WaitForViewShowAndClick("iv_slip_toggle");//iv_slip_toggle
		WaitForLoadingBearDisappear();
		WaitForStringShowAndClick_Scroll(which_Model);
		WaitForLoadingBearDisappear();

	}
	/**
	 * 点击头像进入侧栏，通过文案点击进入某模块，再文案点击进入子模块
	 */
	public static void Goto_UserCenter2(String which_Model,String which_SmallModel){
		WaitForLoadingBearDisappear();
		WaitForViewShowAndClick("iv_slip_toggle");
		WaitForLoadingBearDisappear();
		WaitForStringShowAndClick_Scroll(which_Model);
		WaitForLoadingBearDisappear();
		WaitForStringShowAndClick(which_SmallModel);
		WaitForLoadingBearDisappear();
	}
	/**
	 * 切换在岗状态并且判断当前在岗状态
	 */
	public static void QieHuanAndBijiao_ZaiGangZhuangTai_Now(String ZaiGangZhuangTai_Now){
		ClickOnView("tv_work_current_status");
		ClickOnText(ZaiGangZhuangTai_Now);
		WaitForLoadingBearDisappear();
		AssertThat_TextById("tv_work_current_status", ZaiGangZhuangTai_Now);
	}


	/**
	 * 判断提现密码是否正确
	 */
	public static void Juge_TiXianMiMa_isRight(String Psw1,String Psw2,String BiaoTi,String WenAn){
		AssertThat_DialogIsShow();  
		txpsw(Psw1);
		AssertThat_DialogIsShow();  
		txpsw(Psw2); 
		if(!CheckStringIsShow(BiaoTi)){
			System.err.println(WenAn);
			return ;
		}
	}
	/**
	 * 获取当前的剩余提现次数
	 */
	public static String get_Now_TiXianCiShu(){

		String QiTiJinE=get_Now_ShuZi("tv_cash_limit");
		String Num = QiTiJinE.substring(0,QiTiJinE.length()-3);
		return Num;
	}
	/**
	 * 获取一个id中的数字部分
	 */
	public static String get_Now_ShuZi(String Id){
		String QiTiJinE =GetStringByid(Id);
		String NotNumString="[^0-9]"; 
		Pattern NotNumStringp = Pattern.compile(NotNumString); 
		Matcher m = NotNumStringp.matcher(QiTiJinE); 
		String num=m.replaceAll("").trim().toString();
		return num;
	}
	/**
	 * 获取一个string中的数字部分
	 */
	public static String get_Nowstring_ShuZi(String WenAn){

		String NotNumString="[^0-9]"; 
		Pattern NotNumStringp = Pattern.compile(NotNumString); 
		Matcher m = NotNumStringp.matcher(WenAn); 
		String num=m.replaceAll("").trim().toString();
		return num;
	}
	/**
	 * 获取违规次数，违规次数存在则返回true，否则返回false
	 */
	public static boolean getWeiGuiCiShu(){
		String WeiGuiCiShu =GetStringByXpath(XpathCommon.JIXIAOKAOHE_WEIGUIJILU);
		if(WeiGuiCiShu.equals("暂无")){
			return false;
		}
		//		String NotNumString="[^1-9]"; 
		//		Pattern NotNumStringp = Pattern.compile(NotNumString); 
		//		Matcher m = NotNumStringp.matcher(WeiGuiCiShu); 
		//		String num=m.replaceAll("").trim().toString();
		//		if(num.equals("")){
		//			return false;             //后续历史订单的时候可以维护
		//		}
		return true;
	}
	/**
	 * 滚动式的控件选择第一项
	 */
	public static void XuanZe_HuaDongKongJian(String Id){
		WaitForViewShowAndClick(Id);

		ClickOnView("ok");
	}
	/**
	 * 滚动式的控件选择最后一项
	 */
	public static void XuanZe_HuaDongKongJian_last(String Id){
		WaitForViewShowAndClick(Id);
		swipeToUp_half(500);
		Sleep(1000);
		ClickOnView("ok");
	}

	/**
	 * 查看各tab下英雄榜的页面元素是否正常
	 */
	public static void Check_YingXiongBang_YeMianYuanSu(String Real_Name,String tab, String DanWei,String msg){
		if(CheckViewIsShow("tv_empty")){

		}
		else if(CheckViewIsShow("ll_herolist_container")){
			AssertThat_TextByXpath(XpathCommon.JIXIAOKAOHE_YINGXIONGBANG_WO, "我");
			String PaiMingString = GetStringByXpath(XpathCommon.JIXIAOKAOHE_YINGXIONGBANG_WOPaiMing);
			if(!(PaiMingString.startsWith("第")&PaiMingString.endsWith("名"))){
				System.err.println("准时率tab下有数据，但是排名显示有问题");
			}
			AssertThat_TextByXpath(XpathCommon.JIXIAOKAOHE_YINGXIONGBANG_Name, Real_Name);
			AssertThat_TextById("tv_herolist_unit",DanWei );
			AssertThat_TextById("tv_herolist_rank", "1");   //往下判断1个人的排名
			if(tab.equals("完成单量")){
				if(!CheckViewIsShow("tv_distance")){
					System.err.println("没有显示单均骑行距离");
				}
			}
		}
		else{
			System.err.println(tab+"tab下页面不正常，请查看截图");
			failAndsrceen(msg);
		}
	}
	/**
	 * 清空购物车
	 */
	public static void DeleteShoppingCart(){
		//判断购物车是否为空，为空，返回
		if(CheckStringIsShow(StringCommon.ShoppingCart_IsEmpty)){
			return;
		}
		//判断【清空购物车】是否显示
		if(!CheckStringIsShow(StringCommon.ShoppingCart_ToEmpty)){
			ClickOnView(IdCommon.ShoppingCart_Total_Price);
		}
		WaitForStringShowAndClick(StringCommon.ShoppingCart_ToEmpty);
		AssertThat_DialogIsShow(1);
		Sleep(500);
		CheckStringIsShow(StringCommon.ShoppingCart_IsEmpty);
	}
	/**
	 * 自动下单
	 */
	public static void AutoPlaceOrder(int Times){
		driver.startActivity("com.baidu.lbs.waimai", "com.baidu.lbs.waimai.SplashActivity");
		Sleep(2000);
		WaitForStringShow("餐饮");
		//		ClickOnViewXpath("//android.widget.LinearLayout[contains(@resource-id,\"com.baidu.lbs.waimai:id/bottom_bar\")]/android.view.View[4]");
		ClickOnViewXpath("//android.widget.LinearLayout[contains(@resource-id,\"com.baidu.lbs.waimai:id/bottom_bar\")]/android.widget.LinearLayout[4]/android.widget.RelativeLayout");

		WaitForStringShowAndClick("收藏店铺");
		WaitForStringShowAndClick_Scroll(StringCommon.TestShopName_Food);
		WaitForLoadingBearDisappear();
		DeleteShoppingCart();
		WaitForViewShowAndClick_Scroll("com.baidu.lbs.waimai:id/waimai_shopmenu_dish_plus");
		int i= 0;
		while(i<Times){
			i++;
			WaitForStringShowAndClick("选好了");
			WaitForStringShowAndClick("百度钱包");
			WaitForStringShowAndClick("货到付款");
			//			ClickOnText("确定");
			WaitForLoadingBearDisappear();
			//			WaitForStringShowAndClick("口味备注/用餐人数/发票");
			//			WaitForViewShowAndClick("com.baidu.lbs.waimai:id/remarks_inputer");
			//			driver.getKeyboard().sendKeys("ET_自动下单-可拍小票"+i); 
			//			ClickOnView("right");
			WaitForStringShowAndClick("去下单");
			WaitForLoadingBearDisappear();
			//			driver.tap(1, 540,1570,200);
			//			Sleep(1000);
			ClickOnText("查看订单");
			WaitForLoadingBearDisappear();
			WaitForStringShowAndClick_Scroll("再来一单");
		}
	}
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public static String PostHttpContent(String url, int connectTimeout, int readTimeout, String cookie,String order_id){
		CloseableHttpClient client = HttpClients.createDefault();;
		String responseString = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("order_id", order_id));
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader(new BasicHeader("Cookie", cookie));
			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(postEntity);
			System.out.println(cookie);
			HttpResponse httpResponse = client.execute(httpPost);
			responseString = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(responseString);


		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(client != null)
					client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return responseString;
	}
	public static String qishi_order_process_post(String url, List<NameValuePair> nameValuePairs, String cookie){

		CloseableHttpClient client = HttpClients.createDefault();
		String responseString = "";
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.addHeader(new BasicHeader("Cookie", cookie));
			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			httpPost.setEntity(postEntity);
			System.out.println(httpPost.getRequestLine());
			HttpResponse httpResponse = client.execute(httpPost);
			responseString = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(responseString);


		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(client != null)
					client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return responseString;



	}
	public static String riderGetHttpContent(String url){
		CloseableHttpClient client = null;
		String responseString = "";
		try {
			String URL = url;
			System.out.println(URL);

			HttpGet httpGet = new HttpGet(URL);
			//    	   RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(readTimeout).build();
			//    	   httpGet.setConfig(requestConfig);
			//    	   httpGet.setHeader("Cookie: ", cookie);
			//  httpclient
			client = HttpClients.createDefault();
			HttpResponse response = client.execute(httpGet);
			System.out.println("返回的Json：" + response);

			responseString = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(client != null)
					client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//  System.out.println("返回的Json：" + responseString);
		return responseString;
	}
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	/**
	 * 保存Cookies
	 * 
	 * @param response
	 *            servlet请求
	 * @param value
	 *            保存值
	 * @author jxf
	 */
	public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value,int time) {
		// new一个Cookie对象,键值对为参数
		Cookie cookie = new Cookie(name, value);
		// tomcat下多应用共享
		cookie.setPath("/");
		// 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
		try {
			URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		cookie.setMaxAge(time);
		// 将Cookie添加到Response中,使之生效
		response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
		return response;
	}

}

