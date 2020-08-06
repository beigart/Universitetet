package Labb3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Uppgift2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        ListView<String> artistList = new ListView<>();
        ObservableList<String> artist = FXCollections.observableArrayList();
        artistList.setPrefWidth(395);

        Label huvudrubrik = new Label("MusikDB");
        pane.setTop(huvudrubrik);
        huvudrubrik.setFont(Font.font("Times New Roman", FontWeight.BOLD, 35));
        pane.setLeft(artistList);
        Text text = new Text();

        ListView<String> albumList = new ListView<>();
        albumList.setPrefWidth(395);

        huvudrubrik.setFont(Font.font("Times New Roman", FontWeight.BOLD, 35));
        pane.setRight(albumList);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver laddar");
        } catch (ClassNotFoundException e) {
            //System.out.println("Driver laggar");
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/Music?useSSL=false",
                "root", "Hallonsaft1")) {
            //System.out.println("Connected");

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * from Artist");

            //Lägger in värden i listviewn för artister
            artistList.setItems(artist);

            while (result.next()) {
                artist.add(result.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Något gick fel");
        }



        artistList.getSelectionModel().selectedIndexProperty().addListener(observable -> {
            String pos = artistList.getSelectionModel().getSelectedItem();


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //System.out.println("Driver laddar");
            } catch (ClassNotFoundException e) {
                //System.out.println("Driver laggar");
            }

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/Music?useSSL=false",
                    "root", "Hallonsaft1")) {
                //System.out.println("Connected");

                Statement statement = conn.createStatement();

                ResultSet result = statement.executeQuery("SELECT * from Album where Artist = '" + pos +  " ' "  + " ORDER BY Utgivningsar ASC ");

                //Listan rensas
                albumList.getItems().clear();

                while (result.next()) {
                    albumList.getItems().addAll(result.getString(1));
                }

            } catch (SQLException ex) {
                System.out.println("Något gick fel");
            }
        });

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("MusikDB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

