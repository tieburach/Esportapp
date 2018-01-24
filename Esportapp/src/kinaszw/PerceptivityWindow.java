package kinaszw;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class PerceptivityWindow extends Application {

    int wynik=0;
    Stoper stoper = new Stoper();


    public void ustawcircle(Circle circle){
        circle.setId("circle");
        circle.setFill(Color.TRANSPARENT);
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setId("circleclicked");
                wynik++;
            }
        });
    }

    public void conditionservice(Button wyjscie, Stage stage, Label opis){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(wynik<7){
                    wyjscie.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                            scheduler.shutdown();
                        }
                    });
                }
                else{
                    stoper.stop();
                    scheduler.shutdown();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            opis.setStyle("-fx-text-fill: darkred; -fx-font-size: 15px");
                            if (stoper.getWynik()>10) {
                                opis.setText("Koniec! \nTwoj czas to: " +stoper.getWynik()+"\nco oznacza że masz słabą spostrzegawczosc");
                                ModelWyniki.setSpostrzegawczosc(2);
                                if (stoper.getWynik()>15) {
                                    ModelWyniki.setSpostrzegawczosc(1);
                                }
                            }
                            else if (stoper.getWynik()>5&&stoper.getWynik()<10){
                                opis.setText("Koniec!\nTwoj czas to: " +stoper.getWynik()+"\nco oznacza ze masz średnią spostrzegawczosc");
                                if (stoper.getWynik()>8) ModelWyniki.setSpostrzegawczosc(3);
                                else ModelWyniki.setSpostrzegawczosc(4);
                            }
                            else if (stoper.getWynik()<=5){
                                opis.setText("Koniec \nTwoj czas to: " +stoper.getWynik()+"\nco oznacza ze masz wysoką spostrzegawczosc");
                                ModelWyniki.setSpostrzegawczosc(5);
                            }
                        }
                    });
                }
            }
        },0,200, TimeUnit.MILLISECONDS);}



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
        image.setPrefSize(974,492);
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
        wyjscie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                perceptivityStage.close();

            }
        });

        conditionservice(wyjscie, perceptivityStage, opisspostrzegawczosc);

        Circle circle1 = new Circle(30);
        circle1.setCenterX(570);
        circle1.setCenterY(310);
        ustawcircle(circle1);

        Circle circle2 = new Circle(30);
        circle2.setCenterX(710);
        circle2.setCenterY(250);
        ustawcircle(circle2);


        Circle circle3 = new Circle(30);
        circle3.setCenterX(950);
        circle3.setCenterY(250);
        ustawcircle(circle3);


        Circle circle4 = new Circle(30);
        circle4.setCenterX(715);
        circle4.setCenterY(385);
        ustawcircle(circle4);


        Circle circle5 = new Circle(30);
        circle5.setCenterX(850);
        circle5.setCenterY(45);
        ustawcircle(circle5);


        Circle circle6 = new Circle(30);
        circle6.setCenterX(730);
        circle6.setCenterY(55);
        ustawcircle(circle6);


        Circle circle7 = new Circle(30);
        circle7.setCenterX(585);
        circle7.setCenterY(48);
        ustawcircle(circle7);




        image.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6, circle7);
        layout.getChildren().addAll(image, wyjscie, opisspostrzegawczosc);





        String css = Esport.class.getResource("css\\style2.css").toExternalForm();
        perceptyivityScene.getStylesheets().add(css);
        perceptivityStage.setScene(perceptyivityScene);
        perceptivityStage.show();
    }

}
