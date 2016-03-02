import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BooksTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Books.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Books firstBooks = new Books("Neverwhere", 7);
    Books secondBooks = new Books("Neverwhere", 7);
    assertTrue(firstBooks.equals(secondBooks));
  }

  @Test
  public void save_savesIntoDatabase() {
    Books firstBooks = new Books("Neverwhere", 7);
    firstBooks.save();
    assertTrue(Books.all().get(0).equals(firstBooks));
  }

  @Test
  public void all_returnsAllBookss() {
    Books firstBooks = new Books("Neverwhere", 7);
    firstBooks.save();
    assertEquals(Books.all().size(), 1);
  }

  @Test
  public void find_findBooksInDatabase() {
  Books firstBooks = new Books("Neverwhere", 7);
  firstBooks.save();
  Books savedBooks = Books.find(firstBooks.getId());
  assertTrue(firstBooks.equals(savedBooks));
  }

  @Test
  public void delete_deletesBooksFromDatabase() {
    Books myBooks = new Books("Neverwhere", 7);
    myBooks.save();
    myBooks.delete();
    assertEquals(Books.all().size(), 0);
  }
}
