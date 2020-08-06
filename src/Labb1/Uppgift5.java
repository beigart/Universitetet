/*Kurs: 1IK153
        Laboration: Labb1:5
        Kursdeltagare: Michael Beigart
        Termin och datum: 14 nov;*/

package Labb1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Uppgift5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Pane som placerar noder med start högst upp til vänster
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5);
        pane.setVgap(5);

        //Lägger till inmatning samt en Beräkna-knapp i panen
        pane.add(new Label("Ange antal sekunder:"), 0,0);
        TextField inmatning = new TextField();
        pane.add(inmatning,1,0);
        Button knapp = new Button("Beräkna");
        pane.add(knapp, 0,1);

        //Tre labels som visar antalet timmar, minuter, sekunder
        Label timmar = new Label("Timmar: ");
        pane.add(timmar, 0,2);
        Label minuter = new Label("Minuter: ");
        pane.add(minuter, 0, 3);
        Label sekunder = new Label("Sekunder: ");
        pane.add(sekunder, 0, 4);

        //Detta som sker när användaren trycken på knappen Beräkna
        knapp.setOnAction(e -> {
           //Antal timmar. Divideras med 3600 för att få antalet
           int summa = Integer.parseInt(inmatning.getText()) / 3600;
           timmar.setText(String.valueOf("Timmar: " + summa));

           //Minuter divideras med 60 och lämnar 60 kvar
           int summa2 = Integer.parseInt (inmatning.getText()) / 60 % 60;
           minuter.setText(String.valueOf("Minuter: " + summa2));

           //Sekunder tar det som finns kvar
           int summa3 = Integer.parseInt(inmatning.getText()) % 60;
           sekunder.setText(String.valueOf("Sekunder: " + summa3));

        });

        //Scene & stage
        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tidsräknare");
        primaryStage.show();
    }
}

