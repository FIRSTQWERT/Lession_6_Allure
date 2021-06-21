package allure_test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class Test_issues {

    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "FIRSTQWERT/simple_form_test";
    private static final int ISSUE_NUMBER = 1;
    private final Steps steps = new Steps();

    @Test
    public void testIssueSelenide() {
        open(BASE_URL);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        //$(".header-search-input").setValue(REPOSITORY).submit();
        $(By.linkText(REPOSITORY)).click();
        $(withText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
    }
    @Test
    public void testLambdaSteps() {
        step("Открываем главную страницу", () -> open(BASE_URL));
        step("Ищем репозиторий", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий", () -> $(By.linkText(REPOSITORY)).click());
        step("Открываем таб Issues в репозитории", () -> $(withText("Issues")).click());
        step("Проверяем что Issue с номером 1 существует", () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
        });
    }
    @Test
    public void testWithAnnotation() {
    steps.openPage(BASE_URL);
    steps.searchRepo(REPOSITORY);
    steps.goToRepo(REPOSITORY);
    steps.openIssue();
    steps.checkIssue(ISSUE_NUMBER);
    }
}
