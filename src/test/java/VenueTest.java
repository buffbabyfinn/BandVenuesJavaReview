import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Venue firstVenue = new Venue("Mississippi Studios", "Portland, OR");
    Venue secondVenue = new Venue("Mississippi Studios", "Portland, OR");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesIntoDatabase() {
    Venue firstVenue = new Venue("Mississippi Studios", "Portland, OR");
    firstVenue.save();
    assertTrue(Venue.all().get(0).equals(firstVenue));
  }

  @Test
  public void all_returnsAllVenue() {
    Venue firstVenue = new Venue("Mississippi Studios", "Portland, OR");
    firstVenue.save();
    assertEquals(Venue.all().size(), 1);
  }

  @Test
  public void find_findVenueInDatabase() {
  Venue firstVenue = new Venue("Mississippi Studios", "Portland, OR");
  firstVenue.save();
  Venue savedVenue = Venue.find(firstVenue.getId());
  assertTrue(firstVenue.equals(savedVenue));
  }
}
