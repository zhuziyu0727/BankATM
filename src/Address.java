/**
 * represent an address
 */
public abstract class Address {
    // instance variables with private access
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // constructors
    public Address(String address1, String address2, String city, String state, String zipCode, String country) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        setState(state);
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
        this.country = "";
    }

    // accessor functions
    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    // mutator functions
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        checkState(state);
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        checkZipCode(zipCode);
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // abstract function
    public abstract void checkState(String state);

    public abstract void checkZipCode(String zipCode);
}
