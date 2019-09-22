package automatization.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import automatization.variables.BankBaseVariable;

public class BankBaseController {

    public  static Connection c = null;
    public   static Statement stmt = null;

    private ObservableList<BankBaseVariable> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<BankBaseVariable> tableUsers;

    @FXML
    private TableColumn<BankBaseVariable, Integer> bankId;

    @FXML
    private TableColumn<BankBaseVariable, String> bankName;

    @FXML
    private TableColumn<BankBaseVariable, String> bankManager;

    @FXML
    private TableColumn<BankBaseVariable, String> bankAddress;

    @FXML
    private TableColumn<BankBaseVariable, Integer> bankPercent;


       // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        initData();

        // устанавливаем тип и значение которое должно храниться в колонке
        bankId.setCellValueFactory(new PropertyValueFactory<BankBaseVariable, Integer>("id"));
        bankName.setCellValueFactory(new PropertyValueFactory<BankBaseVariable, String>("name"));
        bankManager.setCellValueFactory(new PropertyValueFactory<BankBaseVariable,String>("login"));
        bankPercent.setCellValueFactory(new PropertyValueFactory<BankBaseVariable, Integer>("percent"));





        bankAddress.setCellValueFactory(new PropertyValueFactory<BankBaseVariable, String>("address"));


        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    // подготавливаем данные для таблицы
       private void initData() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
        c.setAutoCommit(false);

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT  *  FROM bank ");

        while (rs.next()) {
            int Id = rs.getInt("bank_id");
            System.out.println();
            String Name = rs.getString("bank_name");

            String Direct = rs.getString("bank_manager");
             String Address = rs.getString("bank_address");
            int Percent = rs.getInt("bank_percent");
            usersData.add(new BankBaseVariable(Id, Name, Direct, Address, Percent));
        }
    }
}