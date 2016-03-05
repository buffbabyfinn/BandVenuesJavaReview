import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String venue_name;
  private String location;

  public Venue(String venue_name, String location) {
    this.venue_name = venue_name;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return venue_name;
  }

  public String getLocation() {
    return location;
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
      this.getId() == newVenue.getId();
    }
  }

  public static List<Venue> all() {
    String sql = " SELECT * FROM venues ORDER BY venue_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Venue.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues(venue_name, location) VALUES (:venue_name, :location)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("venue_name", venue_name)
        .addParameter("location", location)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues WHERE id = :id";
      Venue venue = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }
}
