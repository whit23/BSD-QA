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

public class TestDonationCreateCyberSourceGateway {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://change-this-to-the-site-you-are-testing/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationCreateCyberSourceGateway() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=BSD Demo");
		selenium.click("id=account-bsd-admin");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Manage gateways")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Manage gateways");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("id=addGateway")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=addGateway");
		selenium.waitForPageToLoad("60000");
		selenium.type("id=gatewayName", selenium.getEval("'Regression_Gateway_Test_CCVV'+Math.floor(Math.random()*10000)"));
		selenium.select("id=gatewayType", "label=CyberSource");
		selenium.type("id=stdConfig-CyberSource-0-merchant_id", "bluestatetest");
		selenium.type("id=stdConfig-CyberSource-0-transaction_key", "yPL2H9Zl7eheOSVx82uPZHiNUsNG4H+4ImDDO2t0KIz+0XHUwjvMJEOGNYamR52p0tcpQuFKwnr8+hDaT3MtrphQWfPE4DanCfYTAztfEcqAGJI7yVDNjZJj78TkefjJ4yAMq3Q73XHwXhKWrV+/x4EZaehLGIB6edVcsO6x18gSELBssav23tYwGnHza49keI1Sw0bgf7giYMM7a3QojP7RcdTCO8wkQ4Y1hqZHnanS1ylC4UrCevz6ENpPcy2umFBZ88TgNqcJ9hMDO18RyoAYkjvJUM2NkmPvxOR5+MnjIAyrdDvdcfBeEpatX7/HgRlp6EsYgHp51Vyw7rHXyA==");
		selenium.click("id=gatewayPaymentOptions-CyberSource-0_0");
		selenium.click("id=gatewayPaymentOptions-CyberSource-0_1");
		selenium.click("id=gatewayPaymentOptions-CyberSource-0_2");
		selenium.click("id=gatewayPaymentOptions-CyberSource-0_3");
		selenium.click("link=Advanced options");
		selenium.click("id=gatewayDebugMode_0");
		selenium.click("id=addConfig-CyberSource-cvv_enabled_0");
		selenium.click("css=#continue > span");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Pass")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=#back_to_manage > span")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=#back_to_manage > span");
		selenium.waitForPageToLoad("60000");
		assertEquals("Manage Gateway Configurations", selenium.getTitle());
		verifyTrue(selenium.isElementPresent("link=glob:*Regression_Gateway_Test_CCVV*"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
