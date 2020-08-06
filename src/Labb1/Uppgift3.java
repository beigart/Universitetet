/*Kurs: 1IK153
        Laboration: Labb1:3
        Kursdeltagare: Michael Beigart
        Termin och datum: 12 nov;*/

package Labb1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Uppgift3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Image picture = new Image("file:images/c.png");
        ImageView hej = new ImageView(picture);

        Group root = new Group();
        root.getChildren().addAll(hej);

        Scene scene = new Scene(root);
        primaryStage.setTitle("En Star Wars-bild");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
