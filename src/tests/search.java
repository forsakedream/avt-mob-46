package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.WebElement;

import java.util.List;

public class search extends CoreTestCase {

    private SearchPageObject SearchPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }

    public void testElementHasText()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchLineHasText();
    }

    public void testCancelSearch()
    {
        String SEARCH_VALUE = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_VALUE);
        List<WebElement> elements = SearchPageObject.getSearchResults(SEARCH_VALUE);
        assertTrue("Search result count <=1 !", elements.size() > 1);
        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForSearchResultsDisappear();
    }

    public void testSearchResultsContainsText()
    {
        String SEARCH_VALUE = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_VALUE);
        List<WebElement> elements = SearchPageObject.getSearchResults(SEARCH_VALUE);
        for (WebElement element : elements){
            boolean isContains = element.getText().toLowerCase().contains(SEARCH_VALUE.toLowerCase());
            assertTrue("Java is not found in search result!", isContains);
        }
    }

    public void testSearchResultsContainsResults()
    {
        String SEARCH_VALUE = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_VALUE);
        assertTrue("There less than 3 search result!", SearchPageObject.getSearchResultsAmount(SEARCH_VALUE) >=3);
        SearchPageObject.waitForElementByTitleAndDescription("Java", "sland");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }

}