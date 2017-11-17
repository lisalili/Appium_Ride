package rider.lib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;


public class pswConfig {

	
	public static String caseScreenSavePath = new File("screenshots").getAbsolutePath()+File.separator;
	public static String ConsoleLogSavePath = new File("ConsolePrint").getAbsolutePath() +File.separator+"resultPrint.xml";

	public static Boolean HUIGUI = false; 
	
	public static String PASSWORD1 = "123789";
	public static String PASSWORD2 = "789123";
}
