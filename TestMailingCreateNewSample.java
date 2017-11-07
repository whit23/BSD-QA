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

public class TestMailingCreateNewSample {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminHome";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingCreateNewSample() throws Exception {
		selenium.open("http://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success");
		verifyTrue(selenium.isTextPresent("Email"));
		assertTrue(selenium.isElementPresent("id=email"));
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
		selenium.click("link=Email");
		selenium.click("id=email-create-new");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Create a new mailing")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Create a new mailing");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Create Mailing")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("exact:*Mailing Name"));
		assertTrue(selenium.isElementPresent("name=mailing_name"));
		selenium.type("name=mailing_name", selenium.getEval("'1-Production_GP_Mailing_Sample_New'+Math.floor(Math.random()*100000)"));
		verifyTrue(selenium.isTextPresent("exact:*Mailing Template"));
		selenium.select("id=mailing_template_id", "label=Default Template");
		verifyTrue(selenium.isTextPresent("Description"));
		selenium.type("id=description", "Production Guinea Pig Sample Mailing Test...");
		verifyTrue(selenium.isTextPresent("Send this Mailing to"));
		verifyTrue(selenium.isTextPresent("Members of a Constituent Group"));
		assertTrue(selenium.isElementPresent("id=to_cons_group"));
		selenium.click("id=to_cons_group");
		assertTrue(selenium.isElementPresent("css=input.bsd-blue-button"));
		selenium.selectWindow("null");
		selenium.click("css=input.bsd-blue-button");
		verifyTrue(selenium.isTextPresent("Filter group list:"));
		verifyTrue(selenium.isTextPresent("Constituent groups:"));
		verifyTrue(selenium.isElementPresent("css=div.cons_group_search"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-Recipients_Production_Mailing_QA")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=td");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("//div[@id='cons_group_finder_overlay']/div/div/div/div/table/tbody/tr[2]/td")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("exact:*From"));
		selenium.select("id=from_mailing_sender_id", "label=James M. Whitfield <jwhitfield@bluestatedigital.com>");
		verifyTrue(selenium.isTextPresent("exact:*Subject"));
		selenium.type("id=subject", "Subject: Production Guinea Pig Sample Mailing Test...");
		selenium.click("id=wysiwygedit-label-body_html");
		selenium.type("id=body_html", "Body: Production Guinea Pig Sample Mailing Test...");
		selenium.click("id=wysiwygedit-label-body_html");
		selenium.click("xpath=(//button[@type='button'])[3]");
		assertTrue(selenium.isElementPresent("//input[@value='Save and NEXT »']"));
		selenium.click("//input[@value='Save and NEXT »']");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Mailing Checklist")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("id=count_update_link"));
		selenium.click("id=count_update_link");
		verifyTrue(selenium.isTextPresent("Recipients: Members of 1-Recipients_Production_Mailing_QA"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("4 recipients")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isElementPresent("link=Email"));
		selenium.click("link=Email");
		verifyTrue(selenium.isElementPresent("id=email-all"));
		selenium.click("id=email-all");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Manage Mailings - BSD Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-Production_GP_Mailing_Sample_New")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
