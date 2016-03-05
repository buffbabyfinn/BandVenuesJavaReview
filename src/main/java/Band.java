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
     String sql = "UPDATE bands SET name = :name WHERE id = :id";
     try(Connection con = DB.sql2o.open()) {
       con.createQuery(sql)
         .addParameter("name", newName)
         .addParameter("id", id)
         .executeUpdate();
     }
   }


  public void addVenue(int venueId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", id)
        .addParameter("venue_id", venueId)
        .executeUpdate();
    }
  }

public List<Venue> getVenues() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT venues.* FROM bands JOIN bands_venues ON (bands.id = bands_venues.band_id) JOIN venues ON (bands_venues.venue_id = venues.id) WHERE bands.id = :id";
    return con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetch(Venue.class);
    }
  }
}
