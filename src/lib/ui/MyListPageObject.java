package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject{

    protected static String LIST_TITLE, ARTICLE_TITLE_TPL, ARTICLE_TITLE;

    public MyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES_METHODS */
    protected static String getArticleElementByTitle(String substring)
    {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", substring);
    }
    /* TEMPLATES_METHODS */


    public void selectList(String list)
    {
        this.waitForElementContainsTextAndCLick(LIST_TITLE, list);
    }

    abstract public void deleteArticleFromList(String article);

    public void assertListHasArticle(String article)
    {
        this.assertElementHasText(ARTICLE_TITLE, article);
    }

    public void clickArticleFromList(String article)
    {
        String articleLocator = getArticleElementByTitle(article);
        this.waitForElementAndClick(articleLocator);
    }
}
