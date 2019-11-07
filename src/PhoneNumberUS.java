/**
 * US phone number
 */
public class PhoneNumberUS extends PhoneNumber {
    // private static final variables
    private static final String COUNTRYCODE = "1";

    // constructors
    public PhoneNumberUS(String areaCode, String phoneNumber) {
        super(COUNTRYCODE, areaCode, phoneNumber);
    }

    public PhoneNumberUS(String phone) {
        this();
        setPhone(phone);
    }

    /**
     * default phone number is set to be (200)000-0000
     */
    public PhoneNumberUS() {
        super(COUNTRYCODE);
    }

    // mutator function
    public void setPhone(String phone) {
    	phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        phone = phone.replaceAll("-", "");
        checkPhone(phone);
        setAreaCode(phone.substring(0,3));
        setPhoneNumber(phone.substring(3));
    }

    // private check function
    private void checkPhone(String phone) {
        if (phone.length() != 10) {
            String alert = String.format("Phone \"%s\" is not a valid number.", phone);
            throw new IllegalArgumentException(alert);
        }
    }

    // override functions
    /**
     * must between 200 and 999
     */
    @Override
    public void checkAreaCode(String areaCode) {
        try {
            int areaCodeInt = Integer.parseInt(areaCode);
            if (areaCodeInt<200 || areaCodeInt>999) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            String alert = String.format("area code \"%s\" is not valid.", areaCode);
            throw new IllegalArgumentException(alert);
        }
    }

    /**
     * must between 0 and 9999999
     */
    @Override
    public void checkPhoneNumber(String phoneNumber) {
        try {
            if (phoneNumber.length() != 7)
                throw new NumberFormatException();
            int phoneNumberInt = Integer.parseInt(phoneNumber);
            if (phoneNumberInt<0 || phoneNumberInt>9999999) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            String alert = String.format("phone number \"%s\" is not valid.", phoneNumber);
            throw new IllegalArgumentException(alert);
        }
    }

    @Override
    public String toString() {
        if (getAreaCode().equals(""))
            return "";
        String phoneNumberFormat = "+%s (%s)%s-%s";
        String phoneNumber = String.format(phoneNumberFormat, COUNTRYCODE, getAreaCode(),
                getPhoneNumber().substring(0, 3), getPhoneNumber().substring(3));
        phoneNumberFormat = "(%s)%s-%s";
        phoneNumber = String.format(phoneNumberFormat, getAreaCode(),
                getPhoneNumber().substring(0, 3), getPhoneNumber().substring(3));
        return phoneNumber;
    }
}
