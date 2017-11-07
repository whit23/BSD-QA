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

public class TestSpeakOutCreateNew {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSpeakOutCreateNew() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		verifyTrue(selenium.isTextPresent("Email"));
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=Your Website");
		selenium.click("id=website-pages-advocacy");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Speak Out Campaigns")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("name=new");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Create Speak Out Campaign")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("How would you like to create your new Speak Out campaign?"));
		assertTrue(selenium.isElementPresent("link=Create a new Speak Out campaign"));
		selenium.click("link=Create a new Speak Out campaign");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Use this page to create a new campaign. If you are cloning another campaign, any existing information from that campaign is loaded by default. To save your entries on this page, be sure to click \"Create Speak Out Campaign\", at the bottom.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Recipient Type:"));
		assertTrue(selenium.isElementPresent("id=spk_recipient_type_id[0]"));
		selenium.select("id=spk_recipient_type_id[0]", "label=Daily Newspapers");
		verifyTrue(selenium.isTextPresent("Extra Custom Recipients:"));
		assertTrue(selenium.isElementPresent("id=spk_recipient_type_id[1]"));
		selenium.select("id=spk_recipient_type_id[1]", "label=Test");
		verifyTrue(selenium.isTextPresent("Campaign Name:"));
		assertTrue(selenium.isElementPresent("id=title"));
		selenium.type("id=title", selenium.getEval("'Xebium_Speak_Out_Campaign'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("Slug:"));
		assertTrue(selenium.isElementPresent("id=slugInput-textBox-value"));
		selenium.type("id=slugInput-textBox-value", selenium.getEval("'xebium_speak_out_campaign'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("Short Campaign Description:"));
		assertTrue(selenium.isElementPresent("name=short_description"));
		selenium.type("name=short_description", "Short Campaign Description");
		verifyTrue(selenium.isTextPresent("Campaign Description:"));
		assertTrue(selenium.isElementPresent("id=description"));
		selenium.type("id=description", "Campaign Description");
		verifyTrue(selenium.isTextPresent("Recipient Letter Limit:"));
		assertTrue(selenium.isElementPresent("name=recipient_letter_limit"));
		selenium.type("name=recipient_letter_limit", "1");
		verifyTrue(selenium.isTextPresent("Thank You Redirect Options"));
		assertTrue(selenium.isElementPresent("name=thank_you_type"));
		selenium.select("name=thank_you_type", "label=Display Thank You Message");
		verifyTrue(selenium.isTextPresent("Thank You Text:"));
		assertTrue(selenium.isElementPresent("id=thank_you_text"));
		selenium.type("id=thank_you_text", "Thank You Text for Speaking Out using Xebium!!!");
		verifyTrue(selenium.isTextPresent("Make Campaign Searchable:"));
		verifyTrue(selenium.isTextPresent("Page Wrapper:"));
		assertTrue(selenium.isElementPresent("name=page_wrapper_id"));
		selenium.select("name=page_wrapper_id", "label=Colorado");
		assertTrue(selenium.isElementPresent("name=button_group"));
		selenium.click("name=button_group");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Speak Out Campaigns")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Xebium_Speak_Out_Campaign"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
