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

public class TestSignupCreateNew {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSignupCreateNew() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		verifyTrue(selenium.isTextPresent("Email"));
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=Your Website");
		selenium.click("id=website-pages-signup");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Manage Signup Forms")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Create new signup form"));
		selenium.click("link=Create new signup form");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Create a new signup form")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Create a new signup form");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Create Signup Form Step 1 of 2: Edit Basic Information")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Form Name:"));
		assertTrue(selenium.isElementPresent("name=signup_form_name"));
		selenium.type("name=signup_form_name", selenium.getEval("'Production_Signup_Form'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("Public Title:"));
		assertTrue(selenium.isElementPresent("id=form_public_title"));
		selenium.type("id=form_public_title", selenium.getEval("'Production_Signup_Title'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("Form Slug:"));
		assertTrue(selenium.isElementPresent("id=slugInput-textBox-value"));
		selenium.type("id=slugInput-textBox-value", selenium.getEval("'production_signup_slug'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("HTML - Above Form:"));
		assertTrue(selenium.isElementPresent("id=form_html_top"));
		selenium.type("id=form_html_top", "PRoduction_Signup: HTML-Above Form");
		verifyTrue(selenium.isTextPresent("HTML - Below Form:"));
		selenium.type("id=form_html_bottom", "Production_Signup: HTML-Below Form");
		verifyTrue(selenium.isTextPresent("Thank You Options"));
		selenium.select("id=thank_you_type", "label=Display thank you message");
		assertTrue(selenium.isElementPresent("id=thank_you_html"));
		selenium.type("id=thank_you_html", "ProductioHTML Thank You Page");
		verifyTrue(selenium.isTextPresent("Email Signup Details to:"));
		assertTrue(selenium.isElementPresent("name=email_copy"));
		selenium.type("name=email_copy", "jwhitfield@bluestatedigital.com");
		assertTrue(selenium.isElementPresent("css=td.field > input[type=\"submit\"]"));
		selenium.click("css=td.field > input[type=\"submit\"]");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Edit Signup Form Step 2 of 2: Edit Fields")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Manage Signup Forms"));
		selenium.click("link=Manage Signup Forms");
		selenium.waitForPageToLoad("60000");
		verifyTrue(selenium.isTextPresent("Production_Signup_Form"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
