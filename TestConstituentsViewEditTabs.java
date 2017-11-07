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

public class TestConstituentsViewEditTabs {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminHome";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testConstituentsViewEditTabs() throws Exception {
		selenium.open("http://demo.bluestatedigital.com/ctl/Core/AdminLogin?m=success");
		assertEquals("BSD Demo Control Panel : Sign In", selenium.getTitle());
		verifyTrue(selenium.isTextPresent("Email"));
		selenium.type("id=email", "whit23@gmail.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "bsddemo23");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=Your Website");
		selenium.click("link=People & Lists");
		selenium.click("id=cons-search");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Search for People")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Clear this search"));
		selenium.click("link=Clear this search");
		verifyTrue(selenium.isTextPresent("Confirm"));
		verifyTrue(selenium.isTextPresent("exact:Are you sure you want to clear this search?"));
		assertTrue(selenium.isElementPresent("css=button[type=\"button\"]"));
		selenium.click("css=button[type=\"button\"]");
		verifyTrue(selenium.isTextPresent("Your Search"));
		assertTrue(selenium.isElementPresent("id=query_type_and"));
		selenium.click("id=query_type_and");
		assertEquals("on", selenium.getValue("id=query_type_and"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Basic Information »")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Basic Information »");
		selenium.click("link=first name");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Adding Criterion")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("First Name"));
		selenium.select("name=type", "label=is exactly");
		selenium.type("id=search", "Alicia");
		selenium.click("css=button.default");
		verifyTrue(selenium.isElementPresent("link=last name"));
		selenium.click("link=last name");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Adding Criterion")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Last Name"));
		selenium.select("name=type", "label=is exactly");
		selenium.type("id=search", "Smith");
		assertTrue(selenium.isElementPresent("css=button.default"));
		selenium.click("css=button.default");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Found 49 unique constituents match with 51 unique email addresses, of which 51 are subscribed to receive email.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("css=input.stateListener-not-empty"));
		selenium.click("css=input.stateListener-not-empty");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
