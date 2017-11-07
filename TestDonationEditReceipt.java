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

public class TestDonationEditReceipt {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationEditReceipt() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Your Website");
		selenium.click("id=website-pages-donation");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Donation Pages".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isElementPresent("link=Edit contribution receipt"));
		selenium.click("link=Edit contribution receipt");
		selenium.selectWindow("name=contribution_receipt");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Edit Contribution Receipt Page".equals(selenium.getText("css=#content > h1"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Edit Contribution Receipt Page".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=page_content", "<a href=\"/page/receipt/logout\" class=\"logout_link\">Logout</a><br />\n%%download_link%%\n<h2>Your Donation History</h2>\n%%donor_name%%, (%%donor_email%%)<br />\n%%donor_address%%\n    <div class=\"receipt_filter\">\n        \n        <div class=\"preset\" style=\"float: left; width: 45%;\">\n            %%preset_date_filter%%\n        </div>\n        <div class=\"preset\" style=\"float: right; width: 45%;\">\n            %%custom_date_filter%%\n        </div>\n        \n    </div>\n<div style=\"display: block\"><p style=\"float: left\">%%date_range%%</p>  <p style=\"float: right\">Total tax $$$ deductible amount given in this time period as follows: <strong>%%total_amount%%</strong></p> </div>\n\n<div style=\"display: block; clear: both;\">\n %%contributions%%\n</div>");
		selenium.type("id=page_content_download", "<h2>Your Contribution Donation History</h2>\n%%donor_name%%, (%%donor_email%%)<br />\n%%donor_address%%\n\n<div style=\"display: block\">\n  <p style=\"float: left\">%%date_range%%</p>\n  <p style=\"float: right\">Total tax deductible amount given in this time period: <strong>%%total_amount%%</strong></p>\n</div>\n\n<div style=\"display: block; clear: both;\">\n %%contributions%%\n</div>");
		selenium.click("name=save");
		selenium.waitForPageToLoad("60000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
