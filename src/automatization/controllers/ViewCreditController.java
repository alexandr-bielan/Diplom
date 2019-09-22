package automatization.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import automatization.Main;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.*;


public class ViewCreditController extends Main {

    public  static Connection c = null;
    public   static Statement stmt = null;

    @FXML
    private Button button;

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
    private TextField  passvidachaColumn;
//
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

    private String b;
    private String Surname;
    private String Name;
    private String Birthdate;
    private String City;
    private String Address;
    private String Email;
    private String Phone;
    private String Passport;
    private String PassportVidacha;
    private int id ;
    private int clientId;
    private String text;
    private int id4 ;
    private String text4;
    private int sum;
    private String productName;
    private int percent;
    private String term;
    private int average;
    private int productCost;
    private String dateRepayment;
    private String creditType;
    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException,ConnectException {
        initData();
    }

    // подготавливаем данные для таблицы
      private void initData() throws SQLException, ClassNotFoundException {

          Class.forName("org.postgresql.Driver");
          c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
          stmt = c.createStatement();

          ResultSet rs1 = stmt.executeQuery("SELECT  *  FROM dipl ");
          while (rs1.next()) {
          text = rs1.getString("new_column");
          id = Integer.parseInt(text);
          }


          ResultSet rs2 = stmt.executeQuery("SELECT  *  FROM credit where  creditid = "+id+"");

          while (rs2.next()) {
              id4 = rs2.getInt("creditid");
              sum = rs2.getInt("creditsum");
              productName = rs2.getString("product");
              percent= rs2.getInt("interestrate");
              term = rs2.getString("dateofloan");
              average = rs2.getInt("client_profit");
              productCost = rs2.getInt("productcost");
              dateRepayment = rs2.getString("daterepayment");
              creditType = rs2.getString("credittype");
              clientId = rs2.getInt("client_id");
          }

          String temp1 = String.valueOf(id4);
          numberColumn.setText(temp1);
          String temp2 = String.valueOf(sum);
          sumColumn.setText(temp2);
          productNameColumn.setText(productName);
          String temp3 = String.valueOf(percent);
          percentColumn.setText(temp3);
          dataCredColumn.setText(term);
          String temp4 = String.valueOf(productCost);
          productCostColumn.setText(temp4);
          typeCreditColumn.setText(creditType);
          String temp5 = String.valueOf(average);
          averageColumn.setText(temp5);
          termColumn.setText(dateRepayment);

          ResultSet rs = stmt.executeQuery("SELECT  *  FROM client where  client_id = "+clientId+"");

         while (rs.next()) {
              id = rs.getInt("client_id");
              Surname = rs.getString("client_surname");
              Name = rs.getString("client_name");
              Birthdate = rs.getString("client_birthdate");
              City = rs.getString("client_city");
              Address = rs.getString("client_address");
              Email = rs.getString("client_email");
              Phone = rs.getString("client_phone");
              Passport = rs.getString("client_passport");
              PassportVidacha = rs.getString("client_passvidacha");
          }

              String temp = String.valueOf(id);
              idColumn.setText(temp);
              loginColumn.setText(Name);
              birthdateColumn.setText(Birthdate);
              surnameColumn.setText(Surname);
              cityColumn.setText(City);
              addressColumn.setText(Address);
              emailColumn.setText(Email);
              phoneColumn.setText(Phone);
              passportColumn.setText(Passport);
              passvidachaColumn.setText(PassportVidacha);



           //stmt.executeUpdate("DELETE FROM dipl WHERE new_column=2" );
          // stmt.executeUpdate("DELETE FROM dipl1 WHERE new_column=2" );

          stmt.close();
        button.setOnAction(new EventHandler<ActionEvent>()  {

            @Override

            public void handle(ActionEvent t) {
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();
                openNewWindow("schedule/scheduleSample.fxml");
            }
        });
    }
}