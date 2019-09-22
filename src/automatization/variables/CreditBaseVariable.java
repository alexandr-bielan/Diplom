package automatization.variables;

public class CreditBaseVariable {

    private int id;
    private String login;
    private String birthdate;
    private String email;
    private String passport;
    private String surname;
    private String city;
    private String address;
    private String phone;

    public CreditBaseVariable(int id, String login, String birthdate, String email, String passport, String surname, String city, String address, String phone) {
        this.id = id;
        this.login = login;
        this.birthdate = birthdate;
        this.email = email;
        this.passport = passport;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}