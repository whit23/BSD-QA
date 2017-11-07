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

public class TestDonationCreateCampaign {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationCreateCampaign() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Your Website");
		selenium.click("id=website-pages-donation");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Donation Pages".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("id=campaign-tab-link")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=campaign-tab-link");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Control Panel | Fundraising Campaigns", selenium.getTitle());
		verifyTrue(selenium.isElementPresent("link=Create a new campaign"));
		selenium.click("link=Create a new campaign");
		selenium.waitForPageToLoad("60000");
		verifyEquals("BSD Control Panel", selenium.getTitle());
		verifyEquals("General", selenium.getText("id=tab-general-button"));
		selenium.type("name=name", selenium.getEval("'Prod_Donation_Campaign'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isElementPresent("id=editslug"));
		selenium.type("id=editslug", selenium.getEval("'donation_campaign'+Math.floor(Math.random()*10000)"));
		selenium.type("name=description", "Production Fundraising Campaign Test...");
		selenium.click("link=Contribution Data");
		selenium.addSelection("id=contribution_page_ids", "label=2009 06 22 Fundraising Campaign");
		selenium.click("link=Data Offset");
		selenium.sendKeys("name=offset_amt", "100.00");
		selenium.sendKeys("name=offset_contributors", "50");
		selenium.sendKeys("name=offset_contributions", "50");
		selenium.select("name=offset_auto_enabled", "label=Yes");
		selenium.click("link=Goal");
		selenium.select("name=goal_type", "label=Amount Raised");
		selenium.sendKeys("name=goal", "100000");
		selenium.click("link=Text/XML (Advanced)");
		selenium.click("name=submit_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Fundraising Campaigns".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
