package automatization.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.ConnectException;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import automatization.Main;

public class ScoringController extends  Main {

    public  static Connection c = null;
    public   static Statement stmt = null;
//Переменные Text Field
    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private TextField idColumn;

    @FXML
    private TextField  loginColumn;

    @FXML
    private TextField birthdateColumn;

    @FXML
    private TextField  emailColumn;

    @FXML
    private TextField  passportColumn;

    @FXML
    private TextField  surnameColumn;

    @FXML
    private TextField cityColumn;

    @FXML
    private TextField  addressColumn;

    @FXML
    private TextField  phoneColumn;

    @FXML
    private TextField dataCredColumn;

    @FXML
    private TextField productNameColumn;

    @FXML
    private TextField sumColumn;

    @FXML
    private TextField termColumn;

    @FXML
    private TextField numberColumn;

    @FXML
    private TextField percentColumn;

    @FXML
    private TextField typeCreditColumn;

    @FXML
    private TextField  productCostColumn;

    @FXML
    private TextField  averageColumn;

    @FXML
    private TextField  bankColumn;

    //Переменные Text Field
    private String Surname;
    private String Name;
    private String Birthdate;
    private String City;
    private String Address;
    private String Email;
    private String Phone;
    private String Passport;
    private String text1;
    private String text;
    private String average;
    private String productName;
    private String productCost;
    private String sum;
    private String credittype;
    private String dateCredit;
    private String date1;
    private String sql1;
    private int term;
    private int bank;
    private int b;
    private int temp1;
    private int average1;
    private int id ;
    private int creditpercent;
    private int creditid;
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    Calendar cal1 = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException,ConnectException {
        initData();
    }

