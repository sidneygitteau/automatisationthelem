package fr.thelem.automation.hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class PlaywrightHooks {

    public static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;

    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @After
    public void tearDown() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
