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

public class TestSpeakOutSubmitFormControlPanel {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSpeakOutSubmitFormControlPanel() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=Your Website");
		selenium.click("id=website-pages-advocacy");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Speak Out Campaigns")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isElementPresent("name=q"));
		selenium.type("name=q", "1-xebium");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("60000");
		selenium.open("/page/speakout/1-xebium_ongoing_soc_cp");
		verifyTrue(selenium.isTextPresent("1-Xebium_Ongoing_SOC_CP"));
		verifyTrue(selenium.isTextPresent("To get started, enter your zip code below and click 'Participate'"));
		assertTrue(selenium.isElementPresent("id=zip"));
		selenium.type("id=zip", "02210");
		assertTrue(selenium.isElementPresent("css=input.landing-button"));
		selenium.click("css=input.landing-button");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-Xebium_Ongoing_SOC_CP")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Step 1:"));
		selenium.click("id=spk_recipient_id_1_82");
		selenium.select("id=country", "label=United States");
		selenium.type("id=firstname", "James");
		selenium.type("id=lastname", selenium.getEval("'Whitfield_SOC_CP'+Math.floor(Math.random()*10000)"));
		selenium.type("id=email", selenium.getEval("\"jwhitfield+soc_cp\"+ Math.floor(Math.random()*10000) + \"@bluestatedigital.com\";"));
		selenium.type("id=phone", "8008008000");
		verifyTrue(selenium.isTextPresent("Step 2: Compose E-Mail"));
		selenium.type("id=subject", "Test");
		selenium.type("id=body", "Test");
		assertTrue(selenium.isElementPresent("name=preview_button[next]"));
		selenium.click("name=preview_button[next]");
		selenium.waitForPageToLoad("60000");
		verifyTrue(selenium.isTextPresent("Step 3: Preview E-Mail"));
		assertTrue(selenium.isElementPresent("name=send_button[send]"));
		selenium.click("name=send_button[send]");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-Xebium_Ongoing_SOC_CP")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
