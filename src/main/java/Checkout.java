import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Checkout {
  private int id;
  private int book_id;
  private int patron_id;
  private String due_date;

  public Checkout(int book_id, int patron_id) {
    this.book_id = book_id;
    this.patron_id = patron_id;
    this.initDueDate();
  }

  public int getId() {
    return id;
  }

  public int getBookId() {
    return book_id;
  }

  public int getPatronId() {
    return patron_id;
  }

  public String getDueDate() {
    // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    // return dateFormat.format(due_date);
    return due_date;
  }

  @Override
  public boolean equals(Object otherCheckout){
    if (!(otherCheckout instanceof Checkout)) {
      return false;
    } else {
      Checkout newCheckout = (Checkout) otherCheckout;
      return this.getBookId()== newCheckout.getBookId() && this.getPatronId() == newCheckout.getPatronId() && this.getId() == newCheckout.getId() && this.getDueDate().equals(newCheckout.getDueDate());
    }
  }

  public void initDueDate() {
    Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.WEEK_OF_MONTH, 2);
    date = c.getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    this.due_date = dateFormat.format(date);
  }

  public static List<Checkout> all() {
    String sql = "SELECT * FROM checkout";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Checkout.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO checkout(book_id, patron_id, due_date) VALUES (:book_id, :patron_id, :due_date)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("book_id", this.book_id)
        .addParameter("patron_id", this.patron_id)
        .addParameter("due_date", this.due_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static Checkout find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM checkout WHERE id = :id";
      Checkout checkout = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Checkout.class);
      return checkout;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM checkout WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
