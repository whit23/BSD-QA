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

public class TestDashboardRegisterNewUser {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminHome";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDashboardRegisterNewUser() throws Exception {
		selenium.open("http://demo.bluestatedigital.com/ctl/Constituent/Login?successurl=L3BhZ2UvZGFzaGJvYXJkL3ZpZXcvcHJpdmF0ZQ==&_h=ZwrTOOyhCP2B5OzFarI5t2-vpRk");
		assertTrue(selenium.isElementPresent("id=firstname"));
		selenium.type("id=firstname", "New_GP_Dashboard");
		assertTrue(selenium.isElementPresent("id=lastname"));
		selenium.type("id=lastname", selenium.getEval("'Registration'+ Math.floor(Math.random()*10000)"));
		assertTrue(selenium.isElementPresent("id=zip"));
		selenium.type("id=zip", "02210");
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", selenium.getEval("\"jwhitfield+gp_newdbuser\"+ Math.floor(Math.random()*10000) + \"@bluestatedigital.com\";"));
		assertTrue(selenium.isElementPresent("id=password1"));
		selenium.type("id=password1", "password");
		assertTrue(selenium.isElementPresent("id=password2"));
		selenium.type("id=password2", "password");
		assertTrue(selenium.isElementPresent("name=signup_submit"));
		selenium.click("name=signup_submit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Account successfully created. You are now logged in.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("New_GP_Dashboard Registration")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Logout");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("You have been logged out.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Login: If you already have an account...")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
