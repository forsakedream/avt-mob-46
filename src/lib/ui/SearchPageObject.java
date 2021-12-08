package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    public static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']",
            SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[contains(@text,'{TITLE}')]/following-sibling::*[contains(@text, '{DESCRIPTION}')]",
            SEARCH_RESULTS_CONTAINER = "id:org.wikipedia:id/search_results_list",
            SEARCH_RESULT = "id:org.wikipedia:id/page_list_item_title",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";

    public SearchPageObject(AppiumDriver<WebElement> driver)
    {
        super(driver);
    }

    /* TEMPLATES_METHODS */
    private static String getResultSearchElementByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE}", substring);
    }

    //    Метод был добавлен при рефакторинге тестов в предыдущем коммите
    private static String getSearchResultByTitleAndDesc(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES_METHODS */

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT);
    }

    public void typeSearchLine(String value)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, value);
    }

    public void assertSearchLineHasText(){
        this.assertElementHasText(SEARCH_INPUT, "Search…",
                "This element has other text!");
    }

    public void waitForSearchResult(String value)
    {
        String search_result_xpath = getResultSearchElementByTitle(value);
        this.waitForElementPresent(search_result_xpath);
    }

    public void waitForSearchResults()
    {
        this.waitForElementPresent(SEARCH_RESULTS_CONTAINER);
    }

    public void waitForSearchResultsDisappear()
    {
        this.waitForElementNotPresent(SEARCH_RESULTS_CONTAINER);
    }

    public List<WebElement> getSearchResults()
    {
        waitForSearchResults();
        By by = getLocatorByString(SEARCH_RESULT);
        return driver.findElements(by);
    }

    public void clearSearchLine()
    {
        this.waitForElementAndClear(SEARCH_INPUT);
    }

    public void waitForSearchCancelButtonAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON);

    }

    public void waitForSearchCancelButtonDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON);

    }

    public void clickCancelSearch()
    {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON);
    }

    public void clickSearchResultByTitle(String title)
    {
        String search_result_xpath = getResultSearchElementByTitle(title);
        this.waitForElementAndClick(search_result_xpath);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDesc(title, description);
        return this.waitForElementPresent(search_result_xpath);
    }

    public void clickSearchResultByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDesc(title, description);
        this.waitForElementAndClick(search_result_xpath);
    }

    public void searchAndSelectArticle(String title){
        initSearchInput();
        typeSearchLine(title);
        clickSearchResultByTitle(title);
    }
    public void searchAndSelectArticle(String title, String description){
        initSearchInput();
        typeSearchLine(title);
        clickSearchResultByTitleAndDescription(title, description);
    }

    public int getSearchResultsAmount()
    {
        List<WebElement> elements = getSearchResults();
        return elements.size();
    }

}
