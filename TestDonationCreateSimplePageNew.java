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

public class TestDonationCreateSimplePageNew {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testDonationCreateSimplePageNew() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("glob:*Welcome to the Control Panel*"));
		selenium.click("link=Your Website");
		selenium.click("id=website-pages-donation");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Donation Pages".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Create a new donation page")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Create a new donation page");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Control Panel", selenium.getTitle());
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Use the tabs below to configure this contribution form as you want it to appear for your users.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("id=tab-general"));
		verifyTrue(selenium.isTextPresent("Page Name:"));
		assertTrue(selenium.isElementPresent("id=page_name"));
		selenium.type("id=page_name", selenium.getEval("'Production_GP_SFRP_ACVV'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Public Title:"));
		assertTrue(selenium.isElementPresent("id=public_title"));
		selenium.type("id=public_title", selenium.getEval("'Production_GP_SFRP_ACVV'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Page Url:"));
		assertTrue(selenium.isElementPresent("id=slugInput-textBox-value"));
		selenium.type("id=slugInput-textBox-value", selenium.getEval("'production_gp_sfrp_acvv_slug'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Credit Card Gateway:"));
		assertTrue(selenium.isElementPresent("id=gateway_id"));
		selenium.select("id=gateway_id", "label=Authnet_CVV");
		verifyTrue(selenium.isTextPresent("Amounts"));
		assertTrue(selenium.isElementPresent("id=tab-amounts"));
		selenium.click("id=tab-amounts");
		verifyTrue(selenium.isTextPresent("Simple"));
		assertTrue(selenium.isElementPresent("id=type-simple"));
		selenium.click("id=type-simple");
		assertEquals("on", selenium.getValue("id=type-simple"));
		verifyTrue(selenium.isTextPresent("Minimum Contribution:"));
		assertTrue(selenium.isElementPresent("name=amt_min"));
		selenium.type("name=amt_min", "100");
		verifyTrue(selenium.isTextPresent("Maximum Contribution:"));
		assertTrue(selenium.isElementPresent("name=amt_max"));
		selenium.type("name=amt_max", "10000");
		verifyTrue(selenium.isTextPresent("Preset Amounts:"));
		assertTrue(selenium.isElementPresent("name=amt_preset_1"));
		selenium.type("name=amt_preset_1", "150");
		assertTrue(selenium.isElementPresent("name=amt_preset_2"));
		selenium.type("name=amt_preset_2", "1250");
		assertTrue(selenium.isElementPresent("name=amt_preset_3"));
		selenium.type("name=amt_preset_3", "2500");
		assertTrue(selenium.isElementPresent("name=amt_preset_4"));
		selenium.type("name=amt_preset_4", "3750");
		assertTrue(selenium.isElementPresent("name=amt_preset_5"));
		selenium.type("name=amt_preset_5", "5250");
		assertTrue(selenium.isElementPresent("name=amt_preset_6"));
		selenium.type("name=amt_preset_6", "7500");
		assertTrue(selenium.isElementPresent("name=amt_preset_7"));
		selenium.type("name=amt_preset_7", "9500");
		verifyTrue(selenium.isTextPresent("Allow tax receipts?"));
		selenium.click("id=allow_tax_receipts");
		assertEquals("on", selenium.getValue("id=allow_tax_receipts"));
		verifyTrue(selenium.isTextPresent("Layout & Wrapper"));
		assertTrue(selenium.isElementPresent("id=tab-pagetext"));
		selenium.click("id=tab-pagetext");
		verifyTrue(selenium.isTextPresent("Page Layout:"));
		verifyTrue(selenium.isTextPresent("Above Form Content: (HTML allowed)"));
		selenium.select("name=layout_format", "label=One column");
		assertTrue(selenium.isElementPresent("id=page_html_header"));
		selenium.type("id=page_html_header", "<table border=\"0\" width = \"850\">   <tbody><tr>     <td><img src=\"http://www.clintonbushhaitifund.org/page/-/Donate/CBHF_2064.jpg\" alt=\"\" width=\"413\" height=\"290\" align=\"right\" /><br /><h2>Support Haiti Relief and Recovery Efforts</h2>  The survivors of the devastating earthquake in Haiti are in a desperate situation. The main sources of income for Haiti's poorest people have been damaged or destroyed. Now we need to help them rebuild, and this time build back better.<br /><br />  What we do right now determines how many lives we save. Together, we can help communities get back on their feet and achieve their full potential.</td>   </tr> </tbody></table>");
		verifyTrue(selenium.isTextPresent("Below Form Content: (HTML allowed)"));
		assertTrue(selenium.isElementPresent("id=page_html_footer"));
		selenium.type("id=page_html_footer", "<br> <strong>If you would prefer to contribute by mail, send a check to:</strong> <blockquote> The Clinton Bush Haiti Fund <br> P.O. Box 632454<br>  Baltimore, MD 21263-2454<br><br>  <b>Mobile Giving</b>  Text the word &quot;QUAKE&quot; to 20222 to donate ${10} to the Clinton Bush Haiti Fund. This donation charges to your cell phone bill. <br><br> A one-time donation of ${10}.00 will be added to your mobile phone bill or deducted from your prepaid balance. Msg&amp;Data Rates May Apply. All charges are billed by and payable to your mobile service provider. Service is available through the following wireless operators: AT&amp;T, Nextel, Sprint, T-Mobile USA, U.S. Cellular, Cricket Communications, Cincinnati Bell, Cellular South and Verizon Wireless. Donations are collected for the benefit of the Clinton Bush Haiti Fund by the Mobile Giving Foundation and subject to the terms found at www.hmgf.org/t. You can unsubscribe at any time by replying STOP to short code 20222; Reply HELP to 20222 for help. </blockquote>");
		verifyTrue(selenium.isTextPresent("Compliance"));
		assertTrue(selenium.isElementPresent("id=tab-compliance"));
		selenium.click("id=tab-compliance");
		verifyTrue(selenium.isTextPresent("Require Employer and Occupation Information:"));
		selenium.select("name=show_employment", "label=Yes");
		assertTrue(selenium.isElementPresent("id=employment_text"));
		selenium.type("id=employment_text", "Federal regulations require that all contributors provide their employment information. If you are not employed, enter 'none' in the occupation and employer boxes.");
		verifyTrue(selenium.isTextPresent("Require Acknowledgement of Legal Text:"));
		selenium.select("name=show_legal", "label=Yes");
		verifyTrue(selenium.isTextPresent("Text To Acknowledge:"));
		assertTrue(selenium.isElementPresent("id=legal_text"));
		selenium.type("id=legal_text", "<strong>Check this box to confirm that the following statements are true and accurate:</strong> <ol> <li>I am a United States citizen or permanent resident alien.</li> <li>This contribution is not made from the general treasury funds of a corporation, labor organization or national bank.</li> <li>This contribution is not made from the treasury of an entity or person who is a federal contractor.</li> <li>The funds I am donating are not being provided to me by another person or entity for the purpose of making this contribution.</li> </ol>");
		verifyTrue(selenium.isTextPresent("Options"));
		assertTrue(selenium.isElementPresent("id=tab-other"));
		selenium.click("id=tab-other");
		verifyTrue(selenium.isTextPresent("Notify Email Addresses:"));
		assertTrue(selenium.isElementPresent("name=notification_email"));
		selenium.type("name=notification_email", "jwhitfield@bluestatedigital.com,amy@bluestatedigital.com,jyuska@bluestatedigital.com,lhaiduck@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Thank You Page"));
		assertTrue(selenium.isElementPresent("id=tab-thankspage"));
		selenium.click("id=tab-thankspage");
		verifyTrue(selenium.isTextPresent("shown a simple thank you page."));
		selenium.click("name=thanks_type");
		verifyTrue(selenium.isTextPresent("Thank You Message:"));
		assertTrue(selenium.isElementPresent("id=page_html_thanks"));
		selenium.type("id=page_html_thanks", "Simple Donation Processed Successfully via Authnet!!!   Thank you!!!  A confirmation receipt will be emailed to you shortly.    Your generosity will promote jobs and create economic opportunities that will lead to long-term growth and prosperity for Haiti.  The Clinton Bush Haiti Fund is committed to being responsible stewards of every dollar you donate. We have specific requirements for reporting and auditing to ensure that every dollars is fully accounted for and has the best chance of producing long-lasting results in Haiti. You can check in on our progress toward investing your dollars anytime on http://www.ClintonBushHaitiFund.org/programs.   If you would like your company to match your gift, please request and complete a \"Matching Gift Form\" from your company. Our EIN is 27-2122785. After you have completed the form, please mail or email it to the Clinton Bush Haiti Fund office:   Clinton Bush Haiti Fund  Suite 380  1501 K Street, NW  Washington, DC 20005 contacts@ClintonBushHaitiFund.org");
		verifyTrue(selenium.isTextPresent("Thank You Email"));
		assertTrue(selenium.isElementPresent("id=tab-thanksemail"));
		selenium.click("id=tab-thanksemail");
		verifyTrue(selenium.isTextPresent("Send Thank You Email:"));
		selenium.select("name=send_thanks_email", "label=No");
		selenium.select("name=send_thanks_email", "label=Yes");
		verifyTrue(selenium.isTextPresent("Sender Email Address:"));
		assertTrue(selenium.isElementPresent("name=thanks_email_from_email"));
		selenium.type("name=thanks_email_from_email", "jwhitfield_production_gp_tests@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Sender Name:"));
		assertTrue(selenium.isElementPresent("name=thanks_email_from_name"));
		selenium.type("name=thanks_email_from_name", "Production Automated GP Testing");
		verifyTrue(selenium.isTextPresent("Subject:"));
		assertTrue(selenium.isElementPresent("name=thanks_email_subject"));
		selenium.type("name=thanks_email_subject", "Production GP Donation Tests!!!");
		verifyTrue(selenium.isTextPresent("Message Body:"));
		assertTrue(selenium.isElementPresent("name=thanks_email_body_plain"));
		selenium.type("name=thanks_email_body_plain", "Dear %%firstname%%, Thank you for your contribution...");
		verifyTrue(selenium.isTextPresent("In Honor of"));
		assertTrue(selenium.isElementPresent("id=tab-honoree"));
		selenium.click("id=tab-honoree");
		verifyTrue(selenium.isTextPresent("Send Honoree Email:"));
		selenium.select("name=send_honoree_email", "label=Yes");
		verifyTrue(selenium.isTextPresent("Explanatory Text for Honoree Fields: (HTML allowed)"));
		assertTrue(selenium.isElementPresent("id=honoree_text"));
		selenium.type("id=honoree_text", "To make your contribution in somebody's honor, fill in the information below. He or she (or, in the case of an <em>in memoriam</em> contribution, the recipient you specify) will be notified of your thoughtfulness and generosity.");
		verifyTrue(selenium.isTextPresent("Sender Email Address:"));
		assertTrue(selenium.isElementPresent("name=honoree_email_from_email"));
		selenium.type("name=honoree_email_from_email", "jwhitfield+honoree@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Sender Name:"));
		assertTrue(selenium.isElementPresent("name=honoree_email_from_name"));
		selenium.type("name=honoree_email_from_name", "James M. Whitfield (Honoree)");
		verifyTrue(selenium.isTextPresent("In Honor of..."));
		verifyTrue(selenium.isTextPresent("Subject:"));
		assertTrue(selenium.isElementPresent("name=honoree_email_subject"));
		selenium.type("name=honoree_email_subject", "A generous contribution has been made in your honor.");
		assertTrue(selenium.isElementPresent("name=honoree_email_body_plain"));
		selenium.type("name=honoree_email_body_plain", "Dear %%honoree%%, %%contributor%% has just made a contribution in your honor of %%amount_formatted%%.");
		verifyTrue(selenium.isTextPresent("In Memory of..."));
		assertTrue(selenium.isElementPresent("name=honoree_email_subject_memoriam"));
		selenium.type("name=honoree_email_subject_memoriam", "A generous contribution has been made in loving memory of %%honoree%%.");
		assertTrue(selenium.isElementPresent("name=honoree_email_body_plain_memoriam"));
		selenium.type("name=honoree_email_body_plain_memoriam", "Dear %%honoree%%, %%contributor%% has just made a contribution in your honor of %%amount_formatted%%.");
		assertTrue(selenium.isElementPresent("name=button_group[submit_button]"));
		selenium.click("name=button_group[submit_button]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Control Panel | Donation Pages".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
