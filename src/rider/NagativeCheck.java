package rider;

import org.testng.annotations.Test;

import rider.lib.BaseTest;

public class NagativeCheck extends BaseTest{
	//-------------------------------------换绑骑士手机号---------------------------------------
			/**
			 * @Name test_2000_ChangePhoneNum
			 * @Catalogue 换绑骑士手机号
			 * @Subcatalog 验证码部分
			 * @Grade P0
			 * @Describe 判断验证码是否展示
			 * @FunctionPoint 验证码没有 加载失败
			 */
			@Test
			public void test_2000_ChangePhoneNum() {
				Goto_UserCenter0();
				WaitForStringShowAndClick_Scroll("系统设置");
				WaitForStringShowAndClick("绑定手机");
				WaitForStringShowAndClick("更换绑定手机号");
				WaitForStringShow("下一步");
				ClickOnText("请输入身份证号");
				driver.getKeyboard().sendKeys("410122199109246524"); 
				ClickOnText("下一步");
				WaitForStringShow("获取验证码");
				ClickOnText("请输入11位手机号");
				driver.getKeyboard().sendKeys("13601023270"); 
				ClickOnText("获取验证码");
				WaitForStringShow("确定");
				Sleep(5000);
				if(CheckStringIsShow("加载失败")){
					failAndsrceen("图片验证码加载失败");
				}
				Success();
			}
	//-------------------------------------换绑银行卡---------------------------------------
			/**
			 * @Name test_2002_ChangeBundleCard
			 * @Catalogue 换绑银行卡
			 * @Subcatalog 验证码部分
			 * @Grade P0
			 * @Describe 判断验证码是否展示
			 * @FunctionPoint 验证码没有 加载失败
			 */
			@Test
			public void test_2002_ChangeBundleCard() {
				Goto_UserCenter0();
				WaitForStringShowAndClick_Scroll("我的余额");
				WaitForStringShowAndClick("设置");
				WaitForViewShowAndClick("com.baidu.lbs.waimai.baidurider:id/tv_change_bind");
				WaitForStringShow("下一步");
				driver.getKeyboard().sendKeys("123789"); 
				ClickOnText("下一步");
				ClickOnText("请输入银行卡号");
				driver.getKeyboard().sendKeys("1360102327099999999"); 
				ClickOnText("请选择");
				ClickOnText("确定");
				ClickOnText("请选择");
				ClickOnText("确定");
				ClickOnText("请选择");
				driver.getKeyboard().sendKeys("渤海银行股份有限公司北京安贞支行"); 
				ClickOnText("确定");
				ClickOnText("获取验证码");
				
				Sleep(5000);
				if(CheckStringIsShow("加载失败")){
					failAndsrceen("图片验证码加载失败");
				}
				Success();
			}
	//-------------------------------------解绑银行卡---------------------------------------
			/**
			 * @Name test_2003_CancleBundleCard
			 * @Catalogue 解绑银行卡
			 * @Subcatalog 验证码部分
			 * @Grade P0
			 * @Describe 判断验证码是否展示
			 * @FunctionPoint 验证码没有 加载失败
			 */
			@Test
			public void test_2003_CancleBundleCard() {
				Goto_UserCenter0();
				WaitForStringShowAndClick_Scroll("我的余额");
				WaitForStringShowAndClick("设置");
				WaitForViewShowAndClick("com.baidu.lbs.waimai.baidurider:id/tv_unbind");
				WaitForStringShow("下一步");
				driver.getKeyboard().sendKeys("123789"); 
				ClickOnText("下一步");
				WaitForStringShowAndClick("获取验证码");
				
				Sleep(5000);
				if(CheckStringIsShow("加载失败")){
					failAndsrceen("图片验证码加载失败");
				}
				Success();
			}
	//-------------------------------------重置提现密码---------------------------------------
			/**
			 * @Name test_2004_ResetDepositPsw
			 * @Catalogue 重置提现密码
			 * @Subcatalog 验证码部分
			 * @Grade P0
			 * @Describe 判断验证码是否展示
			 * @FunctionPoint 验证码没有 加载失败
			 */
			@Test
			public void test_2004_ResetDepositPsw() {
				Goto_UserCenter0();
				WaitForStringShowAndClick_Scroll("我的余额");
				WaitForStringShowAndClick("设置");
				WaitForStringShowAndClick("重置提现密码");
				WaitForStringShow("下一步");
				ClickOnText("请输入身份证号");
				driver.getKeyboard().sendKeys("410122199109246524"); 
				ClickOnText("获取验证码");
				WaitForStringShow("确定");
				Sleep(5000);
				if(CheckStringIsShow("加载失败")){
					failAndsrceen("图片验证码加载失败");
				}
				Success();
			}
	//-------------------------------------忘记密码---------------------------------------
		/**
		 * @Name test_2000_ForgotPsw
		 * @Catalogue 忘记密码
		 * @Subcatalog 验证码部分
		 * @Grade P0
		 * @Describe 判断验证码是否展示
		 * @FunctionPoint 验证码没有 加载失败
		 */
		@Test
		public void test_2004_ForgotPsw() {
			Goto_UserCenter0();
			WaitForStringShowAndClick_Scroll("系统设置");
			WaitForStringShowAndClick("退出当前账号");
			WaitForViewShowAndClick("ok");
			WaitForStringShowAndClick("忘记密码");     //判断是否回到登陆页面
			WaitForStringShow("下一步");
			Sleep(5000);
			if(CheckStringIsShow("加载失败")){
				failAndsrceen("图片验证码加载失败");
			}
			Success();
		}
}
