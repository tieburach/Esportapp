package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Esport extends Application {

    @Override
    public void start(Stage MainWindowStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewMainWindow.fxml"));
        Scene scene = new Scene(root);
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        MainWindowStage.setScene(scene);
        MainWindowStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
