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

public class TestMailingCreateViaCloning {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://secure.clintonbushhaitifund.org/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingCreateViaCloning() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Email");
		selenium.click("id=email-all");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Manage Mailings - BSD Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("".equals(selenium.getText("name=simpleQuery"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("name=simpleQuery", "1-Production_GP_Cloned_Mailing_New1692");
		selenium.click("id=simpleSearch-button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("CLONE".equals(selenium.getText("//div[@id='search-tab']/table/tbody/tr/td/div/a[3]/span"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("//div[@id='search-tab']/table/tbody/tr/td/div/a[3]/span");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Maillist Edit BSD Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("name=mailing_name")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("name=mailing_name", selenium.getEval("'1-Production_GP_Cloned_Mailing_New'+Math.floor(Math.random()*100000)"));
		selenium.type("id=description", "Production Guinea Pig Cloned Mailing Test...");
		assertTrue(selenium.isElementPresent("//input[@value='Save and NEXT »']"));
		selenium.click("//input[@value='Save and NEXT »']");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Mailing Checklist")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("id=count_update_link")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=count_update_link");
		verifyTrue(selenium.isTextPresent("Recipients: Members of 1-Recipients_Production_Mailing_QA"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("2 recipients")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Email");
		selenium.click("id=email-all");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("glob:*1-Production_GP_Cloned_Mailing_New*")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("id=draft-tab-link")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=draft-tab-link");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
