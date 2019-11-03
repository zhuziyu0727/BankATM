import java.util.ArrayList;
import java.util.Arrays;

/**
 * present an address in US
 */
public class AddressUS extends Address {
    // static instance variables
    private static final String COUTNRY = "US";

    // constructor
    public AddressUS(String address1, String address2, String city, String state, String zipCode) {
        super(address1, address2, city, state, zipCode, COUTNRY);
    }

    public AddressUS() {
        super();
        setCountry(COUTNRY);
    }

    // check function
    @Override
    public void checkState(String state) {
        String states = "AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|" +
                "MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY";
        ArrayList<String> validStates = new ArrayList<>(Arrays.asList(states.split("\\|")));
        if (!validStates.contains(state)) {
            String alert = String.format("\"%s\" is not a valid state.", state);
            throw new IllegalArgumentException(alert);
        }
    }

    /**
     * Only check the length is 5
     */
    @Override
    public void checkZipCode(String zipCode) {
        try {
            if (zipCode.length() != 5)
                throw new NumberFormatException();
            int zipCodeInt = Integer.parseInt(zipCode);
            if (zipCodeInt < 0 )
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            String alert = String.format("\"%s\" is not a valid zip code.", zipCode);
            throw new IllegalArgumentException(alert);
        }
    };

    // override function
    @Override
    public String toString() {
        String address = getAddress1();
        address += " " + getAddress2();
        address += " " + getCity();
        address += " " + getState();
        address += " " + getZipCode();
        address += " " + getCountry();
        return address.trim();
    }
}
