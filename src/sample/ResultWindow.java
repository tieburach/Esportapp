package sample;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.text.Position;


public class ResultWindow extends Application {
    final NumberAxis xAxis1 = new NumberAxis(1, 53, 4);
    final NumberAxis yAxis1 = new NumberAxis(0, 80, 10);
    final NumberAxis xAxis2 = new NumberAxis(1, 53, 4);
    final NumberAxis yAxis2 = new NumberAxis(0, 80, 10);





    @Override public void start(Stage stage) {
        GridPane layout = new GridPane();
            layout.setPrefSize(850,700);
            layout.setPadding(new Insets(10));

        BubbleChart<Number,Number> blc = new BubbleChart<>(xAxis1,yAxis1);
            blc.setPrefSize(380,330);

        BubbleChart<Number,Number> blc2 = new BubbleChart<>(xAxis2,yAxis2);
            blc2.setPrefSize(380,330);

        Label opis1 = new Label();
            opis1.setWrapText(true);
            opis1.setPadding(new Insets(10));
            opis1.setPrefSize(420,330);
            opis1.setText("Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis. Maecenas malesuada elit lectus felis, malesuada ultricies. Curabitur et ligula. Ut molestie a, ultricies porta urna. Vestibulum commodo volutpat a, convallis ac, laoreet enim. Phasellus fermentum in, dolor. Pellentesque facilisis. Nulla imperdiet sit amet magna. Vestibulum dapibus, mauris nec malesuada fames ac turpis velit, rhoncus eu, luctus et interdum adipiscing wisi. Aliquam erat ac ipsum. Integer aliquam purus. Quisque lorem tortor fringilla sed, vestibulum id, eleifend justo vel bibendum sapie");
        Label opis2 = new Label();
            opis2.setPadding(new Insets(10));
            opis2.setWrapText(true);
            opis2.setText("massa ac turpis faucibus orci luctus non, consectetuer lobortis quis, varius in, purus. Integer ultrices posuere cubilia Curae, Nulla ipsum dolor lacus, suscipit adipiscing. Cum sociis natoque penatibus et ultrices volutpat. Nullam wisi ultricies a, gravida vitae, dapibus risus ante sodales lectus blandit eu, tempor diam pede cursus vitae, ultricies eu, faucibus quis, porttitor eros cursus lectus, pellentesque eget, bibendum a, gravida ullamcorper quam. Nullam viverra consectetuer. Quisque cursus et, porttitor risus. Aliquam sem. In hendrerit nulla quam nunc, accumsan congue. Lorem ipsum primis in nibh vel risus. Sed vel lectus. Ut sagittis, ipsum dolor quam.");
            opis2.setPrefSize(420,330);




/*
        GridPane.setHalignment(blc, HPos.RIGHT);
        GridPane.setValignment(blc, VPos.TOP);
        GridPane.setHalignment(blc2, HPos.RIGHT);
        GridPane.setValignment(blc2, VPos.BOTTOM);
        GridPane.setHalignment(opis1, HPos.LEFT);
        GridPane.setValignment(opis1, VPos.TOP);
        GridPane.setHalignment(opis2, HPos.RIGHT);
        GridPane.setValignment(opis2, VPos.BOTTOM);
*/
        XYChart.Series series1 = new XYChart.Series();
            series1.setName("Product 1");
            series1.getData().add(new XYChart.Data(3, 35));
            series1.getData().add(new XYChart.Data(12, 60));


        XYChart.Series series2 = new XYChart.Series();
            series2.setName("Product 2");
            series2.getData().add(new XYChart.Data(8, 15));
            series2.getData().add(new XYChart.Data(13, 23));





        blc.getData().addAll(series1, series2);
        layout.add(blc,2,1);
        layout.add(blc2,2,2);
        layout.add(opis1,1,1);
        layout.add(opis2,1,2);



        Scene scene = new Scene(layout);
        String css = Esport.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }}
