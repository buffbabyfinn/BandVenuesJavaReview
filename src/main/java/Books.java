import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Books {
  private int id;
  private String title;
  private int copies;

  public Books(String title, int copies) {
    this.title = title;
    this.copies = copies;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getCopies() {
    return copies;
  }

  @Override
  public boolean equals(Object otherBooks){
    if (!(otherBooks instanceof Books)) {
      return false;
    } else {
      Books newBooks = (Books) otherBooks;
      return this.getTitle().equals(newBooks.getTitle()) && this.getCopies() == newBooks.getCopies() && this.getId() == newBooks.getId();
    }
  }

  public static List<Books> all() {
    String sql = " SELECT * FROM books ORDER BY title";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Books.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books(title, copies) VALUES (:title, :copies)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("copies", this.copies)
        .executeUpdate()
        .getKey();
    }
  }

  public static Books find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books WHERE id = :id";
      Books book = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Books.class);
      return book;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM books WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String newTitle, int newCopies) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :newTitle, copies = :newCopies WHERE id = :id";
      con.createQuery(sql)
      .addParameter("title", newTitle)
      .addParameter("copies", newCopies)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
