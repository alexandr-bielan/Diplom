package automatization.variables;

public class ClientBaseVariable {

    private int id;
    private String login;
    private String birthdate;
    private String email;
    private String passport;
    private String vidacha;
    private String surname;
    private String city;
    private String address;
    private String phone;

    public ClientBaseVariable (int id, String login, String surname, String birthdate, String passport, String vidacha, String city,  String email, String address, String phone) {
        this.id = id;
        this.login = login;
        this.birthdate = birthdate;
        this.email = email;
        this.passport = passport;
        this.vidacha = vidacha;
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

    public String getVidacha() {
        return vidacha;
    }

    public void setVidacha(String vidacha) {
        this.vidacha = vidacha;
    }
}