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

public class TestSignupSubmitForm {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/page/s/xebium_ongoing_signup_form";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSignupSubmitForm() throws Exception {
		selenium.open("http://demo.bluestatedigital.com/page/s/xebium_ongoing_signup_form");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Country")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.select("id=country", "label=United States");
		verifyTrue(selenium.isTextPresent("exact:Email*"));
		selenium.type("id=email", selenium.getEval("\"jwhitfield+signup\"+ Math.floor(Math.random()*10000) + \"@bluestatedigital.com\";"));
		verifyTrue(selenium.isTextPresent("First Name"));
		selenium.type("id=firstname", "James");
		verifyTrue(selenium.isTextPresent("Last Name"));
		selenium.type("id=lastname", selenium.getEval("'Whitfield_Signup'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Address"));
		selenium.type("id=addr1", "280 Summer Street");
		selenium.type("id=addr2", "Seventh Floor");
		verifyTrue(selenium.isTextPresent("City"));
		selenium.type("id=city", "Boston");
		verifyTrue(selenium.isTextPresent("State/Region/Province"));
		selenium.select("id=state_cd", "label=MA");
		verifyTrue(selenium.isTextPresent("exact:Postal Code*"));
		selenium.type("id=zip", "02210");
		verifyTrue(selenium.isTextPresent("Phone"));
		selenium.type("id=phone", "800-555-1212");
		verifyTrue(selenium.isTextPresent("Occupation"));
		selenium.type("id=occupation", "WPPGY");
		verifyTrue(selenium.isTextPresent("Employer"));
		selenium.type("id=employer", "SQE");
		assertTrue(selenium.isElementPresent("name=submit-btn"));
		selenium.click("name=submit-btn");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Thanks for signing up using the 'Xebium Ongoing Signup Form'!!!")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
