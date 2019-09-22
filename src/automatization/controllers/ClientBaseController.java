package automatization.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
// import sample.ClientBaseVariable;
import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import  automatization.variables.ClientBaseVariable;

public class ClientBaseController  {

    public  static Connection c = null;
    public   static Statement stmt = null;

    private ObservableList<ClientBaseVariable> ClientBaseVariableData = FXCollections.observableArrayList();

    @FXML
    private TableView<ClientBaseVariable> tableClientBaseVariable;

    @FXML
    private TableColumn<ClientBaseVariable, Integer> idColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> loginColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> birthdateColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> emailColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> passportColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String>passVidachaColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> surnameColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> cityColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> addressColumn;

    @FXML
    private TableColumn<ClientBaseVariable, String> phoneColumn;

    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("login"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("birthdate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("email"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("passport"));
        passVidachaColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("vidacha"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("city"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<ClientBaseVariable, String>("phone"));

        // заполняем таблицу данными
        tableClientBaseVariable.setItems(ClientBaseVariableData);
    }

    // подготавливаем данные для таблицы
       private void initData() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT  *  FROM client ");

        while (rs.next()) {
            int id = rs.getInt("client_id");
            String Surname = rs.getString("client_surname");
            String Name = rs.getString("client_name");
            String Birthdate = rs.getString("client_birthdate");
            String City = rs.getString("client_city");
            String Address = rs.getString("client_address");
            String Email = rs.getString("client_email");
            String Phone = rs.getString("client_phone");
            String Passport = rs.getString("client_passport");
            String PassportVidacha = rs.getString("client_passvidacha");

            ClientBaseVariableData.add(new ClientBaseVariable(id, Name, Surname, Birthdate,  Passport, PassportVidacha, City, Email, Address, Phone));
        }
    }
}