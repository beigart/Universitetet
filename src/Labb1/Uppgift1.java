/*Kurs: 1IK153
        Laboration: Labb1:1
        Kursdeltagare: Michael Beigart
        Termin och datum: 11 nov;*/

package Labb1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Uppgift1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group layout = new Group();

        Circle bottom = new Circle(320, 400, 60);
        bottom.setFill(Color.WHITE);

        Circle middle = new Circle(320, 320, 40);
        middle.setFill(Color.WHITE);

        Circle top = new Circle(320, 255, 30);
        top.setFill(Color.WHITE);

        Circle sun = new Circle(500, 100, 60);
        sun.setFill(Color.YELLOW);

        Rectangle background = new Rectangle(0, 0, 640, 440);
        background.setFill(Color.SKYBLUE);

        Circle eye1 = new Circle(305, 245, 4);
        eye1.setFill(Color.BLACK);

        Circle eye2 = new Circle(335, 245, 4);
        eye2.setFill(Color.BLACK);

        Rectangle mouth = new Rectangle(305, 260, 30, 2);
        mouth.setFill(Color.BLACK);

        Circle buttom1 = new Circle(320, 300, 4);
        buttom1.setFill(Color.BLACK);

        Circle buttom2 = new Circle(320, 320, 4);
        buttom2.setFill(Color.BLACK);

        Circle buttom3 = new Circle(320, 340, 4);
        buttom3.setFill(Color.BLACK);

        //Lägg till allt i layout
        layout.getChildren().addAll(background, top, middle, bottom, sun, eye1, eye2, mouth, buttom1, buttom2, buttom3);

        //Storlek på window
        Scene scene = new Scene(layout, 640, 480);

        //Stage
        primaryStage.setTitle("Snowman");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
