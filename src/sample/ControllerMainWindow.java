package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ControllerMainWindow {


    private SurveyWindow surveyWindow;


    public void wyjscie(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void goToReflexWindow(ActionEvent actionEvent) throws IOException {
        Stage ReflexStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ViewReflexWindow.fxml"));
        Scene scene = new Scene(root);
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        ReflexStage.setScene(scene);
        ReflexStage.show();


    }

    public void goToPerceptivityWindow(ActionEvent actionEvent) throws IOException {
        Stage PerceptivityStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ViewPerceptivityWindow.fxml"));
        Scene scene = new Scene(root);
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        PerceptivityStage.setScene(scene);
        PerceptivityStage.show();
    }

    public void goToSurveyWindow(ActionEvent actionEvent) throws Exception {
        if (surveyWindow == null) {
            System.out.println("dupa2");
            Stage surveyStage = new Stage();
            SurveyWindow checkList = new SurveyWindow();
            surveyWindow = checkList;
            surveyStage.setOnCloseRequest(we -> clearSurveyWindow());
            checkList.start(surveyStage);
        }

    }

    public void clearSurveyWindow(){
        surveyWindow=null;
    }
}
