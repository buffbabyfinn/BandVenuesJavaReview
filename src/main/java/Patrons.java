import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Patrons {
  private int id;
  private String first_name;
  private String last_name;
  private String email;

  public Patrons(String first_name, String last_name, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object otherPatrons){
    if (!(otherPatrons instanceof Patrons)) {
      return false;
    } else {
      Patrons newPatrons = (Patrons) otherPatrons;
      return this.getFirstName().equals(newPatrons.getFirstName()) &&
      this.getLastName().equals(newPatrons.getLastName()) && this.getEmail().equals(newPatrons.getEmail()) &&
      this.getId() == newPatrons.getId();
    }
  }

  public static List<Patrons> all() {
    String sql = " SELECT * FROM patrons ORDER BY last_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patrons.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons(first_name, last_name, email) VALUES (:first_name, :last_name, :email)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", this.first_name)
        .addParameter("last_name", this.last_name)
        .addParameter("email", email)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patrons find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons WHERE id = :id";
      Patrons patron = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patrons.class);
      return patron;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM patrons WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String newFirst, String newLast, String newEmail) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patrons SET first_name = :newFirst, last_name = :newLast, email = :newEmail WHERE id = :id";
      con.createQuery(sql)
      .addParameter("first_name", newFirst)
      .addParameter("last_name", newLast)
      .addParameter("email", newEmail)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public List<Checkout> getPatronsCheckout() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT checkout.* FROM checkout JOIN patrons ON (checkout.patron_id = patrons.id) WHERE patrons.id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Checkout.class);
    }
  }
}
