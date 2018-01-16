package sample;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SurveyWindow extends Application {

    private static int wynikodpornosc=0;
    private static int wynikasekurant=0;
    private static int wynikniezdecydowany=0;
    private static int wynikdobrzedzialac=0;

    public static int getWynikodpornosc() {
        return wynikodpornosc;
    }

    public static int getWynikasekurant() {
        return wynikasekurant;
    }

    public static int getWynikniezdecydowany() {
        return wynikniezdecydowany;
    }

    public static int getWynikdobrzedzialac() {
        return wynikdobrzedzialac;
    }


    private ResultWindow resultWindow;


    @Override
    public void start(Stage stage) throws Exception{
        ObservableList<Task> tasks = FXCollections.observableArrayList(
                Arrays.stream(pytania).map(Task::new).collect(Collectors.toList())
        );
        stage.setTitle("Test osobowosci");

        ListView<String> reactionLog = new ListView<>();
        tasks.forEach(task -> task.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected) {
                String x =task.getName();
                if (contains(pytaniaodpornosc,x)) {
                    wynikodpornosc++;
                    System.out.println("Wynik odpornosc  " + wynikodpornosc);
                }
                if (contains(pytaniaasekurant,x)) {
                    wynikasekurant++;
                    System.out.println("Wynik asekurant " + wynikasekurant);
                }
                if (contains(pytanianiezdecydowany,x)) {
                    wynikniezdecydowany++;
                    System.out.println("Wynik niezdecydowany " + wynikniezdecydowany);
                }
                if (contains(pytaniadobrzedzialac,x)) {
                    wynikdobrzedzialac++;
                    System.out.println("Wynik dobrego dzialania " + wynikdobrzedzialac);
                }

            }
            if (wasSelected) {
                String x =task.getName();
                if (contains(pytaniaodpornosc,x)) {
                    wynikodpornosc--;
                    System.out.println("Wynik odpornosc  " + wynikodpornosc);
                }
                if (contains(pytaniaasekurant,x)) {
                    wynikasekurant--;
                    System.out.println("Wynik asekurant " + wynikasekurant);
                }
                if (contains(pytanianiezdecydowany,x)) {
                    wynikniezdecydowany--;
                    System.out.println("Wynik niezdecydowany " + wynikniezdecydowany);
                }
                if (contains(pytaniadobrzedzialac,x)) {
                    wynikdobrzedzialac--;
                    System.out.println("Wynik dobrego dzialania " + wynikdobrzedzialac);
                }

            }
        }));

        ListView<Task> checklist = new ListView<>(tasks);
        checklist.setCellFactory(CheckBoxListCell.forListView(Task::selectedProperty, new StringConverter<Task>() {

            @Override
            public String toString(Task object) {
                return object.getName();
            }

            @Override
            public Task fromString(String string) {
                return null;
            }
        }));

        Label labeltytul = new Label("Ankieta");
        labeltytul.setId("tytul");
        Label labelopis = new Label("Zaznacz te zdania, z którymi się zgadzasz");
        labelopis.setId("opis");
        Button buttonzakoncz = new Button("Zakończ ankietę");
        buttonzakoncz.setId("wyjscie");
        buttonzakoncz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                Stage resultStage = new Stage();
                ResultWindow resultWindow = new ResultWindow();
                try {
                    resultWindow.start(resultStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        GridPane layout = new GridPane();


        GridPane.setHalignment(buttonzakoncz, HPos.CENTER);
        GridPane.setValignment(buttonzakoncz, VPos.CENTER);
        GridPane.setHalignment(labeltytul,HPos.CENTER);
        GridPane.setValignment(labeltytul, VPos.CENTER);
        GridPane.setHalignment(labelopis,HPos.CENTER);
        GridPane.setValignment(labelopis, VPos.CENTER);

        labelopis.setPadding(new Insets(10));
        buttonzakoncz.setPadding(new Insets(10));

        layout.setPrefSize(850, 700);
        layout.setPadding(new Insets(30));
        checklist.setPrefSize(850,700);



        layout.add(labeltytul, 1,0);
        layout.add(labelopis,1,2);
        layout.add(checklist,1,4);
        layout.add(buttonzakoncz,1, 5);



        Scene scene = new Scene(layout);
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public static class Task {
        private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();
        private BooleanProperty selected = new SimpleBooleanProperty(false);

        public Task(String name) {
            this.name.set(name);
        }

        public String getName() {
            return name.get();
        }
        public ReadOnlyStringProperty nameProperty() {
            return name.getReadOnlyProperty();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }
        public boolean isSelected() {
            return selected.get();
        }
        public void setSelected(boolean selected) {
            this.selected.set(selected);
        }
    }

    private static final String[] pytania = {
            "Często nie zwracasz uwagi, co się wokół dzieje. Nie interesuje cię, jak zachowują się ludzie, nie przysłuchujesz się ich rozmowom.",
            "Nawet w trudnej sytuacji nie tracisz głowy. Szybko znajdujesz rozwiązanie i wprowadzasz je w zycie.",
            "Lubisz porównywać swoje osiągnięcia z dokonaniami innych.",
            "Często jesteś zaskoczony zachowaniem znajomych. Myślałeś, ze są bardziej przewidywalni.",
            "Chcesz uczestniczyć w pracy nad ważnym projektem. Wycofujesz się jednak w ostatniej chwili.",
            "Denerwują cię ludzie z najblizszego otoczenia. Myślisz: tak juz musi być, ich nie da się zmienić.",
            "W drodze do celu nie zrazasz się trudnościami. Szybko je przezwycięzasz.",
            "Nieraz zawiedli cię ludzie, na których liczyłeś. Czułeś się wtedy osamotniony.",
            "Emocjonalnie podchodzisz do pracy. Sukces dodaje ci skrzydeł, a niepowodzenie wywołuje frustrację i złość.",
            "Nie oczekujesz wiele od zycia. Fascynujące przygody ludzie miewają tylko w filmach – myślisz.",
            "Lubisz chodzić do szkoły. Nauka sprawia ci prawdziwą radość.",
            "Często wydaje się ci, ze jesteś traktowany niesprawiedliwie.",
            "Zawsze czujesz się niepewnie w nowej dla siebie sytuacji.",
            "Nie lubisz swoich codziennych zajęć.",
            "Lubisz wyzwania. Zawsze chcesz być lepszy od innych (lub co najmniej tak samo dobry).",
            "Stale podnosi poprzeczkę. W szkole podejmujesz się coraz trudniejszych zadań.",
            "Często jest ci smutno bez wyraźnego powodu.",
            "Chętnie podejmujesz wyzwania. Lubisz, gdy ludzie podziwiają twoją odwagę.",
            "Często czujesz się zagubiony i bezradny.",
            "Nauczyciel bezpodstawnie ukarał twoją koleżankę. Bez wahania stajesz w jej obronie.",
            "Zanim przystąpisz do działania, dokładnie obmyślasz strategię. Przewidujesz ewentualne problemy i im zapobiegasz.",
            "Nawet drobne niepowodzenia w pracy wpędzają cię w przygnębienie.",
            "Zazwyczaj opracowujesz plan działania, ale się go nie trzymasz.",
            "Myślisz o wielkiej karierze. Nic jednak nie robisz, aby zrealizować marzenia.",
            "W szkole nie czujesz się pewnie. Nie wiesz, co się wydarzy następnego dnia.",
            "Lubisz obserwować zachowanie i reakcje innych ludzi.",
            "Nie panujesz nad uczuciami. Krzyczysz i śmiejesz się bez powodu.",
            "Obiecałeś koledze, że na zebraniu klasowym poprzesz jego pomysł. Nie odezwałeś się jednak ani słowem.",
            "Zazdrościsz koledze, który uczęszcza do „lepszej” szkoły niż ty. W ogóle inni mają lepiej – myślisz.",
            "Jesteś przekonany, że po serii niepowodzeń sukces przyjdzie sam.",
            "Kiedy nauczyciel nagle zleca ci dodatkowe zadanie, wpadasz w panikę.",
            "To, kim jesteś, mocno odbiega od twojego wyobrażenia, kim mógłbyś być.",
            "Twój kolega podjął decyzję w imieniu zespołu. Nie masz do niego pretensji.",
            "Stawiasz sobie zbyt łatwe cele. Ich realizacja nie daje ci satysfakcji.",
            "Masz ambitne plany na przyszłość. Jednak nic nie robisz, aby je zrealizować.",
            "Wyjeżdżasz w krótką podróż. Nie umiesz zdecydować, co spakować do walizki.",
            "Na zakupach długo się zastanawiasz. A gdy już wybierzesz, to zawsze podoba się ci coś innego.",
            "Gdy koledzy prowadzą zażartą dyskusję, chętnie do niej się przyłączasz.",
            "Dokonałeś wyboru. Dręczy cię jednak myśl: co by było, gdybym zdecydował inaczej.",
            "Zdarzało się, że koledzy mieli do ciebie pretensje, bo postąpiłeś wbrew obietnicom.",
            "Decyzje podejmujesz szybko dopiero wtedy, gdy jesteś przyparty do muru.",
            "Odwlekasz chwilę ostatecznego wyboru, bo chcesz zgromadzić jak najwięcej informacji.",
            "Koledzy z grupy chcą pogadać z tobą o wspólnych problemach. Zbywasz ich, bo nie wiesz, jakie zająć stanowisko.",
            "Łatwo rozgrzeszasz się z porażki. Zadanie, którego się podjąłem, było za trudne – myślisz.",
            "Nie potrafisz odnaleźć większego sensu w swoim działaniu.",
            "Nie przejmujesz się porażkami. Natychmiast szukasz sposobów rozwiązania problemu.",
            "Ambitnie planujesz karierę („za rok będę studentem uczelni X), ustalasz cele. Ale nie jesteś wytrwały w ich realizacji.",
            "Niesłusznie posądzono cię o kwestionowanie autorytetu waszego wychowawcy. Bez trudu znajdujesz wyjście z tej niezręcznej sytuacji.",
            "Niechętnie sam planujesz pracę. Wolisz, gdy inni mówią ci, co masz robić.",
            "Bardziej odpowiada ci rola zastępcy niż szefa zespołu."
    };

    public String[] pytaniaodpornosc = new String[]{
            "Często nie zwracasz uwagi, co się wokół dzieje. Nie interesuje cię, jak zachowują się ludzie, nie przysłuchujesz się ich rozmowom.",
            "Często jesteś zaskoczony zachowaniem znajomych. Myślałeś, ze są bardziej przewidywalni.",
            "Nieraz zawiedli cię ludzie, na których liczyłeś. Czułeś się wtedy osamotniony.",
            "Nie oczekujesz wiele od zycia. Fascynujące przygody ludzie miewają tylko w filmach – myślisz.",
            "Często wydaje się ci, ze jesteś traktowany niesprawiedliwie.",
            "Zawsze czujesz się niepewnie w nowej dla siebie sytuacji.",
            "Nie lubisz swoich codziennych zajęć.",
            "Często jest ci smutno bez wyraźnego powodu.",
            "Często czujesz się zagubiony i bezradny.",
            "Nawet drobne niepowodzenia w pracy wpędzają cię w przygnębienie.",
            "W szkole nie czujesz się pewnie. Nie wiesz, co się wydarzy następnego dnia.",
            "Nie panujesz nad uczuciami. Krzyczysz i śmiejesz się bez powodu.",
            "Nie potrafisz odnaleźć większego sensu w swoim działaniu."
    };
    public String[] pytaniaasekurant = new String[]{
            "Denerwują cię ludzie z najblizszego otoczenia. Myślisz: tak juz musi być, ich nie da się zmienić.",
            "Myślisz o wielkiej karierze. Nic jednak nie robisz, aby zrealizować marzenia.",
            "Zazdrościsz koledze, który uczęszcza do „lepszej” szkoły niż ty. W ogóle inni mają lepiej – myślisz.",
            "Jesteś przekonany, że po serii niepowodzeń sukces przyjdzie sam.",
            "To, kim jesteś, mocno odbiega od twojego wyobrażenia, kim mógłbyś być.",
            "Stawiasz sobie zbyt łatwe cele. Ich realizacja nie daje ci satysfakcji.",
            "Na zakupach długo się zastanawiasz. A gdy już wybierzesz, to zawsze podoba się ci coś innego.",
            "Dokonałeś wyboru. Dręczy cię jednak myśl: co by było, gdybym zdecydował inaczej.",
            "Decyzje podejmujesz szybko dopiero wtedy, gdy jesteś przyparty do muru.",
            "Odwlekasz chwilę ostatecznego wyboru, bo chcesz zgromadzić jak najwięcej informacji.",
            "Bardziej odpowiada ci rola zastępcy niż szefa zespołu."
    };

    public String[] pytanianiezdecydowany = new String[]{
            "Chcesz uczestniczyć w pracy nad ważnym projektem. Wycofujesz się jednak w ostatniej chwili.",
            "Zazwyczaj opracowujesz plan działania, ale się go nie trzymasz.",
            "Obiecałeś koledze, że na zebraniu klasowym poprzesz jego pomysł. Nie odezwałeś się jednak ani słowem.",
            "Kiedy nauczyciel nagle zleca ci dodatkowe zadanie, wpadasz w panikę.",
            "Masz ambitne plany na przyszłość. Jednak nic nie robisz, aby je zrealizować.",
            "Wyjeżdżasz w krótką podróż. Nie umiesz zdecydować, co spakować do walizki.",
            "Zdarzało się, że koledzy mieli do ciebie pretensje, bo postąpiłeś wbrew obietnicom.",
            "Koledzy z grupy chcą pogadać z tobą o wspólnych problemach. Zbywasz ich, bo nie wiesz, jakie zająć stanowisko.",
            "Łatwo rozgrzeszasz się z porażki. Zadanie, którego się podjąłem, było za trudne – myślisz.",
            "Ambitnie planujesz karierę („za rok będę studentem uczelni X), ustalasz cele. Ale nie jesteś wytrwały w ich realizacji.",
            "Niechętnie sam planujesz pracę. Wolisz, gdy inni mówią ci, co masz robić.",
    };

    public String[] pytaniadobrzedzialac = new String[]{
            "Nawet w trudnej sytuacji nie tracisz głowy. Szybko znajdujesz rozwiązanie i wprowadzasz je w zycie.",
            "Lubisz porównywać swoje osiągnięcia z dokonaniami innych.",
            "W drodze do celu nie zrazasz się trudnościami. Szybko je przezwycięzasz.",
            "Emocjonalnie podchodzisz do pracy. Sukces dodaje ci skrzydeł, a niepowodzenie wywołuje frustrację i złość.",
            "Lubisz chodzić do szkoły. Nauka sprawia ci prawdziwą radość.",
            "Lubisz wyzwania. Zawsze chcesz być lepszy od innych (lub co najmniej tak samo dobry).",
            "Stale podnosi poprzeczkę. W szkole podejmujesz się coraz trudniejszych zadań.",
            "Chętnie podejmujesz wyzwania. Lubisz, gdy ludzie podziwiają twoją odwagę.",
            "Nauczyciel bezpodstawnie ukarał twoją koleżankę. Bez wahania stajesz w jej obronie.",
            "Zanim przystąpisz do działania, dokładnie obmyślasz strategię. Przewidujesz ewentualne problemy i im zapobiegasz.",
            "Lubisz obserwować zachowanie i reakcje innych ludzi.",
            "Twój kolega podjął decyzję w imieniu zespołu. Nie masz do niego pretensji.",
            "Gdy koledzy prowadzą zażartą dyskusję, chętnie do niej się przyłączasz.",
            "Nie przejmujesz się porażkami. Natychmiast szukasz sposobów rozwiązania problemu.",
            "Niesłusznie posądzono cię o kwestionowanie autorytetu waszego wychowawcy. Bez trudu znajdujesz wyjście z tej niezręcznej sytuacji."
    };

    public boolean contains(String[] tablica, String string){
        boolean wynik=false;
        for (int j =0; j<tablica.length; j++) {
            if(tablica[j]==string){
                wynik=true;
                break;
            }
            else wynik=false;
        }
        return wynik;
    }
}
