/**
 * representing a phone number
 */
public abstract class PhoneNumber {
    // instance variables with private access
    private String countryCode;
    private String areaCode;
    private String phoneNumber;

    // constructor
    public PhoneNumber(String countryCode, String areaCode, String phoneNumber) {
        this.countryCode = countryCode;
        setAreaCode(areaCode);
        setPhoneNumber(phoneNumber);
    }

    public PhoneNumber(String countryCode) {
        this.countryCode = countryCode;
        areaCode = "";
        phoneNumber = "";
    }

    // accessor functions
    public String getCountryCode() {
        return countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // mutator function
    public void setAreaCode(String areaCode) {
        checkAreaCode(areaCode);
        this.areaCode = areaCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    // abstract function
    public abstract void setPhone(String phone);

    public abstract void checkAreaCode(String areaCode);

    public abstract void checkPhoneNumber(String phoneNumber);
}
