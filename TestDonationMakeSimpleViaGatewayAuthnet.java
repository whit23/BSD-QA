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

public class TestDonationMakeSimpleViaGatewayAuthnet {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://secure.clintonbushhaitifund.org/page/contribute/xebium_ongoing_sfrp_ccvv_donation_page";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationMakeSimpleViaGatewayAuthnet() throws Exception {
		selenium.open("https://secure.alfranken.com/page/contribute/xebium_gp_sfrp_anocvv?nomin=1");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Contributor")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=firstname", "James");
		selenium.type("id=lastname", "Whitfield");
		selenium.type("id=addr1", "255 East Street");
		selenium.type("id=city", "Dedham");
		verifyTrue(selenium.isElementPresent("id=state_cd"));
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
		selenium.type("id=cc_number", "");
		selenium.select("id=cc_expir_month", "label=12");
		selenium.select("id=cc_expir_year", "label=2018");
		assertTrue(selenium.isElementPresent("id=legal_confirm"));
		selenium.click("id=legal_confirm");
		assertTrue(selenium.isElementPresent("id=employer"));
		selenium.type("id=employer", "WPPGY");
		assertTrue(selenium.isElementPresent("id=occupation"));
		selenium.type("id=occupation", "SQE");
		assertTrue(selenium.isElementPresent("id=processbutton"));
		selenium.click("id=processbutton");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Al Franken for Senate | Contribute".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Thanks -- your contribution has been processed!"));
		assertTrue(selenium.isElementPresent("link=Go to the home page"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
