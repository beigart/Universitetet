/*Kurs: 1IK153
        Laboration: Labb1:2
        Kursdeltagare: Michael Beigart
        Termin och datum: 11 nov;*/

package Labb1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Uppgift2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();             //Placerar noderna i specifika kolumner och rader
        int size = 8;                               //8 rutor

         {
             //Loop
            for (int rad = 0; rad < size; rad++) {  //Skapar 8 rader
                for (int kolumn = 0; kolumn < size; kolumn++) { //Skapar 8 kolumner

                    Rectangle square = new Rectangle();         //Skapar kvadrat-objekt
                    Color color;                                //Färg

                    // Om rad + kolumn divideras med 2 och det blir en kvar blir det en svart ruta, annars vit
                    if ((rad + kolumn) % 2 == 1) color = Color.BLACK;
                    else color = Color.WHITE;

                    square.setFill(color);                      //Fyller rutor
                    root.add(square, kolumn, rad);              //Lägger till allt i gridpanen

                    //Binder ihop start och slut-punkterna för kvadraterna med höjden och
                    //bredden för panen så att dessa ändras om panens storlek ändras. s.585
                    square.widthProperty().bind(root.widthProperty().divide(size));
                    square.heightProperty().bind(root.heightProperty().divide(size));
                }
            }

            //Stage
            primaryStage.setTitle("Schack");
            primaryStage.setScene(new Scene(root, 700, 700));
            primaryStage.show();
        }
    }
}
