package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PerceptivityWindow extends Application{

    @Override
    public void start(Stage perceptivityStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewPerceptivityWindow.fxml"));
        Scene scene = new Scene(root, 850, 700);
        perceptivityStage.setTitle("Test spostrzegawczości");
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        perceptivityStage.setScene(scene);
        perceptivityStage.show();


    }

}
