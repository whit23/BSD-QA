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

public class TestEventSearchControlPanel {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testEventSearchControlPanel() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=Your Website");
		selenium.click("id=website-comm-events");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Search Events".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Event Type")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("name=event_type"));
		selenium.select("name=event_type", "label=BSD Regression Events");
		selenium.click("name=button_group[submit_button]");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Control Panel | Search Events", selenium.getTitle());
		selenium.select("id=csz_state", "label=MA");
		selenium.type("id=zip", "02111");
		verifyTrue(selenium.isTextPresent("Host Name"));
		assertTrue(selenium.isElementPresent("name=host_name"));
		selenium.select("name=create_date_start[M]", "label=Feb");
		selenium.select("name=create_date_start[d]", "label=01");
		selenium.select("name=create_date_start[Y]", "label=2004");
		selenium.select("name=create_date_end[M]", "label=Dec");
		selenium.select("name=create_date_end[d]", "label=31");
		verifyTrue(selenium.isTextPresent("Sort Results By"));
		assertTrue(selenium.isElementPresent("name=order_field"));
		selenium.select("name=order_field", "label=Event Date");
		assertTrue(selenium.isElementPresent("name=button_group[submit_button]"));
		selenium.click("name=button_group[submit_button]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.getText("css=p.total-count").matches("^[\\s\\S]*events found from your search[\\s\\S]*$")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
