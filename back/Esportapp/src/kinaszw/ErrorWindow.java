package kinaszw;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class ErrorWindow extends Application {
    Pane layout;
    @Override
    public void start(Stage errorStage) throws Exception {
        layout = new Pane();
        ImageView imageView = new ImageView();
        Image image = new Image(new FileInputStream("C:\\Users\\Tieburach\\Desktop\\Esportapp\\src\\kinaszw\\sources\\znakostrzegawczy.jpg"));
        imageView.setImage(image);
        imageView.setFitHeight(85);
        imageView.setFitWidth(80);
        imageView.setLayoutX(10);
        imageView.setLayoutY(10);
        Label label = new Label();
        label.setText("Nie możesz powtórzyć drugi raz tego samego zadania. Ukończ wszystkie zadania, a będziesz miał możliwość zakończenia testu. Pozostały ci zadania: " + ControllerMainWindow.jakieZostaly());
        label.setWrapText(true);
        label.setPrefSize(300, 100);
        label.setLayoutX(100);
        label.setLayoutY(0);
        Button button = new Button();
        button.setText("Rozumiem.");
        button.setLayoutX(140);
        button.setLayoutY(105);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorStage.close();
            }
        });
        layout.setPrefSize(400,150);
        layout.getChildren().addAll(label, button, imageView);






        String css = Esport.class.getResource("css\\style.css").toExternalForm();
        Scene errorScene = new Scene(layout);
        errorScene.getStylesheets().add(css);
        errorStage.setScene(errorScene);
        errorStage.show();
    }
}
