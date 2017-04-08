package com.naga.test;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import com.naga.NagaDriver;

public class NagaH5Test {
	NagaDriver driver = new NagaDriver();

	@Before
	public void setUp() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("browser", "Safari");
//		jsonObject.put("url", "http://www.baidu.com");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("port", "8901");
		 desiredCapabilities.put("udid", "E36F8D1D-0DD8-4B1A-81AA-611585A5C034");
		desiredCapabilities.put("desiredCapabilities", jsonObject);
		driver.initDriver(desiredCapabilities);
	}

	@Test
	public void test_case_1() throws Exception {
		// set screenshot save path
		String imageFilepath = System.getProperty("user.dir");

		System.out.println("------------#1 baidu web test-------------------");
		driver.sleep(2);
		driver.toUrl("http://www.baidu.com");
		driver.sleep(2);
		driver.saveScreenShot(imageFilepath + "/baidu.png");

		driver.context("WEBVIEW_百度一下");
		driver.sleep(5);
		driver.findElementById("index-kw").sendKeys("中文+TesterHome");
		driver.findElementById("index-bn").click();
		driver.sleep(3);
		String source = driver.source();
		System.out.println(source);
		Assert.assertThat(source, containsString("TesterHome"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
