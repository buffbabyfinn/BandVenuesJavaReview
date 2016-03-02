import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CheckoutTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Checkout.all().size(), 0);
  }

  @Test
  public void initDueDate_ReturnsADate() {
    Checkout firstCheckout = new Checkout(1, 3);
    assertEquals(true, firstCheckout.getDueDate() instanceof String);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Checkout firstCheckout = new Checkout(1, 3);
    Checkout secondCheckout = new Checkout(1, 3);
    assertTrue(firstCheckout.equals(secondCheckout));
  }

  @Test
  public void save_savesIntoDatabase() {
    Checkout firstCheckout = new Checkout(1, 3);
    firstCheckout.save();
    assertTrue(Checkout.all().get(0).equals(firstCheckout));
  }

  @Test
  public void all_returnsAllCheckouts() {
    Checkout firstCheckout = new Checkout(1, 3);
    firstCheckout.save();
    assertEquals(Checkout.all().size(), 1);
  }

  @Test
  public void find_findCheckoutInDatabase() {
  Checkout firstCheckout = new Checkout(1, 3);
  firstCheckout.save();
  Checkout savedCheckout = Checkout.find(firstCheckout.getId());
  assertTrue(firstCheckout.equals(savedCheckout));
  }

  @Test
  public void delete_deletesCheckoutFromDatabase() {
    Checkout myCheckout = new Checkout(1, 3);
    myCheckout.save();
    myCheckout.delete();
    assertEquals(Checkout.all().size(), 0);
  }
}
