import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the Starbucks abstract class. Provides a simple structure to store
 * Starbucks locations and retrieve the nearest location based on coordinates.
 *
 * <p>Author: ADD YOUR NAME HERE
 */
public class StudentStarbucks extends Starbucks {

  /**
   * Builds a data structure containing all provided Starbucks locations.
   *
   * <p>If two locations have coordinates such that both |x1 - x2| <= 0.00001 and |y1 - y2| <=
   * 0.00001, only one should be added to avoid duplicate entries. Make sure that you are
   * implementing this in your implementation.
   *
   * @param allLocations An array of Locations objects representing all Starbucks locations.
   */
  private List<Locations> locations;
  @Override
  public void build(Locations[] allLocations) {
    locations = new ArrayList<>();
    for (Locations loc : allLocations) {
      boolean duplicate =
          locations.stream()
              .anyMatch(
                  l -> Math.abs(l.lat - loc.lat) <= 0.001 && Math.abs(l.lng - loc.lng) <= 0.001);
      if (!duplicate) locations.add(loc);
    }
  }

  /**
   * Finds and returns a DEEP copy of the Starbucks location nearest to the given longitude and
   * latitude.
   *
   * @param lng The longitude of the query point, in degrees.
   * @param lat The latitude of the query point, in degrees.
   * @return A deep copy of the nearest Locations object, or null if no locations exist.
   */
  @Override
  public Locations getNearest(double lng, double lat) {
    Locations best = null;
    double bestDist = Double.MAX_VALUE;

    for (Locations loc : locations) {
      double dx = loc.lng - lng, dy = loc.lat - lat;
      double distSq = dx * dx + dy * dy;

      if (distSq < bestDist * bestDist + 1e-9) {
        double dist = Math.sqrt(distSq);
        if (dist < bestDist) {
          bestDist = dist;
          best = loc;
        }
      }
    }

    return best == null ? null : new Locations(best);
  }
}
