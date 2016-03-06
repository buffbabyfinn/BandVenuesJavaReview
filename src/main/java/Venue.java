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

  public void addBand(int bandId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", bandId)
        .addParameter("venue_id", id)
        .executeUpdate();
    }
  }

public List<Band> getBands() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT bands.* FROM venues JOIN bands_venues ON (venues.id = bands_venues.venue_id) JOIN bands ON (bands_venues.band_id = bands.id) WHERE venues.id = :id";
    return con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetch(Band.class);
    }
  }
}
