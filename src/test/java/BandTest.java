import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Band firstBand = new Band("Toe");
    Band secondBand = new Band("Toe");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesIntoDatabase() {
    Band firstBand = new Band("Toe");
    firstBand.save();
    assertTrue(Band.all().get(0).equals(firstBand));
  }

  @Test
  public void all_returnsAllBands() {
    Band firstBand = new Band("Toe");
    firstBand.save();
    assertEquals(Band.all().size(), 1);
  }

  @Test
  public void find_findBandInDatabase() {
  Band firstBand = new Band("Toe");
  firstBand.save();
  Band savedBand = Band.find(firstBand.getId());
  assertTrue(firstBand.equals(savedBand));
  }

  @Test
  public void delete_deletesBandFromDatabase() {
    Band myBand = new Band("Toe");
    myBand.save();
    myBand.delete();
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void getName_returnsStyledName() {
    Band myBand = new Band("the pixies");
    myBand.save();
    assertEquals(myBand.getName(), "The Pixies");
  }
}
