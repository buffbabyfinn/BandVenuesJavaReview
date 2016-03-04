import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class AuthorsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Authors.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Authors firstAuthors = new Authors("Niel", "Gaiman");
    Authors secondAuthors = new Authors("Niel", "Gaiman");
    assertTrue(firstAuthors.equals(secondAuthors));
  }

  @Test
  public void save_savesIntoDatabase() {
    Authors firstAuthors = new Authors("Niel", "Gaiman");
    firstAuthors.save();
    assertTrue(Authors.all().get(0).equals(firstAuthors));
  }

  @Test
  public void all_returnsAllAuthorss() {
    Authors firstAuthors = new Authors("Niel", "Gaiman");
    firstAuthors.save();
    assertEquals(Authors.all().size(), 1);
  }

  @Test
  public void find_findAuthorsInDatabase() {
  Authors firstAuthors = new Authors("Niel", "Gaiman");
  firstAuthors.save();
  Authors savedAuthors = Authors.find(firstAuthors.getId());
  assertTrue(firstAuthors.equals(savedAuthors));
  }

  @Test
  public void delete_deletesAuthorsFromDatabase() {
    Authors myAuthors = new Authors("Niel", "Gaiman");
    myAuthors.save();
    myAuthors.delete();
    assertEquals(Authors.all().size(), 0);
  }
}
