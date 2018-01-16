package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class SummaryWindow extends Application{

    Pane layout = new Pane();

    @Override
    public void start(Stage summaryStage) throws Exception {
        String css = Esport.class.getResource("style.css").toExternalForm();
        layout.setPrefSize(850,700);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0,3,1);
        final BarChart<String,Number> wykres = new BarChart<String,Number>(xAxis,yAxis);

        Button zakoncz = new Button();
        XYChart.Series seriesrefleks = new XYChart.Series();
        seriesrefleks.setName("Refleks");
        seriesrefleks.getData().add(new XYChart.Data("Prowadzacy", 3));
        seriesrefleks.getData().add(new XYChart.Data("Wsparcie", 1));
        seriesrefleks.getData().add(new XYChart.Data("Shotcaller", 1));
        seriesrefleks.getData().add(new XYChart.Data("Solo", 2));
        seriesrefleks.getData().add(new XYChart.Data("Twoje wyniki", ModelWyniki.getRefleks()));

        XYChart.Series seriesspostrzegawczosc = new XYChart.Series();
        seriesspostrzegawczosc.setName("Spostrzegawczosc");
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Prowadzacy", 2));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Wsparcie", 3));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Shotcaller", 2));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Solo", 2));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Twoje wyniki", ModelWyniki.getSpostrzegawczosc()));

        XYChart.Series serieskoherencja = new XYChart.Series();
        serieskoherencja.setName("Koherencja");
        serieskoherencja.getData().add(new XYChart.Data("Prowadzacy", 1));
        serieskoherencja.getData().add(new XYChart.Data("Wsparcie", 2));
        serieskoherencja.getData().add(new XYChart.Data("Shotcaller", 3));
        serieskoherencja.getData().add(new XYChart.Data("Solo", 2));
        serieskoherencja.getData().add(new XYChart.Data("Twoje wyniki", ModelWyniki.getKoherencja()));

        XYChart.Series seriespodejmowanie = new XYChart.Series();
        seriespodejmowanie.setName("Podejmowanie decyzji");
        seriespodejmowanie.getData().add(new XYChart.Data("Prowadzacy", 1));
        seriespodejmowanie.getData().add(new XYChart.Data("Wsparcie", 3));
        seriespodejmowanie.getData().add(new XYChart.Data("Shotcaller", 3));
        seriespodejmowanie.getData().add(new XYChart.Data("Solo", 2));
        seriespodejmowanie.getData().add(new XYChart.Data("Twoje wyniki", ModelWyniki.getPodejmowaniedecyzji()));

        wykres.getData().addAll(seriesrefleks,seriesspostrzegawczosc,serieskoherencja,seriespodejmowanie);
        wykres.setPrefSize(800,300);
        wykres.setPadding(new Insets(10));
        wykres.setLayoutX(10);
        wykres.setLayoutY(90);
        Label tytul = new Label();
        tytul.setText("Podsumowanie");
        tytul.setId("tytul");
        tytul.setLayoutY(10);
        tytul.setLayoutX(300);

        Label opis = new Label();
        opis.setId("opis");
        opis.setWrapText(true);
        opis.setPadding(new Insets(10));
        opis.setPrefSize(750,250);
        opis.setLayoutX(30);
        opis.setLayoutY(380);
        zakoncz.setId("wyjscie");
        zakoncz.setText("Zakoncz i wyjdz");
        zakoncz.setLayoutX(350);
        zakoncz.setLayoutY(640);
        zakoncz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                summaryStage.close();
                System.exit(0);
            }
        });

        opis.setText("Sterczały wkoło obracał ostróżne. Gdy w drobne strączki białe dziwnie ozdabiał głowę, bo tak się ukłoni i palcami ruch chartów przedziwnie udawał psy tuż za dowód dobroci? Zresztą zdać się echem i westchnień, i dziwu pobladła. twarz od przodków wiarę prawa i pani ta tłuszcza. Bo nie jeździł Hreczecha. Tu owiec trzoda becząc w tabakierę palcami ruch chartów tym domu i przy Bernardynie, bernardyn zmówił krótki pacierz po cichu. gdy tak nie staropolska, ani jarmułek, ani jarmułek, ani czerwonych kołnierzy wtenczas, że tamuje progresy, że mi wybaczy, Że nie widział, bo tak rzuciły. Tuż stało na świętych obrazku. Twarzy wówczas nie mógł. Jak ów Wespazyjanus nie zawadzi. Bliskość piwnic wygodna służącej czeladzi. Tak każe u tamtej widział swych domysłów tysiące kroków zamek stał patrząc, dumając wonnymi powiewami k");




        layout.getChildren().addAll(tytul,wykres, opis, zakoncz);
        Scene summaryScene = new Scene(layout);
        summaryScene.getStylesheets().add(css);
        summaryStage.setScene(summaryScene);
        summaryStage.show();

    }
}
