/*Kurs: 1IK153
        Laboration: Labb1:8
        Kursdeltagare: Michael Beigart
        Termin och datum: 25 nov;*/

package Labb1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Uppgift8 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //ArrayList som håller bilder
        ArrayList <Image> pictures = new ArrayList<>();

        //Lägger till bilder i ArrayList
        pictures.add(new Image("file:santa/Run (1).png"));
        pictures.add(new Image("file:santa/Run (2).png"));
        pictures.add(new Image("file:santa/Run (3).png"));
        pictures.add(new Image("file:santa/Run (4).png"));
        pictures.add(new Image("file:santa/Run (5).png"));
        pictures.add(new Image("file:santa/Run (6).png"));
        pictures.add(new Image("file:santa/Run (7).png"));
        pictures.add(new Image("file:santa/Run (8).png"));
        pictures.add(new Image("file:santa/Run (9).png"));
        pictures.add(new Image("file:santa/Run (10).png"));
        pictures.add(new Image("file:santa/Run (11).png"));

        //ImageView
        ImageView santa = new ImageView();
        santa.setImage(pictures.get(0));
        santa.setFitWidth(250);
        santa.setFitHeight(180);
        santa.setX(40);

        Group root = new Group();

        //Bakgrund
        Image background = new Image("file:santa/BG.png");
        ImageView view = new ImageView(background);

        //Vänstra hörnet
        Image bild1 = new Image("file:Tiles/1.png");
        ImageView vanster = new ImageView(bild1);
        vanster.setX(0);
        vanster.setY(600);

        //Högra hörnet
        Image bild2 = new Image("file:Tiles/3.png");
        ImageView hoger = new ImageView(bild2);
        hoger.setX(1150);
        hoger.setY(600);

        //Vänster1
        Image bild3 = new Image("file:Tiles/2.png");
        ImageView v1 = new ImageView(bild3);
        v1.setX(120);
        v1.setY(600);

        //Vänter2
        ImageView v2 = new ImageView(bild3);
        v2.setX(240);
        v2.setY(600);

        //Vänter3
        ImageView v3 = new ImageView(bild3);
        v3.setX(360);
        v3.setY(600);

        //Vänter4
        ImageView v4 = new ImageView(bild3);
        v4.setX(480);
        v4.setY(600);

        //Center
        ImageView c = new ImageView(bild3);
        c.setX(600);
        c.setY(600);

        //Höger 1 om center
        ImageView h1 = new ImageView(bild3);
        h1.setX(720);
        h1.setY(600);

        //Höger 2 om center
        ImageView h2 = new ImageView(bild3);
        h2.setX(840);
        h2.setY(600);

        //Höger 3 om center
        ImageView h3 = new ImageView(bild3);
        h3.setX(960);
        h3.setY(600);

        //Höger 4 om center
        ImageView h4 = new ImageView(bild3);
        h4.setX(1080);
        h4.setY(600);

        //Snowman
        Image bild12 = new Image("file:Object/SnowMan.png");
        ImageView snowMan = new ImageView(bild12);
        snowMan.setX(-50);
        snowMan.setY(390);

        //Träd
        Image bild13 = new Image("file:Object/Tree_2.png");
        ImageView tree = new ImageView(bild13);
        tree.setX(1080);
        tree.setY(320);

        //Lägg till allt i rooten
        root.getChildren().addAll(view, vanster, hoger, v1, v2, v3, v4, c, h1, h2, h3, h4, snowMan, tree, santa);

        //Scene & stage
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tomten");
        primaryStage.show();


        //AnimationTimer
        new AnimationTimer() {

            //Kollar om det tomten går åt höger
            boolean right = true;
            //Börjar på x-position 0
            int x = 0;
            //Springer i y-led på postion 440
            int y = 440;

            @Override
            public void handle(long arg0) {

                //Om tomten går åt höger
                if (right) {
                    santa.setScaleX(1);     //Tomten är vänd mot höger

                    if (x <= 830)           //Hur långt tomten går åt höger
                        x += 6;             //Vilken hastighet tomten går åt höger

                    else if (x >= 830)      // Om tomten går längre än 830 blir right false. Går ej längre till h
                        right = false;
                } else {
                    santa.setScaleX(-1);    //Vänder bilden. Tomten går åt vänster
                    if (x >= 0)             //Hur långt tomten går till vänster
                        x -= 6;             //Vilken hastighet tomten går åt vänster
                    else if (x <= 0)        //När x blir 0 eller mindre blir right = true. Tomten går åt höger igen
                        right = true;
                }
                santa.setTranslateX(x);
                santa.setTranslateY(y);

            }
        }.start();

        new AnimationTimer() {
            int x = 0;
            @Override
            public void handle(long arg0) {
                x = (x + 1) % 11;                //Bestämmer hur bilderna rullar. Modulus 11 för 11 bilder
                santa.setImage(pictures.get(x));//Hämtar bilder från ArrayListen

            }
        }.start();
    }
}

