import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void bandAddTest() {
    goTo("http://localhost:4567/bands");
    fill("#bandName").with("Neutral Milk Hotel");
    submit("#bandButton");
    assertThat(pageSource()).contains("Neutral Milk Hotel");
  }

  @Test
  public void venueAddTest() {
    goTo("http://localhost:4567/venues");
    fill("#venueName").with("White Eagle");
    fill("#venueLocation").with("Portland, OR");
    submit("#venueButton");
    assertThat(pageSource()).contains("White Eagle", "Portland, OR");
  }


  // @Test
  // public void bandUpdateTest() {
  //   Band testBand = new Band("Neutral Milk Hotel");
  //   testBand.save();
  //   String bandpage = String.format("http://localhost:4567/bands/%d/update", testBand.getId());
  //   goTo(bandpage);
  //   fill("#nameUpdate").with("Ty Segall");
  //   submit("#nameUpdate");
  //   assertThat(pageSource()).contains("Ty Segall");
  // }

}
