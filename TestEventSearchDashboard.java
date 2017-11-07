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

public class TestEventSearchDashboard {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Constituent/Login?successurl=L3BhZ2UvZGFzaGJvYXJkL3ZpZXcvcHJpdmF0ZQ==&_h=ZwrTOOyhCP2B5OzFarI5t2-vpRk";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testEventSearchDashboard() throws Exception {
		selenium.open("http://demo.bluestatedigital.com/ctl/Constituent/Login?successurl=L3BhZ2UvZGFzaGJvYXJkL3ZpZXcvcHJpdmF0ZQ==&_h=ZwrTOOyhCP2B5OzFarI5t2-vpRk");
		selenium.type("id=username", "jwhitfield@bluestatedigital.com");
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("name=login_submit"));
		selenium.click("name=login_submit");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("James NotRequiredEmpOcc")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("My Events"));
		assertTrue(selenium.isElementPresent("css=#widget_event2_widget > div.widget_header.container > div.widget_actions > form > select[name=\"action_link\"]"));
		selenium.select("css=#widget_event2_widget > div.widget_header.container > div.widget_actions > form > select[name=\"action_link\"]", "label=Find Events");
		assertTrue(selenium.isElementPresent("css=#widget_event2_widget > div.widget_header.container > div.widget_actions > form > button[name=\"submit\"]"));
		selenium.click("css=#widget_event2_widget > div.widget_header.container > div.widget_actions > form > button[name=\"submit\"]");
		selenium.waitForPageToLoad("60000");
		verifyTrue(selenium.isTextPresent("Search for an event near you..."));
		verifyTrue(selenium.isTextPresent("exact:Country:"));
		selenium.select("id=simple_country", "label=United States");
		verifyTrue(selenium.isTextPresent("Postal/Zip Code:"));
		assertTrue(selenium.isElementPresent("id=event_search_zip"));
		selenium.type("id=event_search_zip", "02111");
		verifyTrue(selenium.isTextPresent("Search Radius:"));
		assertTrue(selenium.isElementPresent("name=radius"));
		selenium.type("name=radius", "5");
		assertTrue(selenium.isElementPresent("id=radius_unit"));
		selenium.select("id=radius_unit", "label=miles");
		verifyTrue(selenium.isElementPresent("css=input[type=\"submit\"]"));
		selenium.click("css=input[type=\"submit\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Desktop | Find Results".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Events within 5 miles of Boston, MA".equals(selenium.getText("id=sectionheader"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals("Non Justo Ltd", selenium.getText("link=Non Justo Ltd"));
		selenium.click("link=Non Justo Ltd");
		selenium.waitForPageToLoad("60000");
		verifyEquals("The Details", selenium.getText("id=sectionheader"));
		verifyEquals("Non Justo Ltd (Business Meetings)", selenium.getText("css=div.title"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
