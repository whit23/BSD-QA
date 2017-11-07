package com.example.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class TestConsUpload {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testConsUpload() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=People & Lists");
		selenium.selectWindow("null");
		assertTrue(selenium.isElementPresent("id=cons-upload"));
		selenium.click("id=cons-upload");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Control Panel | Upload People (Constituents)", selenium.getTitle());
		assertTrue(selenium.isElementPresent("name=csv_file"));
		selenium.type("name=csv_file", "/Users/jwhitfield/Desktop/Upload_Files/Cons_Upload_10.csv");
		assertTrue(selenium.isElementPresent("name=submit_button"));
		selenium.click("name=submit_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=input.page_submit_button")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=input.page_submit_button");
		selenium.waitForPageToLoad("60000");
		verifyEquals("BSD Control Panel", selenium.getTitle());
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Finishing Constituent Upload")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Constituent Upload Being Processed"));
		verifyTrue(selenium.isTextPresent("10 records will begin processing to be loaded into the database."));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
