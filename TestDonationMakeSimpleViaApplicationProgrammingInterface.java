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

public class TestDonationMakeSimpleViaApplicationProgrammingInterface {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://secure.clintonbushhaitifund.org/page/contribute/xebium_ongoing_sfrp_ccvv_donation_page";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationMakeSimpleViaApplicationProgrammingInterface() throws Exception {
		selenium.open("https://test4.bluestatedigital.com/donate-api-contrib.html");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("AR: Donate API Contribution Page")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("exact:*Slug"));
		assertTrue(selenium.isElementPresent("name=slug"));
		selenium.type("name=slug", "donate_api_acvv");
		verifyTrue(selenium.isTextPresent("exact:*Country"));
		assertTrue(selenium.isElementPresent("id=country"));
		selenium.type("id=country", "US");
		verifyTrue(selenium.isTextPresent("exact:*First name"));
		assertTrue(selenium.isElementPresent("id=firstname"));
		selenium.type("id=firstname", "James");
		verifyTrue(selenium.isTextPresent("exact:*Last name"));
		assertTrue(selenium.isElementPresent("id=lastname"));
		selenium.type("id=lastname", "Whitfield");
		verifyTrue(selenium.isTextPresent("exact:*Address"));
		assertTrue(selenium.isElementPresent("id=addr1"));
		selenium.type("id=addr1", "255 East Street");
		assertTrue(selenium.isElementPresent("id=addr2"));
		verifyTrue(selenium.isTextPresent("exact:*City"));
		assertTrue(selenium.isElementPresent("id=city"));
		selenium.type("id=city", "Dedham");
		verifyTrue(selenium.isTextPresent("exact:*Zip"));
		assertTrue(selenium.isElementPresent("id=zip"));
		selenium.type("id=zip", "02026");
		verifyTrue(selenium.isTextPresent("exact:*State"));
		assertTrue(selenium.isElementPresent("id=state_cd"));
		selenium.select("id=state_cd", "label=MA");
		verifyTrue(selenium.isTextPresent("exact:*Email address"));
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", selenium.getEval("\"jwhitfield+new_apiuserdonation\"+ Math.floor(Math.random()*10000) + \"@bluestatedigital.com\";"));
		verifyTrue(selenium.isTextPresent("exact:*Phone number"));
		assertTrue(selenium.isElementPresent("id=phone"));
		selenium.type("id=phone", "6179476316");
		verifyTrue(selenium.isTextPresent("exact:*Amount"));
		assertTrue(selenium.isElementPresent("id=amt_other_text"));
		selenium.type("id=amt_other_text", "100.00");
		verifyTrue(selenium.isTextPresent("American Express"));
		verifyTrue(selenium.isTextPresent("Discover"));
		verifyTrue(selenium.isTextPresent("MasterCard"));
		verifyTrue(selenium.isTextPresent("Visa"));
		verifyTrue(selenium.isTextPresent("PayPal"));
		selenium.click("id=cc_vs");
		verifyTrue(selenium.isTextPresent("exact:*Card number"));
		assertTrue(selenium.isElementPresent("id=cc_number"));
		selenium.type("id=cc_number", "4007000000027");
		verifyTrue(selenium.isTextPresent("exact:*CVV"));
		assertTrue(selenium.isElementPresent("id=cc_cvv"));
		selenium.type("id=cc_cvv", "555");
		verifyTrue(selenium.isTextPresent("exact:*Expiration"));
		assertTrue(selenium.isElementPresent("id=cc_expir_month"));
		selenium.select("id=cc_expir_month", "label=12");
		assertTrue(selenium.isElementPresent("id=cc_expir_year"));
		selenium.select("id=cc_expir_year", "label=2016");
		verifyTrue(selenium.isTextPresent("exact:*Employer"));
		assertTrue(selenium.isElementPresent("id=employer"));
		selenium.type("id=employer", "WPP Digital");
		verifyTrue(selenium.isTextPresent("exact:*Occupation"));
		assertTrue(selenium.isElementPresent("id=occupation"));
		selenium.type("id=occupation", "SQE");
		assertTrue(selenium.isElementPresent("id=processbutton"));
		selenium.click("id=processbutton");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("\"api_version\":1,")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("\"status\":\"success\""));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
