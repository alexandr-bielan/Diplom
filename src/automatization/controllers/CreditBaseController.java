package automatization.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import automatization.variables.CreditBaseVariable;

import java.sql.*;

public class CreditBaseController {

    public  static Connection c = null;
    public   static Statement stmt = null;

    private ObservableList<CreditBaseVariable> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<CreditBaseVariable> tableUsers;

    @FXML
    private TableColumn<CreditBaseVariable, Integer> idColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> loginColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> birthdateColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> emailColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> passportColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> surnameColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> cityColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> addressColumn;

    @FXML
    private TableColumn<CreditBaseVariable, String> phoneColumn;

    // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

         initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("login"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("birthdate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("email"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("passport"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("surname"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("city"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<CreditBaseVariable, String>("phone"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() throws SQLException, ClassNotFoundException {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "2034489f");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  *  FROM credit ");

            while (rs.next()) {
                int id = rs.getInt("creditId");
                String Sum = rs.getString("creditSum");
                String Product = rs.getString("product");
                String InterestRate = rs.getString("interestRate");
                String dateOfLoan = rs.getString("dateOfLoan");
                String client_Profit = rs.getString("client_profit");
                String productCost = rs.getString("productCost");
                String Repayment = rs.getString("daterepayment");
                String Credittype = rs.getString("credittype");

                usersData.add(new CreditBaseVariable(id, Product, Sum, InterestRate,  Credittype, dateOfLoan, productCost, client_Profit, Repayment));
            }
    }
}