import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PatronsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patrons.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Patrons firstPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
    Patrons secondPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
    assertTrue(firstPatrons.equals(secondPatrons));
  }

  @Test
  public void save_savesIntoDatabase() {
    Patrons firstPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
    firstPatrons.save();
    assertTrue(Patrons.all().get(0).equals(firstPatrons));
  }

  @Test
  public void all_returnsAllPatrons() {
    Patrons firstPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
    firstPatrons.save();
    assertEquals(Patrons.all().size(), 1);
  }

  @Test
  public void find_findPatronsInDatabase() {
  Patrons firstPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
  firstPatrons.save();
  Patrons savedPatrons = Patrons.find(firstPatrons.getId());
  assertTrue(firstPatrons.equals(savedPatrons));
  }

  @Test
  public void delete_deletesPatronsFromDatabase() {
    Patrons myPatrons = new Patrons("Franklin", "Jones", "frankiej@gmail.com");
    myPatrons.save();
    myPatrons.delete();
    assertEquals(Patrons.all().size(), 0);
  }
}
