package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ReflexWindow extends Application{
    Button osu = new Button();
    Random random = new Random();
    int wynik = 0;
    int wynikzlych = 0;
    Label iloscdobrych = new Label();
    Label ilosczlych = new Label();
    Label podsumowanie = new Label();
    Button wyjscie = new Button();

    @Override
    public void start(Stage reflexStage) throws Exception {

        Pane layout = new Pane();
        layout.setId("osubackground");
        podsumowanie.setLayoutX(350);
        podsumowanie.setLayoutY(300);
        podsumowanie.setWrapText(true);
        podsumowanie.setId("podsumowanie");
        podsumowanie.setVisible(false);
        layout.setPrefSize(850,700);
        layout.setPadding(new Insets(10));
        reflexStage.setTitle("Test refleksu");

        iloscdobrych.setText("Ilosc dobrych: ");
        ilosczlych.setText("Ilosc zlych: ");
        Label opis = new Label();
            opis.setText("W tym teście na ekranie będą pojawiać się w losowych miejscach punkty, na których naciśnięcie masz krótką chwilę czasu, po czym znikną. Im więcej uda ci się nacisnąć tym lepszy wynik!");
            opis.setLayoutX(200);
            opis.setLayoutY(150);
            opis.setWrapText(true);
            opis.setPrefSize(500,200);
            opis.setId("opiszadania");

        Button buttonrozpocznij = new Button();
            buttonrozpocznij.setText("ROZPOCZNIJ TEST");
            buttonrozpocznij.setLayoutX(350);
            buttonrozpocznij.setLayoutY(330);
        osu.setVisible(false);
        wynik=0;
        iloscdobrych.setVisible(false);
        ilosczlych.setVisible(false);
        iloscdobrych.setLayoutY(650);
        iloscdobrych.setLayoutX(200);
        ilosczlych.setLayoutX(450);
        ilosczlych.setLayoutY(650);
        wyjscie.setLayoutY(620);
        wyjscie.setLayoutX(620);
        wyjscie.setId("wyjscie");
        wyjscie.setText("Wroc do menu glownego");
        wyjscie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reflexStage.close();
            }
        });


        layout.getChildren().addAll(buttonrozpocznij, opis, osu, iloscdobrych, ilosczlych, podsumowanie, wyjscie);

        buttonrozpocznij.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonrozpocznij.setVisible(false);
                opis.setVisible(false);
                ilosczlych.setId("ilosczlych");
                iloscdobrych.setId("iloscdobrych");
                iloscdobrych.setVisible(true);
                ilosczlych.setVisible(true);
                startScheduledExecutorService();
            }
        });

        Scene reflexScene = new Scene(layout);
        String css = Esport.class.getResource("style.css").toExternalForm();
        reflexScene.getStylesheets().add(css);
        reflexStage.setScene(reflexScene);
        reflexStage.show();

    }
    private void startScheduledExecutorService(){

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                counter++;
                if (counter <= 2) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            osu.setId("osu");
                            osu.setVisible(true);
                            osu.setText(""+ counter);
                            osu.setLayoutY(random.nextDouble()*650);
                            osu.setLayoutX(random.nextDouble()*800);
                            osu.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    osu.setVisible(false);
                                    wynik++;
                               }
                            });
                            wynikzlych=counter-wynik-1;
                            iloscdobrych.setText("Ilosc dobrych:  "+wynik);
                            ilosczlych.setText("Ilosc zlych:  "+wynikzlych);
                        }
                    });
                } else {
                    scheduler.shutdown();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            osu.setVisible(false);
                            podsumowanie.setVisible(true);
                            wynikzlych=counter-wynik-1;
                            iloscdobrych.setText("Ilosc dobrych:  "+wynik);
                            ilosczlych.setText("Ilosc zlych:  "+wynikzlych);
                            if (wynik==10){
                                podsumowanie.setText("SWIETNY WYNIK");
                            }
                            else if (wynik> 5){
                                podsumowanie.setText("SREDNI WYNIK");
                            }
                            else {
                                podsumowanie.setText("SLABY WYNIK");
                            }
                        }
                    });
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}

