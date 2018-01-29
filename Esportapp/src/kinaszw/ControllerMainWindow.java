package kinaszw;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControllerMainWindow {


    private static ReflexWindow reflexWindow;
    private static PerceptivityWindow perceptivityWindow;
    private static SurveyWindow surveyWindow;
    private static SummaryWindow summaryWindow;


    @FXML
    Button summaryButton, reflexButton, perceptivityButton, surveyButton;


    private void sprawdzCzyKoniec() {
        if (reflexWindow != null) {
            reflexButton.setId("buttonzrobiony");
        }
        if (surveyWindow != null) {
            surveyButton.setId("buttonzrobiony");
        }
        if (perceptivityWindow != null) {
            perceptivityButton.setId("buttonzrobiony");
        }
        if (surveyWindow != null && reflexWindow != null && perceptivityWindow != null) {
            summaryButton.setVisible(true);

        }
    }

    static String jakieZostaly() {
        String wynik = "";

        if (reflexWindow == null) {
            wynik = wynik + " Refleks, ";
        }
        if (perceptivityWindow == null) {
            wynik = wynik + " Spostrzegawczość,";
        }
        if (surveyWindow == null) {
            wynik = wynik + " Test osobowości. ";
        }
        if (surveyWindow != null && reflexWindow != null && perceptivityWindow != null) {
            wynik = wynik + " Możesz przejść do podsumowania.";
        }
        return wynik;
    }

    public void wyjscie() {
        System.exit(0);
    }

    public void goToReflexWindow() throws Exception {
        if (reflexWindow == null) {
            Stage reflexStage = new Stage();
            reflexWindow = new ReflexWindow();
            try {
                reflexWindow.start(reflexStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sprawdzCzyKoniec();
        } else {
            wyswietlblad();
        }
    }

    public void goToPerceptivityWindow() throws Exception {
        if (perceptivityWindow == null) {
            Stage perceptivityStage = new Stage();
            perceptivityWindow = new PerceptivityWindow();
            try {
                perceptivityWindow.start(perceptivityStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sprawdzCzyKoniec();
        } else {
            wyswietlblad();
        }

    }

    public void goToSurveyWindow() throws Exception {
        if (surveyWindow == null) {
            Stage surveyStage = new Stage();
            surveyWindow = new SurveyWindow();
            surveyWindow.start(surveyStage);
            sprawdzCzyKoniec();
        } else {
            wyswietlblad();
        }

    }

    public void goToSummaryWindow() throws Exception {
        if (summaryWindow == null) {
            Stage summaryStage = new Stage();
            summaryWindow = new SummaryWindow();
            summaryWindow.start(summaryStage);
        }

    }

    private void wyswietlblad() throws Exception {
        Stage errorStage = new Stage();
        ErrorWindow errorWindow = new ErrorWindow();
        errorWindow.start(errorStage);
    }

}


