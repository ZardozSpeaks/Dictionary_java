import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public ClearRule clearRule = new ClearRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Dictionary Creator");
  }

  //WORD TESTS//

  @Test
  public void wordIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#word").with("Cromulent");
    submit(".btn");
    assertThat(pageSource()).contains("Cromulent");
  }


//DEFINITION TESTS//

  @Test
  public void definitionFormisDisplayed() {
    goTo("http://localhost:4567/");
    fill("#word").with("Embiggen");
    submit(".btn");
    click("a", withText("Embiggen"));
    assertThat(pageSource()).contains("Add a New Definition:");
  }
  @Test
  public void definitionIsAddedAndDisplayed() {
    goTo("http://localhost:4567/");
    fill("#word").with("Embiggen");
    submit(".btn");
    click("a", withText("Embiggen"));
    fill("#text").with("(verb) - A most cromulent word");
    submit(".btn");
    assertThat(pageSource()).contains("(verb) - A most cromulent word");
    }

}
