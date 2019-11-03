/**
 * Record the name attributes of a person
 */
public class Name {
    // instance variables with private access
    private String lastName;
    private String firstName;
    private String middleName;
    private String nickName;

    // constructors
    public Name(String lastName, String firstName, String middleName, String nickName) {
        this.lastName   = lastName;
        this.firstName  = firstName;
        this.middleName = middleName;
        this.nickName   = nickName;
    }

    /**
     * If Name is initialized with none arguments
     * all fields will be set as ""
     */
    public Name() {
        this("", "", "", "");
    }

    // accessor functions
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNickName() {
        return nickName;
    }

    // mutator functions
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    // override functions
    @Override
    public String toString() {
        String fullName = lastName + " " + middleName + " " + firstName + " " + nickName;
        fullName = fullName.trim();
        return fullName;
    }
}
