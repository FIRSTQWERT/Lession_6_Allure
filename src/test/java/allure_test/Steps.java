package allure_test;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Steps {
    @Step("Открываем главную страницу {site}")
    public void openPage(String site){
        open(site);
    }
    @Step("Ищем репозиторий {repoName}")
    public void searchRepo(String repoName){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repoName);
        $(".header-search-input").submit();
    }
    @Step("Переходим в репозиторий {repoName}")
    public void goToRepo(String repoName){
        $(By.linkText(repoName)).click();
    }
    @Step("Открываем таб Issues в репозитории")
    public void openIssue(){
        $(withText("Issues")).click();
    }
    @Step("Проверяем что Issue с номером {issueNumber} существует")
    public void checkIssue(int issueNumber){
        $(withText("#" + issueNumber)).should(Condition.exist);
    }
}
