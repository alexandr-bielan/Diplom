package automatization.variables;

public class ScheduleVariable {

    private int id;
    private String sum;
    private String date;
    private int control;

    public ScheduleVariable(int id, String sum, String date, int control) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.control = control;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }
}