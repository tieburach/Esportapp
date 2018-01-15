package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ControllerMainWindow {


    public SurveyWindow surveyWindow;


    public void wyjscie(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void goToReflexWindow(ActionEvent actionEvent) throws IOException {
        Stage reflexStage = new Stage();
        ReflexWindow reflexWindow = new ReflexWindow();
        try {
            reflexWindow.start(reflexStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToPerceptivityWindow(ActionEvent actionEvent) throws Exception {
        Stage perceptivityStage = new Stage();
        PerceptivityWindow perceptivityWindow = new PerceptivityWindow();
        try {
            perceptivityWindow.start(perceptivityStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToSurveyWindow(ActionEvent actionEvent) throws Exception {
        if (surveyWindow == null) {
            Stage surveyStage = new Stage();
            SurveyWindow checkList = new SurveyWindow();
            surveyWindow = checkList;
            checkList.start(surveyStage);
        }

    }}


