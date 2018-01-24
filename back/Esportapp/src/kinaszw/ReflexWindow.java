package kinaszw;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ReflexWindow extends Application {
    private Button osu = new Button();
    private Random random = new Random();
    private int wynik = 0;
    private int wynikzlych = 0;
    private Label iloscdobrych = new Label();
    private Label ilosczlych = new Label();
    private Label podsumowanie = new Label();
    private Button wyjscie = new Button();

    @Override
    public void start(Stage reflexStage) throws Exception {

        Pane layout = new Pane();
        layout.setId("osubackground");
        podsumowanie.setLayoutX(200);
        podsumowanie.setLayoutY(200);
        podsumowanie.setWrapText(true);
        podsumowanie.setPrefSize(400, 200);
        podsumowanie.setWrapText(true);
        podsumowanie.setId("podsumowanie");
        podsumowanie.setVisible(false);
        layout.setPrefSize(850, 700);
        layout.setPadding(new Insets(10));
        reflexStage.setTitle("Test refleksu");

        iloscdobrych.setText("Ilosc dobrych: ");
        ilosczlych.setText("Ilosc zlych: ");
        Label opis = new Label();
        opis.setText("W tym teście na ekranie będą pojawiać się w losowych miejscach punkty, na których naciśnięcie masz krótką chwilę czasu, po czym znikną. Im więcej uda ci się nacisnąć tym lepszy wynik!");
        opis.setLayoutX(180);
        opis.setLayoutY(160);
        opis.setWrapText(true);
        opis.setPrefSize(500, 200);
        opis.setId("opiszadania");

        Button buttonrozpocznij = new Button();
        buttonrozpocznij.setId("wyjscie");
        buttonrozpocznij.setText("ROZPOCZNIJ TEST");
        buttonrozpocznij.setLayoutX(350);
        buttonrozpocznij.setLayoutY(330);
        osu.setVisible(false);
        wynik = 0;
        iloscdobrych.setVisible(false);
        ilosczlych.setVisible(false);
        iloscdobrych.setLayoutY(650);
        iloscdobrych.setLayoutX(200);
        ilosczlych.setLayoutX(450);
        ilosczlych.setLayoutY(650);
        wyjscie.setLayoutY(640);
        wyjscie.setLayoutX(640);
        wyjscie.setId("wyjscie");
        wyjscie.setText("Wroc do menu glownego");
        wyjscie.setOnAction(event -> reflexStage.close());


        layout.getChildren().addAll(buttonrozpocznij, opis, osu, iloscdobrych, ilosczlych, podsumowanie, wyjscie);

        buttonrozpocznij.setOnAction(event -> {
            buttonrozpocznij.setVisible(false);
            opis.setVisible(false);
            ilosczlych.setId("ilosczlych");
            iloscdobrych.setId("iloscdobrych");
            iloscdobrych.setVisible(true);
            ilosczlych.setVisible(true);
            startScheduledExecutorService();
        });

        Scene reflexScene = new Scene(layout);
        String css = Esport.class.getResource("css\\style.css").toExternalForm();
        reflexScene.getStylesheets().add(css);
        reflexStage.setScene(reflexScene);
        reflexStage.show();

    }

    private void startScheduledExecutorService() {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


        scheduler.scheduleAtFixedRate(new Runnable() {
            int counter = 0;

            @Override
            public void run() {
                counter++;
                if (counter <= 20) {
                    Platform.runLater(() -> {
                        osu.setId("osu");
                        osu.setVisible(true);
                        osu.setText("" + counter);
                        osu.setLayoutY(random.nextDouble() * 580);
                        osu.setLayoutX(random.nextDouble() * 770);
                        osu.setOnAction(event -> {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            osu.setVisible(false);
                            wynik++;
                        });
                        wynikzlych = counter - wynik - 1;
                        iloscdobrych.setText("Ilosc dobrych:  " + wynik);
                        ilosczlych.setText("Ilosc zlych:  " + wynikzlych);
                    });
                } else {
                    scheduler.shutdown();
                    Platform.runLater(() -> {
                        osu.setVisible(false);
                        podsumowanie.setVisible(true);
                        wynikzlych = counter - wynik - 1;
                        iloscdobrych.setText("Ilosc dobrych:  " + wynik);
                        ilosczlych.setText("Ilosc zlych:  " + wynikzlych);
                        if (wynik > 16) {
                            podsumowanie.setText("Swietny wynik, masz bardzo dobry refleks!");
                            ModelWyniki.setRefleks(4);
                            if (wynik > 18) ModelWyniki.setRefleks(5);

                        } else if (wynik > 11) {
                            podsumowanie.setText("Twój refleks jest przeciętny.");
                            ModelWyniki.setRefleks(3);
                        } else {
                            podsumowanie.setText("Masz słaby refleks, może warto potrenować?");
                            ModelWyniki.setRefleks(2);
                            if (wynik < 5) ModelWyniki.setRefleks(1);
                        }
                    });
                }
            }
        }, 500, 900, TimeUnit.MILLISECONDS);
    }
}