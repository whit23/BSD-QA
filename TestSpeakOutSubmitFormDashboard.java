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

public class TestSpeakOutSubmitFormDashboard {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Constituent/Login?successurl=L3BhZ2UvZGFzaGJvYXJkL3ZpZXcvcHJpdmF0ZQ==&_h=ZwrTOOyhCP2B5OzFarI5t2-vpRk";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSpeakOutSubmitFormDashboard() throws Exception {
		selenium.open("http://demo.bluestatedigital.com/ctl/Constituent/Login?successurl=L3BhZ2UvZGFzaGJvYXJkL3ZpZXcvcHJpdmF0ZQ==&_h=ZwrTOOyhCP2B5OzFarI5t2-vpRk");
		selenium.type("id=username", "jwhitfield@bluestatedigital.com");
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("name=login_submit"));
		selenium.click("name=login_submit");
		selenium.waitForPageToLoad("60000");
		selenium.open("http://demo.bluestatedigital.com/page/dashboard/view/private");
		verifyTrue(selenium.isTextPresent("Peer Contact"));
		assertTrue(selenium.isElementPresent("link=Xebium_Ongoing_Speak_Out_Campaign"));
		selenium.click("link=Xebium_Ongoing_Speak_Out_Campaign");
		selenium.waitForPageToLoad("60000");
		selenium.open("http://demo.bluestatedigital.com/page/speakout/xebium_ongoing_speak_out_campaign");
		assertTrue(selenium.isElementPresent("id=zip"));
		selenium.type("id=zip", "02210");
		assertTrue(selenium.isElementPresent("css=input.landing-button"));
		selenium.click("css=input.landing-button");
		selenium.waitForPageToLoad("60000");
		verifyTrue(selenium.isTextPresent("Select the recipient(s)"));
		selenium.click("id=spk_recipient_id_1_26");
		verifyTrue(selenium.isTextPresent("Country"));
		selenium.select("id=country", "label=United States");
		verifyTrue(selenium.isTextPresent("First Name"));
		assertTrue(selenium.isElementPresent("id=firstname"));
		selenium.type("id=firstname", "James");
		verifyTrue(selenium.isTextPresent("Last Name"));
		assertTrue(selenium.isElementPresent("id=lastname"));
		selenium.type("id=lastname", selenium.getEval("'Whitfield_SOC_DB'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Email"));
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", selenium.getEval("\"jwhitfield+soc_db\"+ Math.floor(Math.random()*10000) + \"@bluestatedigital.com\";"));
		verifyTrue(selenium.isTextPresent("Phone"));
		assertTrue(selenium.isElementPresent("id=phone"));
		selenium.type("id=phone", "800-800-8000");
		verifyTrue(selenium.isTextPresent("Subject"));
		assertTrue(selenium.isElementPresent("id=subject"));
		selenium.type("id=subject", "Xebium_SOC_DB_GP_Tests");
		verifyTrue(selenium.isTextPresent("exact:Message*"));
		selenium.type("id=body", "Xebium_SOC_DB_GP_Tests - Message...");
		assertTrue(selenium.isElementPresent("name=preview_button[next]"));
		selenium.click("name=preview_button[next]");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Xebium_Ongoing_Speak_Out_Campaign")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("name=send_button[send]"));
		selenium.click("name=send_button[send]");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Xebium_Ongoing_Speak_Out_Campaign")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Thank You Text for Speaking Out using Xebium!!!"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
