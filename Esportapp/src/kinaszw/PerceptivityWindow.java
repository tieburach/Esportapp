package kinaszw;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class PerceptivityWindow extends Application {

    private Stoper stoper = new Stoper();
    private boolean flaga1, flaga2, flaga3, flaga4, flaga5, flaga6, flaga7 = false;

    private void ustawcircle(Circle circle) {
        circle.setId("circle");
        circle.setFill(Color.TRANSPARENT);
    }

    private void conditionservice(Button wyjscie, Stage stage, Label opis) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if (flaga1 && flaga2 && flaga3 && flaga4 && flaga5 && flaga6 && flaga7) {
                stoper.stop();
                scheduler.shutdown();
                Platform.runLater(() -> {
                    opis.setStyle("-fx-text-fill: darkred; -fx-font-size: 15px");
                    if (stoper.getWynik() > 80) {
                        opis.setText("Koniec! \nTwoj czas to: " + stoper.getWynik() + "\nco oznacza że masz słabą spostrzegawczosc");
                        ModelWyniki.setSpostrzegawczosc(2);
                        if (stoper.getWynik() > 100) {
                            ModelWyniki.setSpostrzegawczosc(1);
                        }
                    } else if (stoper.getWynik() > 40 && stoper.getWynik() < 80) {
                        opis.setText("Koniec!\nTwoj czas to: " + stoper.getWynik() + "\nco oznacza ze masz średnią spostrzegawczosc");
                        ModelWyniki.setSpostrzegawczosc(3);
                    } else if (stoper.getWynik() <= 30) {
                        opis.setText("Koniec \nTwoj czas to: " + stoper.getWynik() + "\nco oznacza ze masz wysoką spostrzegawczosc");
                        ModelWyniki.setSpostrzegawczosc(4);
                        if (stoper.getWynik() < 25) {
                            ModelWyniki.setSpostrzegawczosc(5);
                        }
                    }
                });
            } else {
                wyjscie.setOnAction(event -> {
                    stage.close();
                    scheduler.shutdown();
                });
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }


    @Override
    public void start(Stage perceptivityStage) throws Exception {
        Pane layout = new Pane();
        Button wyjscie = new Button();
        Label opisspostrzegawczosc = new Label();
        Scene perceptyivityScene = new Scene(layout, 1000, 700);
        perceptivityStage.setTitle("Test spostrzegawczości");

        stoper.start();
        Pane image = new Pane();
        image.setId("znajdzroznice");
        image.setPrefSize(974, 492);
        image.setLayoutX(13);
        image.setLayoutY(50);

        wyjscie.setLayoutY(620);
        wyjscie.setLayoutX(780);

        opisspostrzegawczosc.setLayoutX(40);
        opisspostrzegawczosc.setLayoutY(600);
        opisspostrzegawczosc.setWrapText(true);
        opisspostrzegawczosc.setPrefSize(700, 70);
        opisspostrzegawczosc.setId("opiszakoncz");
        opisspostrzegawczosc.setText("W tym teście musisz znaleźć jak najszybciej wszystkie (7) róznice pomiędzy obrazkami. Zaznaczaj je na prawym obrazku. Gdy zaznaczysz juz wszystkie, aplikacja pokaże twój czas.");
        wyjscie.setId("wyjscie");
        wyjscie.setText("Wroc do menu glownego");
        wyjscie.setOnAction(event -> perceptivityStage.close());

        conditionservice(wyjscie, perceptivityStage, opisspostrzegawczosc);

        Circle circle1 = new Circle(30);
        circle1.setCenterX(570);
        circle1.setCenterY(310);
        ustawcircle(circle1);
        circle1.setOnMouseClicked(event -> {
            flaga1 = true;
            circle1.setId("circleclicked");
        });

        Circle circle2 = new Circle(30);
        circle2.setCenterX(710);
        circle2.setCenterY(250);
        ustawcircle(circle2);
        circle2.setOnMouseClicked(event -> {
            flaga2 = true;
            circle2.setId("circleclicked");
        });

        Circle circle3 = new Circle(30);
        circle3.setCenterX(950);
        circle3.setCenterY(250);
        ustawcircle(circle3);
        circle3.setOnMouseClicked(event -> {
            flaga3 = true;
            circle3.setId("circleclicked");
        });

        Circle circle4 = new Circle(30);
        circle4.setCenterX(715);
        circle4.setCenterY(385);
        ustawcircle(circle4);
        circle4.setOnMouseClicked(event -> {
            flaga4 = true;
            circle4.setId("circleclicked");
        });

        Circle circle5 = new Circle(30);
        circle5.setCenterX(850);
        circle5.setCenterY(45);
        ustawcircle(circle5);
        circle5.setOnMouseClicked(event -> {
            flaga5 = true;
            circle5.setId("circleclicked");
        });

        Circle circle6 = new Circle(30);
        circle6.setCenterX(730);
        circle6.setCenterY(55);
        ustawcircle(circle6);
        circle6.setOnMouseClicked(event ->
        {
            flaga6 = true;
            circle6.setId("circleclicked");
        });

        Circle circle7 = new Circle(30);
        circle7.setCenterX(585);
        circle7.setCenterY(48);
        ustawcircle(circle7);
        circle7.setOnMouseClicked(event -> {
            flaga7 = true;
            circle7.setId("circleclicked");
        });


        image.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6, circle7);
        layout.getChildren().addAll(image, wyjscie, opisspostrzegawczosc);


        String css = Esport.class.getResource("css\\style2.css").toExternalForm();
        perceptyivityScene.getStylesheets().add(css);
        perceptivityStage.setScene(perceptyivityScene);
        perceptivityStage.show();
    }

}
