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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import automatization.Main;

import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.*;

public class NewCreditController {

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
    @FXML
    private TextField dataCredColumn;

    @FXML
    private TextField productNameColumn;

    @FXML
    private TextField sumColumn;

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
    private String text1;
    private String text;
    private String temp;
    private int sum1;
    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException   {
        initData();
    }

    // подготавливаем данные для таблицы
      private void initData() throws SQLException, ClassNotFoundException {

          Class.forName("org.postgresql.Driver");
          c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
          stmt = c.createStatement();

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
               openNewWindow1("/automatization/forms/NewCreditForm.fxml");
              Stage stage = (Stage) button1.getScene().getWindow();
              //stage.close();
          });

          ResultSet rs1 = stmt.executeQuery("SELECT  *  FROM dipl ");
          while (rs1.next()) {
          text = rs1.getString("new_column");
          id = Integer.parseInt(text);
          }
          System.out.println(id);

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

              temp = String.valueOf(id);
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

           //stmt.executeUpdate("DELETE FROM dipl" );
          ResultSet rs5 = stmt.executeQuery( "SELECT COUNT (creditid ) FROM credit_application");
          sum1=0;
          while (rs5.next()) {
              int kl = rs5.getInt(1);
              sum1 = sum1 + kl;
          }

        button.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent t) {
                String productName =  productNameColumn.getText();
                String sum =  sumColumn.getText();
                String number =  temp;
                String productCost =  productCostColumn.getText();
                String average =  averageColumn.getText();

                String sql = "INSERT INTO credit_application (creditid, creditsum, product, client_profit, productcost, client_id) \n"
                        +   "VALUES (" + (sum1)+",'"+sum+"','"+productName+"','"+average+"','"+productCost+"','"+number+"')";

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
                stage.close();
                //openNewWindow1("newCreditSample.fxml");
            }
        });
    }

     public void openNewWindow1(String FXMLFile) {
        //ChildNode child;
        try {
            URL url = getClass().getResource(FXMLFile);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Pane root =   fxmlLoader.load(url.openStream());
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/automatization/img/icon.png"));
            Scene scene = new Scene(root, 900, 550);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}