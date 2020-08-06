/*Kurs: 1IK153
        Laboration: Labb1:4
        Kursdeltagare: Michael Beigart
        Termin och datum: 13 nov;*/

package Labb1;


import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Uppgift4 extends Application
{
    private static Button knapp;
    private static Label lbl;
    private static int counter = 0;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        //Ny knapp
        knapp = new Button();
        knapp.setText("Tryck på mig");

        //Knapp som kallar på metod när ett musklick utförs
        knapp.setOnAction(e -> klick());

        //Label som visar antalet klick
        lbl = new Label();
        lbl.setText("");

        //Placerar knapparna i panen, till vänster och center
        BorderPane pane = new BorderPane();
        pane.setCenter(lbl);
        pane.setLeft(knapp);

        //Scen
        Scene scene = new Scene(pane, 200, 40);

        //Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tryck på knappen");
        primaryStage.show();
    }

    //Metod som adderar till countern varje gång ett klick görs. Den tomma labeln visar antalet klick och ökar hela tiden
    public void klick()

    {
        counter++;
        lbl.setText("" + counter);
    }
}
