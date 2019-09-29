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
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import  automatization.services.OpenNewWindow;

public class NewClientController extends Main {
    OpenNewWindow x = new OpenNewWindow();
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

    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

         initData();

     }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() throws SQLException, ClassNotFoundException {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT COUNT (client_id ) FROM client");
            int sum=0;
            while (rs.next()) {
                int kl = rs.getInt(1);
                sum = sum + kl;
            }
            String b = String.valueOf(sum+1);
            idColumn.setText(b);

            button.setOnAction(new EventHandler<ActionEvent>()  {

            @Override

            public void handle(ActionEvent t) {
                //String
                String id =  idColumn.getText();
                String surname =  surnameColumn.getText();
                String name =  loginColumn.getText();
                String birthdate  = birthdateColumn.getText();
                String city =  cityColumn.getText();
                String address  =  addressColumn.getText();
                String email =  emailColumn.getText();
                String phone =  phoneColumn.getText();
                String passport =  passportColumn.getText();

                String sql = "INSERT INTO client (client_id,  client_surname,  client_name,  client_birthdate, client_city,  client_address,  client_email, client_phone, client_passport)\n"
                                +   "VALUES (" + id+",'"+surname+"','"+name+"','"+birthdate+"','"+city+"','"+address+"','"+email+"','"+phone+"','"+passport+"')";

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
                stage.close();
                x.openNewWindow("/automatization/forms/NewClientForm.fxml");

            }
        });

    }

}