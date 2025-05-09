// Baylen Romanello
import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the Starbucks abstract class. Provides a simple structure to store
 * Starbucks locations and retrieve the nearest location based on coordinates.
 *
 * <p>Author: ADD YOUR NAME HERE
 */
public class StudentStarbucks extends Starbucks {

  // List to store unique Starbucks locations
  private List<Locations> locations;

  /**
   * Builds a data structure containing all provided Starbucks locations.
   *
   * <p>If two locations have coordinates such that both |x1 - x2| <= 0.00001 and |y1 - y2| <=
   * 0.00001, only one should be added to avoid duplicate entries. This ensures that the data
   * structure does not contain redundant locations.
   *
   * @param allLocations An array of Locations objects representing all Starbucks locations.
   */
  @Override
  public void build(Locations[] allLocations) {
    // Initialize the list to store unique locations
    locations = new ArrayList<>();

    // Iterate through all provided locations
    for (Locations loc : allLocations) {
      // Check if the current location is a duplicate of any existing location
      boolean duplicate =
          locations.stream()
              .anyMatch(
                  l -> Math.abs(l.lat - loc.lat) <= 0.001 && Math.abs(l.lng - loc.lng) <= 0.001);

      // If the location is not a duplicate, add it to the list
      if (!duplicate) locations.add(loc);
    }
  }

  /**
   * Finds and returns a DEEP copy of the Starbucks location nearest to the given longitude and
   * latitude.
   *
   * <p>This method iterates through all stored locations to find the one closest to the given
   * coordinates. The distance is calculated using the Euclidean distance formula.
   *
   * @param lng The longitude of the query point, in degrees.
   * @param lat The latitude of the query point, in degrees.
   * @return A deep copy of the nearest Locations object, or null if no locations exist.
   */
  @Override
  public Locations getNearest(double lng, double lat) {
    // Variable to store the closest location found so far
    Locations best = null;

    // Variable to store the shortest distance found so far
    double bestDist = Double.MAX_VALUE;

    // Iterate through all stored locations
    for (Locations loc : locations) {
      // Calculate the difference in longitude and latitude
      double dx = loc.lng - lng, dy = loc.lat - lat;

      // Calculate the squared distance (avoiding unnecessary square root for comparison)
      double distSq = dx * dx + dy * dy;

      // Check if the current location is closer than the best found so far
      if (distSq < bestDist * bestDist + 1e-9) {
        // Calculate the actual distance
        double dist = Math.sqrt(distSq);

        // Update the best location and distance if the current location is closer
        if (dist < bestDist) {
          bestDist = dist;
          best = loc;
        }
      }
    }

    // Return a deep copy of the closest location, or null if no locations exist
    return best == null ? null : new Locations(best);
  }
}
