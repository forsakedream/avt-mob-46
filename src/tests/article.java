package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.openqa.selenium.By;

public class article extends CoreTestCase {

    private MainPageObject MainPageObject;
    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private MyListPageObject MyListPageObject;
    private NavigationUI NavigationUI;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        MyListPageObject = new MyListPageObject(driver);
        NavigationUI = new NavigationUI(driver);
    }

    public void testSwipeArticle()
    {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickSearchResultByTitle("Appium");
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.swipeUpToFooter();
    }

    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject.searchAndSelectArticle("Appium");
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.addArticleToNewList("Learning");
        ArticlePageObject.closeArticle();
        SearchPageObject.searchAndSelectArticle("Java", "Object-oriented programming language");
        ArticlePageObject.addArticleToExistingList("Learning");
        ArticlePageObject.closeArticle();
        NavigationUI.goToMyList();
        MyListPageObject.selectList("Learning");
        MyListPageObject.deleteArticleFromList("Appium");
        MyListPageObject.assertListHasArticle("Java (programming language)");
        MyListPageObject.clickArticleFromList("Java (programming language)");
        ArticlePageObject.assertArticleHasTitle("Java (programming language)");
    }

    public void testAssertTitle(){
        SearchPageObject.searchAndSelectArticle("Appium");
        MainPageObject.assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

}
