package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.openqa.selenium.WebElement;

import java.util.List;

public class search extends CoreTestCase {

    private SearchPageObject SearchPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        SearchPageObject = new SearchPageObject(driver);
    }

    public void testElementHasText()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchLineHasText();
    }

    public void testCancelSearch()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        List<WebElement> elements = SearchPageObject.getSearchResults();
        assertTrue("Search result count <=1 !", elements.size() > 1);
        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForSearchResultsDisappear();
    }

    public void testSearchResultsContainsText()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        List<WebElement> elements = SearchPageObject.getSearchResults();
        for (WebElement element : elements){
            boolean isContains = element.getText().contains("Java");
            assertTrue("Java is not found in search result!", isContains);
        }
    }

    public void testSearchResultsContainsResults()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        assertTrue("There less than 3 search result!", SearchPageObject.getSearchResultsAmount() >=3);
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }

}