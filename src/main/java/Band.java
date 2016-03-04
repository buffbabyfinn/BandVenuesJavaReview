import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Authors {
  private int id;
  private String first_name;
  private String last_name;

  public Authors(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  @Override
  public boolean equals(Object otherAuthors){
    if (!(otherAuthors instanceof Authors)) {
      return false;
    } else {
      Authors newAuthors = (Authors) otherAuthors;
      return this.getFirstName().equals(newAuthors.getFirstName()) &&
      this.getLastName().equals(newAuthors.getLastName()) &&
      this.getId() == newAuthors.getId();
    }
  }

  public static List<Authors> all() {
    String sql = " SELECT * FROM authors ORDER BY last_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Authors.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors(first_name, last_name) VALUES (:first_name, :last_name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", this.first_name)
        .addParameter("last_name", this.last_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Authors find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE id = :id";
      Authors author = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Authors.class);
      return author;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM authors WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String newFirst, String newLast) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET first_name = :newFirst, last_name = :newLast WHERE id = :id";
      con.createQuery(sql)
      .addParameter("first_name", newFirst)
      .addParameter("last_name", newLast)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static List<Books> searchAuthors(String searchAuthors) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT books.* FROM books JOIN authors_books ON (authors_books.book_id = books.id) JOIN authors ON (authors_books.author_id = authors.id) WHERE authors.last_name = :searchAuthors";
      return con.createQuery(sql)
      .addParameter("searchAuthors", searchAuthors)
      .executeAndFetch(Books.class);
    }
  }
}
