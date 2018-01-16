package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ResultWindow extends Application {
    private int wynikodpornosc= SurveyWindow.getWynikodpornosc();
    private int wynikasekurant= SurveyWindow.getWynikasekurant();
    private int wynikniezdecydowany=SurveyWindow.getWynikniezdecydowany();
    private int wynikdobrzedzialac=SurveyWindow.getWynikdobrzedzialac();
    Button wyjscie = new Button();


    private final NumberAxis xAxis1 = new NumberAxis(-16, 15, 1);
    private final NumberAxis yAxis1 = new NumberAxis(-11, 12, 1);

    private LineChart<Number,Number> lineChart = new LineChart<>(xAxis1, yAxis1);

    @Override public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Wyniki testu");
        GridPane layout = new GridPane();
            layout.setPrefSize(850,700);
            layout.setPadding(new Insets(10));
            lineChart.setPrefSize(800,330);


        Label opis1 = new Label();
            opis1.setWrapText(true);
            opis1.setPadding(new Insets(10));
            opis1.setPrefSize(340,130);

        Label opis2 = new Label();
            opis2.setPadding(new Insets(10));
            opis2.setWrapText(true);
            opis2.setPrefSize(340,130);

        Label opis3 = new Label();
            opis3.setPadding(new Insets(10));
            opis3.setPrefSize(340,130);
            opis3.setWrapText(true);

        Label opis4 = new Label();
            opis4.setPadding(new Insets(10));
            opis4.setWrapText(true);
            opis4.setPrefSize(340,130);


        SplitPane gora = new SplitPane();
            gora.setMaxSize(720, 130);
            gora.setOrientation(Orientation.HORIZONTAL);
            gora.getItems().addAll(opis1, opis2);
            gora.setDividerPosition(0,0.6f);
        SplitPane dol = new SplitPane();
            dol.setMaxSize(720, 130);
            dol.setOrientation(Orientation.HORIZONTAL);
            dol.getItems().addAll(opis3, opis4);
            dol.setDividerPosition(0, 0.6f);


        if (wynikodpornosc < 5) {
            ModelWyniki.setKoherencja(3);
            opis1.setText("Wynik koherencji (odpornosci na stres):\n" + "Masz wysoką odporność psychiczną, świetnie radzisz sobie z problemami. Kontrolujesz życie, umiesz korzystać ze swojego doświadczenia i pomocy innych ludzi.");
        } else if (wynikodpornosc >= 5 && wynikodpornosc < 9) {
            ModelWyniki.setKoherencja(2);
            opis1.setText("Wynik koherencji (odpornosci na stres):\n"+ "Twoja odporność psychiczna jest na średnim poziomie. MoŜe czas się nad sobą zastanowić – czy nie brakuje Ci pewności siebie? Czy nie za rzadko polegasz na innych?");
        } else {
            ModelWyniki.setKoherencja(1);
            opis1.setText("Wynik koherencji (odpornosci na stres):\n" + "To oznacza niską odporność psychiczną. Zapewne bez przerwy jesteś spięty i aby osiągnąć cel (nawet niezbyt ambitny), musisz mobilizować wszystkie siły. Tak długo nie da się żyć, ciągły stres moze być przyczyną różnych chorób. Głęboko przemyśl, co powinieneś zmienić w sobie i swoim zachowaniu.");
        }

        if (wynikasekurant < 5){
            ModelWyniki.incrementpodejmowanie();
            opis2.setText("Wynik asekuracji (unikania ryzyka):\n"+"Brawo. Twój poziom asekuracji w podejmowaniu decyzji jest w normie. Nie unikasz odpowiedzialności, radzisz sobie z problemami. ");
        } else {
            opis2.setText("Wynik asekuracji (unikania ryzyka):\n"+"Czas zacząć pracować nad sobą. Najprawdopodobniej męczysz się, nie kontrolujesz swojego życia. W opinii Twoich kolegów - trudno się z Tobą współpracuje. Samodzielnie też nie pracujesz efektywnie.");
        }

        if (wynikniezdecydowany < 5){
            ModelWyniki.incrementpodejmowanie();
            opis3.setText("Wynik ambiwalentności (niezdecydowania):\n"+"Twój poziom niezdecydowania jest w normie. Oczywiście, zdarza Ci się zmienić plany, ale robisz to wtedy, gdy jest to konieczne.");
        } else {
            opis3.setText("Wynik ambiwalentności (niezdecydowania):\n"+"Często zmieniasz swoje zdanie. Masz ambitne plany, ale ich nie realizujesz. Wpadasz w panikę, gdy czeka Cię nowe zadanie. W opinii kolegów nie można na Tobie polegać. Uważaj, bo ludzie wiedząc, że nie można na Ciebie liczyć, będą się od Ciebie odsuwać. Zapewne nie cieszy Cię to, co robisz, wykonujesz to mechanicznie.");
        }

        if (wynikdobrzedzialac < 8){
            opis4.setText("Wynik czy potrafisz dzialać: \n"+"Nie wykorzystujesz swoich możliwości. Stać Cię na więcej ryzyka, bo potrafisz radzić sobie z problemami i zapewne wybrnąłbyś z każdej opresji. ");
        } else {
            ModelWyniki.incrementpodejmowanie();
            opis4.setText("Wynik czy potrafisz dzialać: \n"+"Masz bardzo wysoką odporność psychiczną, a swoim zdecydowaniem dajesz innym poczucie bezpieczeństwa. Odwagi do działania dodaje Ci ciekawość świata. Podejmowanie decyzji sprawia Ci satysfakcję. Potrafisz zaplanować pracę, własne wydatki i Ŝycie. Zapewne chętnie będą Cię zatrudniać na kierowniczych stanowiskach.");
        }



        lineChart.getData().clear();


        XYChart.Series<Number, Number> seriesmoje1 = new XYChart.Series<>();
            seriesmoje1.getData().add(new XYChart.Data(-wynikdobrzedzialac,0));
            seriesmoje1.getData().add(new XYChart.Data(0, wynikniezdecydowany));
            seriesmoje1.getData().add(new XYChart.Data(wynikodpornosc,0));

        XYChart.Series<Number, Number> seriesmoje2 = new XYChart.Series<>();
            seriesmoje2.getData().add(new XYChart.Data(-wynikdobrzedzialac,0));
            seriesmoje2.getData().add(new XYChart.Data(0, -wynikasekurant));
            seriesmoje2.getData().add(new XYChart.Data(wynikodpornosc,0));

        XYChart.Series<Number, Number> serieskobiety1 = new XYChart.Series<>();
            int wynikdobrzedzialackobiety = 8;
            serieskobiety1.getData().add(new XYChart.Data(-wynikdobrzedzialackobiety,0));
            int wynikniezdecydowanykobiety = 7;
            serieskobiety1.getData().add(new XYChart.Data(0, wynikniezdecydowanykobiety));
            int wynikodpornosckobiety = 7;
            serieskobiety1.getData().add(new XYChart.Data(wynikodpornosckobiety,0));

        XYChart.Series<Number, Number> serieskobiety2 = new XYChart.Series<>();
            serieskobiety2.getData().add(new XYChart.Data(-wynikdobrzedzialackobiety,0));
            int wynikasekurantkobiety = 4;
            serieskobiety2.getData().add(new XYChart.Data(0, -wynikasekurantkobiety));
            serieskobiety2.getData().add(new XYChart.Data(wynikodpornosckobiety,0));

        XYChart.Series<Number, Number> seriesfaceci1 = new XYChart.Series<>();
            int wynikdobrzedzialacfaceci = 9;
            seriesfaceci1.getData().add(new XYChart.Data(-wynikdobrzedzialacfaceci,0));
            int wynikniezdecydowanyfaceci = 4;
            seriesfaceci1.getData().add(new XYChart.Data(0, wynikniezdecydowanyfaceci));
            int wynikodpornoscfaceci = 5;
            seriesfaceci1.getData().add(new XYChart.Data(wynikodpornoscfaceci,0));
        XYChart.Series<Number, Number> seriesfaceci2 = new XYChart.Series<>();
            seriesfaceci2.getData().add(new XYChart.Data(-wynikdobrzedzialacfaceci,0));
            int wynikasekurantfaceci = 2;
            seriesfaceci2.getData().add(new XYChart.Data(0, -wynikasekurantfaceci));
            seriesfaceci2.getData().add(new XYChart.Data(wynikodpornoscfaceci,0));


        lineChart.setLegendVisible(false);
        lineChart.getData().addAll(seriesmoje1, seriesmoje2, serieskobiety1, serieskobiety2, seriesfaceci1, seriesfaceci2);
        ImageView imageView = new ImageView();
        Image image1 = new Image(new FileInputStream("C:\\Users\\Tieburach\\IdeaProjects\\proba2\\src\\sample\\wyniki.png"));
        imageView.setImage(image1);
        imageView.setFitHeight(70);
        imageView.setFitWidth(220);

        layout.add(gora,1,1);
        layout.add(dol,1,2);
        layout.setHalignment(gora, HPos.CENTER);
        layout.setHalignment(dol, HPos.CENTER);
        layout.add(lineChart,1,3);
        layout.add(imageView,1,4);
        layout.setHalignment(imageView, HPos.CENTER);

        wyjscie.setText("Wroc do menu glownego");
        wyjscie.setId("wyjscie");
        wyjscie.setLayoutX(800);
        wyjscie.setLayoutY(680);
        wyjscie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        layout.add(wyjscie,1,4);
        layout.setHalignment(wyjscie, HPos.RIGHT);


        Scene scene = new Scene(layout);
;
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }}
