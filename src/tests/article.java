package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class article extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private MyListPageObject MyListPageObject;
    private NavigationUI NavigationUI;

    protected void setUp() throws Exception
    {
        super.setUp();

        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
        NavigationUI = NavigationUIFactory.get(driver);
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
        if  (Platform.getInstance().isIOS()) {ArticlePageObject.skipTutorial();}
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.addArticleToNewList("Learning");
        ArticlePageObject.closeArticle();
        SearchPageObject.searchAndSelectArticle("Java", "Object-oriented programming language");
        ArticlePageObject.waitForArticleTitle();
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
        ArticlePageObject.assertTitlePresent();
    }

}
