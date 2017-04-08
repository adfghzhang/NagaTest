package com.naga.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import com.naga.NagaDriver;

public class NagaHybridTest {
	NagaDriver driver = new NagaDriver();

	@Before
	public void setUp() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("bundleId", "xdf.ios-app-bootstrap");
		jsonObject.put("app", System.getProperty("user.dir") + "/apps/ios-app-bootstrap.app");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("port", "8901");
		desiredCapabilities.put("udid", "9CC1ADCC-94CE-47C3-AA4F-5008AE463119");
		desiredCapabilities.put("desiredCapabilities", jsonObject);
		driver.initDriver(desiredCapabilities);
	}

	@Test
	public void test_case_1() throws Exception {
		// set the screenshot image where to save
		String imageFilepath = System.getProperty("user.dir");

		System.out.println("------------#1 login test-------------------");

		driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("中文+Test+12345678");
		driver.findElementByXPath("//XCUIElementTypeSecureTextField[1]").sendKeys("111111");
		driver.findElementByName("Done").click();
		driver.findElementByName("Login").click();
		driver.sleep(1);

		System.out.println("------------#2 scroll tableview test-------------------");

		driver.findElementByName("HOME").click();
		driver.findElementByName("list").click();
		driver.sleep(1);
		driver.swipe(200, 420, 200, 120, 1);
		driver.sleep(2);

		System.out.println("------------#3 webview test-------------------");

		driver.findElementByName("Webview").click();
		driver.sleep(3);
		// save screen shot
		driver.saveScreenShot(imageFilepath + "/webView.png");

		driver.context("WEBVIEW_test");
		driver.sleep(2);
		driver.findElementById("pushView").click();
		driver.sleep(3);
		driver.findElementById("popView").click();
		driver.sleep(2);

		System.out.println("------------#4 baidu web test-------------------");
		switchToNative();
		driver.findElementByName("Baidu").click();
		driver.sleep(5);
		driver.saveScreenShot(imageFilepath + "/baidu.png");

		driver.context("WEBVIEW_百度一下");
		// driver.context("WEBVIEW_2");
		driver.findElementById("index-kw").sendKeys("中文+TesterHome");
		driver.findElementById("index-bn").click();
		driver.sleep(5);
		System.out.println(driver.source());

		System.out.println("------------#5 logout test-------------------");

		switchToNative();
		driver.findElementByName("PERSONAL").click();
		driver.sleep(1);
		driver.findElementByName("Logout").click();
		driver.sleep(1);
	}

	// switch to native
	public void switchToNative() throws Exception {
		driver.context("NATIVE_APP");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
