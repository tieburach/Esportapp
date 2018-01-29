package kinaszw;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SummaryWindow extends Application {

    private Pane layout = new Pane();

    @Override
    public void start(Stage summaryStage) throws Exception {
        String css = Esport.class.getResource("css\\style.css").toExternalForm();
        layout.setPrefSize(850, 700);
        Button zakoncz = new Button();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 5, 1);
        final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis(0, 5, 1);
        final BarChart<String, Number> wykres = new BarChart<>(xAxis, yAxis);
        final BarChart<String, Number> wykrestwoj = new BarChart<>(xAxis2, yAxis2);

        int refleks, spostrzegawczosc, koherencja, decyzje;
        refleks = ModelWyniki.getRefleks();
        spostrzegawczosc = ModelWyniki.getSpostrzegawczosc();
        koherencja = ModelWyniki.getKoherencja();
        decyzje = ModelWyniki.getPodejmowaniedecyzji();

        XYChart.Series seriestwojrefleks = new XYChart.Series();
        seriestwojrefleks.setName("Refleks");
        seriestwojrefleks.getData().add(new XYChart.Data("Twoje wyniki", refleks));
        XYChart.Series seriestwojspostrzegawczosc = new XYChart.Series();
        seriestwojspostrzegawczosc.setName("Spostrzegawczość");
        seriestwojspostrzegawczosc.getData().add(new XYChart.Data("Twoje wyniki", spostrzegawczosc));
        XYChart.Series seriestwojkoherencja = new XYChart.Series();
        seriestwojkoherencja.setName("Koherencja");
        seriestwojkoherencja.getData().add(new XYChart.Data("Twoje wyniki", koherencja));
        XYChart.Series seriestwojpodejmowaniedecyzji = new XYChart.Series();
        seriestwojpodejmowaniedecyzji.setName("Podejmowanie decyzji");
        seriestwojpodejmowaniedecyzji.getData().add(new XYChart.Data("Twoje wyniki", decyzje));

        XYChart.Series seriesrefleks = new XYChart.Series();
        seriesrefleks.setName("Refleks");


        seriesrefleks.getData().add(new XYChart.Data("Prowadzacy", 5));
        seriesrefleks.getData().add(new XYChart.Data("Wsparcie", 2));
        seriesrefleks.getData().add(new XYChart.Data("Shotcaller", 2));
        seriesrefleks.getData().add(new XYChart.Data("Solo", 3));

        XYChart.Series seriesspostrzegawczosc = new XYChart.Series();
        seriesspostrzegawczosc.setName("Spostrzegawczosc");
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Prowadzacy", 4));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Wsparcie", 5));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Shotcaller", 3));
        seriesspostrzegawczosc.getData().add(new XYChart.Data("Solo", 3));

        XYChart.Series serieskoherencja = new XYChart.Series();
        serieskoherencja.setName("Koherencja");
        serieskoherencja.getData().add(new XYChart.Data("Prowadzacy", 2));
        serieskoherencja.getData().add(new XYChart.Data("Wsparcie", 4));
        serieskoherencja.getData().add(new XYChart.Data("Shotcaller", 5));
        serieskoherencja.getData().add(new XYChart.Data("Solo", 3));

        XYChart.Series seriespodejmowanie = new XYChart.Series();
        seriespodejmowanie.setName("Podejmowanie decyzji");
        seriespodejmowanie.getData().add(new XYChart.Data("Prowadzacy", 1));
        seriespodejmowanie.getData().add(new XYChart.Data("Wsparcie", 3));
        seriespodejmowanie.getData().add(new XYChart.Data("Shotcaller", 5));
        seriespodejmowanie.getData().add(new XYChart.Data("Solo", 3));


        wykres.getData().addAll(seriesrefleks, seriesspostrzegawczosc, serieskoherencja, seriespodejmowanie);
        wykres.setPrefSize(800, 300);
        wykres.setPadding(new Insets(10));
        wykres.setLayoutX(10);
        wykres.setLayoutY(40);
        wykres.getYAxis().setTickLabelsVisible(false);
        wykres.getYAxis().setTickMarkVisible(false);

        wykrestwoj.getData().addAll(seriestwojrefleks, seriestwojspostrzegawczosc, seriestwojkoherencja, seriestwojpodejmowaniedecyzji);
        wykrestwoj.setPrefSize(355, 300);
        wykrestwoj.setPadding(new Insets(10));
        wykrestwoj.setLayoutX(10);
        wykrestwoj.setLayoutY(330);
        wykrestwoj.getYAxis().setTickLabelsVisible(false);
        wykrestwoj.getYAxis().setTickMarkVisible(false);


        Label tytul = new Label();
        tytul.setText("Podsumowanie");
        tytul.setId("tytul");
        tytul.setLayoutY(10);
        tytul.setLayoutX(300);

        Label opis = new Label();
        opis.setWrapText(true);
        opis.setId("opis");
        opis.setPrefSize(400, 250);
        opis.setLayoutX(370);
        opis.setLayoutY(330);

        String wynik = "Twoja proponowana pozycja to:\n";
        if (refleks >= 4 && spostrzegawczosc >= 4 && decyzje >= 4 && koherencja >= 4) {
            wynik = wynik + "Wszędzie. \nMożesz grać na każdej pozycji, test przeszedłeś na znakomitych wynikach. Sam musisz zdecydować co najbardziej ci odpowiada.";
        } else if (refleks > 3 && spostrzegawczosc >= 2) {
            wynik = wynik + "Prowadzacy. \nMasz bardzo dobry refleks i spostrzegawczosc. Nadawałbyś się świetnie na prowadzącego w drużynie. To rola, w której trzeba pokazywać dużo umiejętności indywidualnych. Ci gracze z reguły są najbardziej widowiskowi.";
        } else if (spostrzegawczosc > 3 && koherencja >= 1 && decyzje >= 1) {
            wynik = wynik + "Wsparcie. \nMasz bardzo wysoką spostrzegawczość. Przydałbyś się drużynie jako gracz wspierający. Często zapominana rola, aczkolwiek jedna z najważniejszych dla drużyny.";
        } else if (koherencja > 3 && decyzje > 3) {
            wynik = wynik + "Shotcaller. \nJesteś osobą o dużej odporności i dobrze idzie ci podejmowanie decyzji, nie tylko za siebie, ale i w imieniu drużyny. Nie panikujesz, lubisz przewodzić ludźmi. Odnajdziesz się jako głowa zespołu. ";
        } else if (spostrzegawczosc < 2 && refleks < 2 && koherencja <= 3 && decyzje <= 3) {
            wynik = wynik + "Żadna z powyższych. \nMoże e-sport nie jest dla Ciebie?";
        } else {
            wynik = wynik + "Solo. \n Twoje umiejętności wskazują że jesteś graczem uniwersalnym. Tacy ludzie z reguły są pomocni w każdej dziedzinie, potrafią podejmować decyzje, ale również mają umiejętności do ogrania rywala.";
        }
        opis.setText(wynik);


        zakoncz.setId("wyjscie");
        zakoncz.setText("Zakoncz i wyjdz");
        zakoncz.setLayoutX(350);
        zakoncz.setLayoutY(640);
        zakoncz.setOnAction(event -> {
            summaryStage.close();
            System.exit(0);
        });


        layout.getChildren().addAll(tytul, opis, wykres, wykrestwoj, zakoncz);
        Scene summaryScene = new Scene(layout);
        summaryScene.getStylesheets().add(css);
        summaryStage.setScene(summaryScene);
        summaryStage.show();

    }
}
