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

public class TestDonationMakeSimpleViaGatewayPayPalWebsitePaymentPro {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://secure.clintonbushhaitifund.org/page/contribute/xebium_ongoing_sfrp_ccvv_donation_page";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationMakeSimpleViaGatewayPayPalWebsitePaymentPro() throws Exception {
		selenium.open("https://donate.naacp.org/page/contribute/bdonatefooter?nomin=1");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Your Information")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=firstname", "James");
		selenium.type("id=lastname", "Whitfield");
		selenium.type("id=addr1", "255 East Street");
		selenium.type("id=city", "Dedham");
		selenium.select("id=state_cd", "label=MA");
		selenium.type("id=zip", "02026");
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		selenium.type("id=phone", "617-947-6316");
		selenium.click("id=amt_other");
		assertEquals("on", selenium.getValue("id=amt_other"));
		selenium.type("id=amt_other_text", ".01");
		selenium.click("id=cc_vs");
		assertEquals("on", selenium.getValue("id=cc_vs"));
		selenium.type("id=cc_number", "4117704047090047");
		selenium.select("id=cc_expir_month", "label=12");
		selenium.select("id=cc_expir_year", "label=2018");
		selenium.type("id=cc_cvv", "977");
		selenium.click("id=processbutton");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Quick Donate Opt In".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Your donation was successful. Thank you."));
		verifyTrue(selenium.isElementPresent("id=BSD-qd_optin-skip"));
		selenium.click("id=BSD-qd_optin-skip");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("NAACP | Thank you for donating!".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Thank you for donating!".equals(selenium.getText("css=h1"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
