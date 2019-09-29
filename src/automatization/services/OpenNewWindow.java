package automatization.services;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class OpenNewWindow {

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

}
