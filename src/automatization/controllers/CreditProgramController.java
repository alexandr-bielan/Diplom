package automatization.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import automatization.variables.CreditProgramVariable;

public class CreditProgramController {

    public  static Connection c = null;
    public   static Statement stmt = null;

    private ObservableList<CreditProgramVariable> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<CreditProgramVariable> tableUsers;

    @FXML
    private TableColumn<CreditProgramVariable, Integer> idColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String> loginColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String> birthdateColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String> emailColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String> passportColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String>passVidachaColumn;

    @FXML
    private TableColumn<CreditProgramVariable, String> surnameColumn;

    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
       idColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, Integer>("id"));
       loginColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("login"));
       birthdateColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("birthdate"));
       emailColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("email"));
       passportColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("passport"));
       passVidachaColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("vidacha"));
       surnameColumn.setCellValueFactory(new PropertyValueFactory<CreditProgramVariable, String>("surname"));

       // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    // подготавливаем данные для таблицы
       private void initData() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT  *  FROM credit_program");

        while (rs.next()) {
            int id = rs.getInt("creditid");
            String Surname = rs.getString("credittypecolumn");
            String Name = rs.getString("term");
            String Birthdate = rs.getString("creditpercentcolumn");
            String Email = rs.getString("minsumcolumn");
            String Passport = rs.getString("maxsumcolumn");
            String PassportVidacha = rs.getString("comission");

            usersData.add(new CreditProgramVariable(id, Name, Surname, Birthdate, Email, Passport, PassportVidacha));
        }
    }
}