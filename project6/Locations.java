/**
 * Note: DO NOT CHANGE THIS FILE!!!
 * A Data Type class that holds the necessary details of a Starbucks location,
 * including city, address, longitude, and latitude.
 * 
 * Author: amjadm
 */
public class Locations {

    /** The city where the Starbucks location is found. */
    public String city;

    /** The address of the Starbucks location. */
    public String address;

    /** The longitude coordinate of the Starbucks location. */
    public double lng; // longitude

    /** The latitude coordinate of the Starbucks location. */
    public double lat; // latitude

    /**
     * Default constructor.
     * Initializes a location with empty city and address,
     * and sets longitude and latitude to 0.0.
     */
    public Locations() {
        this.city = "";
        this.address = "";
        this.lng = lat = 0.0;
    }

    /**
     * Parameterized constructor.
     * Initializes a location with specified city, address, longitude, and latitude.
     *
     * @param icity    The city name.
     * @param iaddress The address.
     * @param ilng     The longitude coordinate.
     * @param ilat     The latitude coordinate.
     */
    public Locations(String icity, String iaddress, double ilng, double ilat) {
        this.city = icity;
        this.address = iaddress;
        this.lng = ilng;
        this.lat = ilat;
    }

    /**
     * Copy constructor.
     * Creates a new Locations object that is a deep copy of another Locations object.
     *
     * @param orig The original Locations object to copy.
     */
    public Locations(Locations orig) {
        this.city = orig.city;
        this.address = orig.address;
        this.lng = orig.lng;
        this.lat = orig.lat;
    }
}