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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.*;

public class ViewClientController {

    public  static Connection c = null;
    public   static Statement stmt = null;

    @FXML
    private Button button;

    @FXML
    private Button button1;

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
    private String Surname;
    private String Name;
    private String Birthdate;
    private String City;
    private String Address;
    private String Email;
    private String Phone;
    private String Passport;
    private String PassportVidacha;
    private String text;
    private int id ;

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

          ResultSet rs = stmt.executeQuery("SELECT  *  FROM client where  client_id = "+id+"");

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

               stmt.close();
              button.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent t) {
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();
                //openNewWindow1("newCreditSample.fxml");
            }
        });
    }
}