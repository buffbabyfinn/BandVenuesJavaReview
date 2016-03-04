import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.WordUtils;
import org.sql2o.*;

public class Band {
  private int id;
  private String name;

  public Band(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return WordUtils.capitalize(name);

  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
      this.getId() == newBand.getId();
    }
  }

  public static List<Band> all() {
    String sql = " SELECT * FROM bands ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE id = :id";
      Band band = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM bands WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :newName WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", newName)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static List<Band> searchBands(String searchName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE name LIKE ':searchName%'";
      return con.createQuery(sql)
      .addParameter("searchName", searchName)
      .executeAndFetch(Band.class);
    }
  }
}
