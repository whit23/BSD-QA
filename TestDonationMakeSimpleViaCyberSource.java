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

public class TestDonationMakeSimpleViaCyberSource {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationMakeSimpleViaCyberSource() throws Exception {
		selenium.open("https://my.democrats.org/page/contribute/donate-to-help-democrats-today?nomin=1");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Contributor")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=firstname", "James");
		selenium.type("id=lastname", "Whitfield");
		selenium.type("id=addr1", "255 East Street");
		selenium.type("id=city", "Dedham");
		selenium.select("id=state_cd", "label=MA");
		selenium.type("id=zip", "02026");
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", "jameswhitfield@verizon.net");
		assertTrue(selenium.isElementPresent("id=phone"));
		selenium.type("id=phone", "7814070877");
		selenium.click("id=amt_other");
		assertEquals("on", selenium.getValue("id=amt_other"));
		selenium.type("id=amt_other_text", ".01");
		selenium.click("id=cc_vs");
		assertEquals("on", selenium.getValue("id=cc_vs"));
		selenium.type("id=cc_number", "4117704033781625");
		selenium.select("id=cc_expir_month", "label=12");
		selenium.select("id=cc_expir_year", "label=2016");
		assertTrue(selenium.isElementPresent("id=employer"));
		selenium.type("id=employer", "WPPGY");
		assertTrue(selenium.isElementPresent("id=occupation"));
		selenium.type("id=occupation", "SQE");
		assertTrue(selenium.isElementPresent("id=processbutton"));
		selenium.click("id=processbutton");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Your donation was successful. Thank you.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals("Quick Donate Opt In", selenium.getTitle());
		assertTrue(selenium.isElementPresent("id=BSD-qd_optin-skip"));
		selenium.click("id=BSD-qd_optin-skip");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("You're almost done")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
