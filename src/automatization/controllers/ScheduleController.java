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

import automatization.variables.ScheduleVariable;

public class ScheduleController {

    public  static Connection c = null;
    public   static Statement stmt = null;

    private ObservableList<ScheduleVariable> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<ScheduleVariable> tableUsers;

    @FXML
    private TableColumn<ScheduleVariable, Integer> idColumn;

    @FXML
    private TableColumn<ScheduleVariable, String> loginColumn;

    @FXML
    private TableColumn<ScheduleVariable, String> birthdateColumn;

    @FXML
    private TableColumn<ScheduleVariable, Integer> control;


       // инициализируем форму данными
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<ScheduleVariable, Integer>("id"));
        control.setCellValueFactory(new PropertyValueFactory<ScheduleVariable, Integer>("control"));
        control.setOnEditCommit(new EventHandler<CellEditEvent<ScheduleVariable, Integer>>() {
                                     @Override
                                     public void handle(CellEditEvent<ScheduleVariable, Integer> t) {
                                         ((ScheduleVariable) t.getTableView().getItems().get(
                                                 t.getTablePosition().getRow())
                                         ).setControl(t.getNewValue());
                                         /*int newPrice = t.getNewValue();
                                         int uniqueIdentifier = t.getRowValue().getUniqueIdentifier();*/

                                     }
                                 }
        );
        loginColumn.setCellValueFactory(new PropertyValueFactory<ScheduleVariable, String>("sum"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleVariable, String>("date"));

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
        ResultSet rs = stmt.executeQuery("SELECT  *  FROM grafic_payment ");

        while (rs.next()) {
            int Id = rs.getInt("graficpayment_id");
            String Sum = rs.getString("graficpayment_sum");
            String Date = rs.getString("graficpayment_date");
            int Control = rs.getInt("control");
            usersData.add(new ScheduleVariable(Id, Sum, Date, Control));
        }
    }
}