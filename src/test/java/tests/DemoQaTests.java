package tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests extends TestBase {
    @ValueSource(strings = {"Alex", "Mike", "Fred"})
    @DisplayName("Тест поля Full Name")
    @ParameterizedTest(name = "Тест поля Full Name на примере {0}")
    void textBoxFullNameFieldTest(String value) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(value);
        $("#submit").scrollIntoView(true).click();
        $("#output").shouldHave(text("Name:" + value));
    }
}