package automatization;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import java.net.URL;
import java.io.IOException;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage)    {
        BorderPane root1 = new BorderPane();

        MenuBar menuBar = new MenuBar();
        menuBar.setPadding(new Insets(10, 10, 10, 10));
        root1.setTop(menuBar);
        // Set margin for top area.
        BorderPane.setMargin(menuBar, new Insets(87, 10, 10, 60));
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root1.setTop(menuBar);

        Menu fileMenu1 = new Menu("      ");

        // File menu - new, save, exit

        Menu fileMenu = new Menu("Главная         ");

        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(arg0 -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        Menu fileMenu2 = new Menu("Кредиты         ");
        MenuItem newMenuItem1 = new MenuItem("Оформить новый кредит");
        MenuItem saveMenuItem1 = new MenuItem("База кредитов       ");
        MenuItem newMenuItem4 = new MenuItem("Поиск кредитов       ");
        MenuItem creditProgramMenuItem = new MenuItem("Виды кредитных программ");
        newMenuItem1.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/NewCreditForm.fxml");
            }
        });
        saveMenuItem1.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/CreditBaseForm.fxml");
            }
        });
        newMenuItem4.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/SearchLoanForm.fxml");
            }
        });
        creditProgramMenuItem.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/CreditProgramForm.fxml");
            }
        });


        fileMenu2.getItems().addAll(newMenuItem1, saveMenuItem1, newMenuItem4,creditProgramMenuItem);

        Menu sqlMenu = new Menu("Клиенты            ");
        MenuItem newMenuItem3 = new MenuItem("Добавить нового клиента");
        newMenuItem3.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/NewClientForm.fxml");
            }
        });
        MenuItem searchClient = new MenuItem("Поиск клиента");
        searchClient.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/SearchClientForm.fxml");
            }
        });

        MenuItem oracleItem = new  MenuItem("Клиентская база");
        oracleItem.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("/automatization/forms/ClientBaseForm.fxml");
            }
        });

        sqlMenu.getItems().addAll(newMenuItem3, oracleItem,searchClient,
                new SeparatorMenuItem());

        Menu scoringMenu = new Menu("Управление     ");
        MenuItem newMenuItem5 = new MenuItem("Скоринг");
        newMenuItem5.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/ScoringForm.fxml");
            }
        });
        MenuItem newMenuItem6 = new MenuItem("Расчет ежемесячного платежа");
        newMenuItem6.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/SearchScheduleForm.fxml");
            }
        });
        MenuItem newMenuItem7 = new MenuItem("Выбор банка");
        newMenuItem7.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/BankBaseForm.fxml");
            }
        });
        scoringMenu.getItems().addAll(newMenuItem5, newMenuItem6, newMenuItem7);

        menuBar.getMenus().addAll(fileMenu1, fileMenu, fileMenu2, sqlMenu, scoringMenu);

        // create a resizable pane.
        final StackPane left = new StackPane();
        left.setStyle("-fx-background-color: lightblue;");
        left.setPrefWidth(500);

        // create a pane with a 300 pixel fixed width.
        final StackPane right = new StackPane();
        right.setStyle("-fx-background-color: palegreen;");
        right.setPrefWidth(300);
        right.setMinWidth(StackPane.USE_PREF_SIZE);
        right.setMaxWidth(StackPane.USE_PREF_SIZE);

        // layout the left and right pane.
        final HBox layout = new HBox(left, right);

        // grow the left pane width to fill available space.
        HBox.setHgrow(left, Priority.ALWAYS);

        // LEFT
        Button btnLeft = new Button(" ");
        btnLeft.setStyle("-fx-background-color: #4e98bb");
        btnLeft.setPadding(new Insets(615, 75, 15, 15));
        root1.setLeft(btnLeft);
        // Set margin for left area.
        BorderPane.setMargin(btnLeft, new Insets(-125, 10,  110, 0));

        // CENTER
        Button btnCenter = new Button("Виды кредитных программ");
        btnCenter.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/CreditProgramForm.fxml");
            }
        });
        btnCenter.setPadding(new Insets( 5, 35,  5, 35));
        root1.setCenter(btnCenter);
        BorderPane.setMargin(btnCenter, new Insets(80, 10, 510, - 80));
        // Alignment.
        BorderPane.setAlignment(btnCenter, Pos.BOTTOM_CENTER);
        Font font = new Font(15); //Button font's size should increase to 40
        btnCenter.setFont(font);
        Image img = new Image("automatization/img/11111.jpg");
        ImageView imageView = new ImageView(img);
        root1.getChildren().addAll(imageView);

        BackgroundImage myBI= new BackgroundImage(new Image("automatization/img/22222.png",832,432,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        root1.setBackground(new Background(myBI));

        // RIGHT
        Button btnRight = new Button("Поиск по договорам");
        btnRight.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent t) {
                openNewWindow("forms/SearchLoanForm.fxml");
            }
        });
        btnRight.setPadding(new Insets(5, 55, 5, 55));
        root1.setRight(btnRight);
        // Set margin for right area.
        BorderPane.setMargin(btnRight, new Insets(90, 110, 10, 10));
        btnRight.setFont(font);

        Scene scene = new Scene(root1,900, 550, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("automatization/img/icon.png"));
        primaryStage.show();
    }

    public void openNewWindow(String FXMLFile) {
        //ChildNode child;
        try {
            URL url = getClass().getResource(FXMLFile);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Pane root =   fxmlLoader.load(url.openStream());

            Stage stage = new Stage();

            stage.getIcons().add(new Image("automatization/img/icon.png"));
            Scene scene = new Scene(root, 900, 550);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}