/**
 * stand for a register member in any website or organization
 * currently, only support US phone number and US address
 */
public class Member extends Person{
    // instance variable with private access
    private Email email;
    private Username username;
    private Password password;
    private PhoneNumber phoneNumber;
    private Address address;

    // constructor
    public Member() {
        super();
        email = new Email();
        username = new Username();
        password = new Password();
        phoneNumber = new PhoneNumberUS();
        address = new AddressUS();
    }

    // accessor functions
    public String getEmail() {
        return email.toString();
    }

    public String getUsername() {
        return username.getUsername();
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getPhoneNumber() {
        return phoneNumber.toString();
    }

    public String getAddress() {
        return address.toString();
    }

    public String getAddressAddress1() {
        return address.getAddress1();
    }

    public String getAddressAddress2() {
        return address.getAddress2();
    }

    public String getAddressCity() {
        return address.getCity();
    }

    public String getAddressState() {
        return address.getState();
    }

    public String getAddressZipCode() {
        return address.getZipCode();
    }

    public String getAddressCountry() {
        return address.getCountry();
    }

    // mutator functions
    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public void setUsername(String username) {
        this.username.setUsername(username);
    }

    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public void setPhoneNumber(String phone) {
        phoneNumber.setPhone(phone);
    }

    public void setAddress1(String address1) {
        address.setAddress1(address1);
    }

    public void setAddress2(String address2) {
        address.setAddress2(address2);
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public void setState(String state) {
        address.setState(state);
    }

    public void setCountry(String country) {
        address.setCountry(country);
    }

    public void setZipCode(String zipCode) {
        address.setZipCode(zipCode);
    }
}
