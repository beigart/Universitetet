/*Kurs: 1IK153
        Laboration: Labb1:6
        Kursdeltagare: Michael Beigart
        Termin och datum: 14 nov;*/

package Labb1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Uppgift6 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Placerar alla noder horisontellt
        HBox layout = new HBox();
        layout.setSpacing(7);
        layout.setAlignment(Pos.CENTER_LEFT);

        Label angeTid = new Label("  Ange tid: ");

        //Användaren får mata in timmar, minuter, sekunder
        TextField timmar = new TextField();
        timmar.setPrefSize(50, 20);
        Label hours = new Label("h");

        TextField minuter = new TextField();
        minuter.setPrefSize(50, 20);
        Label minutes = new Label("m");

        TextField sekunder = new TextField();
        sekunder.setPrefSize(50, 20);
        Label seconds = new Label ("s");

        //Knapp
        Button knapp = new Button("Beräkna");

        //Det som händer när använadren trycker på Beräkna
        knapp.setOnAction(event -> {


            //Om någon av fälten inte har något inskrivet ska Error-ruta poppa upp
            if (timmar.getText().equals("") || minuter.getText().equals("")  || sekunder.getText().equals("")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Meddelande");
                alert.setHeaderText("Problem");
                alert.setContentText("Alla fält ej ifyllda");
                alert.showAndWait();

                //Annars räknas allt ut och visar antal sekunder i en annan pop-up-ruta
            } else {
                int summa = Integer.parseInt(timmar.getText()) * 3600;
                int summa2 = Integer.parseInt(minuter.getText()) * 60;
                int summa3 = Integer.parseInt(sekunder.getText());
                int total = summa + summa2 + summa3;

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Meddelande");
                alert2.setHeaderText("Beräknat värde");
                alert2.setContentText("Det blir " + total + " sekunder");
                alert2.showAndWait();
            }

        });


        layout.getChildren().addAll(angeTid, timmar, hours, minuter, minutes, sekunder, seconds, knapp);


        //Scene & stage
        Scene scene = new Scene(layout, 400, 90);
        primaryStage.setTitle("Beräkna tid");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

