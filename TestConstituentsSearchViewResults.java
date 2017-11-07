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

public class TestConstituentsSearchViewResults {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminHome";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testConstituentsSearchViewResults() throws Exception {
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
			try { if ("BSD Demo Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Your Website");
		selenium.click("link=People & Lists");
		selenium.click("id=cons-search");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Search for People")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Clear this search")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Clear this search");
		verifyTrue(selenium.isTextPresent("Confirm"));
		verifyTrue(selenium.isTextPresent("exact:Are you sure you want to clear this search?"));
		assertTrue(selenium.isElementPresent("css=button[type=\"button\"]"));
		selenium.click("css=button[type=\"button\"]");
		verifyTrue(selenium.isTextPresent("Your Search"));
		assertTrue(selenium.isElementPresent("id=query_type_and"));
		selenium.click("id=query_type_and");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Clear this search")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Clear this search");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Confirm")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("exact:Are you sure you want to clear this search?"));
		assertTrue(selenium.isElementPresent("css=button[type=\"button\"]"));
		selenium.click("css=button[type=\"button\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Basic Information »")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Basic Information »");
		assertTrue(selenium.isElementPresent("link=last name"));
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
			try { if (selenium.isTextPresent("last name is exactly Smith")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Found 435 unique constituents match with 471 unique email addresses, of which 471 are subscribed to receive email.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Found 435 unique constituents match with 471 unique email addresses, of which 471 are subscribed to receive email."));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=input.stateListener-not-empty")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=input.stateListener-not-empty");
		selenium.click("css=#nav-home > span");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
