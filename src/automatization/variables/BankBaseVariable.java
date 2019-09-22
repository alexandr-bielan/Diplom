package automatization.variables;



public class BankBaseVariable {

    private int id;
    private String name;
    private String  login;
    private String address;
    private int percent;

    public BankBaseVariable(int id, String name, String login, String address, int percent) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.address = address;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}