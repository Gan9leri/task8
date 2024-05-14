package tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class JavaRushTests extends TestBase {
    @DisplayName("Тестирование зависимости URL от выбранного языка")
    @CsvFileSource(resources = "/test_data.csv")
    @ParameterizedTest()
    void languageTest(String language, String link) {
        open("https://javarush.com/");
        $(".language-switcher-item").scrollIntoView(true).click();
        $(".language-switcher").$(byText(language)).click();
        webdriver().shouldHave(currentFrameUrl(link));
    }
    static Stream<Arguments> sectionsForumTest(){
        return Stream.of( Arguments.of( List.of(
                "О JavaRush", "Как пользоваться курсом", "Отзывы", "FAQ", "Контакты", "Оферта") ) );
    }
    @DisplayName("Тестирование доступности разделов форума")
    @MethodSource
    @ParameterizedTest
    void sectionsForumTest(List<String> value) {
        open("https://javarush.com/about/reviews");
        for (String item : value) {
            $(".tabs__list").$$("li").filter(visible).find(text(item)).click();
        }
    }
}