    // подготавливаем данные для таблицы
      private void initData() throws SQLException, ClassNotFoundException {

            //Открываем доступ в базу данных
          Class.forName("org.postgresql.Driver");
          c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
          stmt = c.createStatement();

          //Вводим номер заявки
          button1.setOnAction(e->{
              text1 = idColumn.getText();
              id = Integer.parseInt(text1);
              System.out.println(id);
              String sql = "INSERT INTO dipl (new_column)"  +  "VALUES ("+id+")";

              try {
                  stmt.executeUpdate(sql);
                  stmt.close();
                  c.commit();
              }
              catch (SQLException t) {
                  System.err.println(t.getClass().getName()+": "+t.getMessage());

                   System.exit(0);
              }
               openNewWindow("/automatization/forms/ScoringForm.fxml");
              Stage stage = (Stage) button1.getScene().getWindow();
              stage.close();
          });

          //Считываем номер заявки из БД
          ResultSet rs1 = stmt.executeQuery("SELECT  *  FROM dipl ");
          while (rs1.next()) {
          text = rs1.getString("new_column");
          id = Integer.parseInt(text);
          }
          System.out.println(id);
          //Вводим в форму данные из таблицы БД  credit_application
          ResultSet rs5 = stmt.executeQuery("SELECT  *  FROM credit_application where  creditid = "+id+"");

          while (rs5.next()) {
              b = rs5.getInt("creditid");
              average = rs5.getString("client_profit");
              average1 = rs5.getInt("client_profit");
              productCost = rs5.getString("productcost");
              temp1 = rs5.getInt("creditsum");
              productName  = rs5.getString("product");
          }
          averageColumn.setText(average);
          productCostColumn.setText(productCost);
          sum = String.valueOf(temp1);
          sumColumn.setText(sum);
          productNameColumn.setText(productName);
          String temp = String.valueOf(b);
          idColumn.setText(temp);

          //Вводим в форму данные из таблицы БД  client
          ResultSet rs = stmt.executeQuery("SELECT  *  FROM client where  client_id = "+b+"");

          while (rs.next()) {
              //id = rs.getInt("credit_id");
              Surname = rs.getString("client_surname");
              Name = rs.getString("client_name");
              Birthdate = rs.getString("client_birthdate");
              City = rs.getString("client_city");
              Address = rs.getString("client_address");
              Email = rs.getString("client_email");
              Phone = rs.getString("client_phone");
              Passport = rs.getString("client_passport");
          }

          loginColumn.setText(Name);
          birthdateColumn.setText(Birthdate);
          surnameColumn.setText(Surname);
          cityColumn.setText(City);
          addressColumn.setText(Address);
          emailColumn.setText(Email);
          phoneColumn.setText(Phone);
          passportColumn.setText(Passport);

           //stmt.executeUpdate("DELETE FROM dipl" );

          //Проверяем условие выдачи кредита
          int temp2;//Переменная для хранения рассчетного значения month
          int month = temp1/(average1 - 25000);
          if(month<=6)
              temp2 = 6;
              else if(month>=6 && month<=12)
                  temp2 = 12;
                  else
                      temp2 = 18;
          if(month>18)
             temp2 = 0;
          ResultSet rs16 = stmt.executeQuery("SELECT  *  FROM credit_program where  term = "+temp2+"");
            while (rs16.next()) {
              //id = rs16.getInt("credit_id");
              credittype = rs16.getString("credittypecolumn");
              creditpercent = rs16.getInt("creditpercentcolumn");
              creditid = rs16.getInt("creditid");
              dateCredit = df.format(date);
              term = rs16.getInt("term");
          }

         button.setOnAction(e->{
              if(month>18)
                  showAlertWithoutHeaderText();
              else
                  {
                      showAlertWithoutHeaderText1();
                      String с = String.valueOf(term);
                      termColumn.setText(с);
                      String d = String.valueOf(creditpercent);
                      percentColumn.setText(d);
                      typeCreditColumn.setText(credittype);
                      dataCredColumn.setText(dateCredit);
                      String f = String.valueOf(creditid);
                      numberColumn.setText(f);
                  }
           });

          button2.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent t) {

                cal.add(Calendar.MONTH, 18);
                cal1.add(Calendar.MONTH, 0);
                String dataCred1 =  df.format(cal.getTime());
                String dataCred =  dataCredColumn.getText();
                String productName =  productNameColumn.getText();
                String sum =  sumColumn.getText();
                String term1  = termColumn.getText();
                String number =  numberColumn.getText();
                String percent  =  percentColumn.getText();
                String typeCredit =  typeCreditColumn.getText();
                String productCost =  productCostColumn.getText();
                String average =  averageColumn.getText();
                String Bank =  bankColumn.getText();

                String sql = "INSERT INTO credit (creditid, creditsum, product, interestrate, dateofloan, client_profit, productcost, daterepayment, credittype,solution,client_id, bank) \n"
                        +   "VALUES (" + number+",'"+sum+"','"+productName+"','"+percent+"','"+dataCred+"','"+average+"','"+productCost+"','"+dataCred1+"','"+typeCredit+"','"+term1+"',"+b+",'"+Bank+"')";
                System.out.println (term);
                for(int i=1; i<=term;i++)
                {
                    cal1.add(Calendar.MONTH, 1);
                    String dataCred2 =  df.format(cal1.getTime());

                    sql1 = "INSERT INTO grafic_payment (graficpayment_id, graficpayment_sum, graficpayment_date, credit_id) \n"
                            +   "VALUES (" + i+",'"+(temp1*(1+2*creditpercent*0.01)/18)+"','"+dataCred2+"','"+number+"')";
                    try {
                        stmt.executeUpdate(sql1);
                        c.commit();
                    }
                    catch (SQLException e) {
                        System.err.println(e.getClass().getName()+": "+e.getMessage());
                    }
                }

                // System.out.println ("You have entered a client_Passporty " + surname);
                try {

                    stmt.executeUpdate(sql);

                    stmt.close();
                    c.commit();
                }
                catch (SQLException e) {
                    System.err.println(e.getClass().getName()+": "+e.getMessage());

                    //System.exit(1);
                }
                Stage stage = (Stage) button.getScene().getWindow();

            }
        });
    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Выдача кредита невозможна");
        alert.showAndWait();
    }

    private void showAlertWithoutHeaderText1() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Выдача кредита возможна");
        alert.showAndWait();
    }
}