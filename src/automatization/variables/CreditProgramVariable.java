package automatization.variables;

public class CreditProgramVariable {

    private int id;
    private String login;
    private String birthdate;
    private String email;
    private String passport;
    private String vidacha;
    private String surname;

    public CreditProgramVariable (int id, String login, String surname, String birthdate, String passport, String vidacha,  String email) {
        this.id = id;
        this.login = login;
        this.birthdate = birthdate;
        this.email = email;
        this.passport = passport;
        this.vidacha = vidacha;
        this.surname = surname;
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

    public String getVidacha() {
        return vidacha;
    }

    public void setVidacha(String vidacha) {
        this.vidacha = vidacha;
    }
}