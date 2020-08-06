package Labb3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Uppgift3 extends Application {

    Stage window;
    Scene scene1, scene2;
    public static void main(String[] args) {
        launch(args);
    }

    //ArrayList som hämtar namn på artiser
    private ArrayList<String> getArtists () {
        ArrayList <String> artister = new ArrayList<>();

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

            ResultSet result = statement.executeQuery("SELECT Namn from Artist");


            while (result.next()) {
                artister.add(result.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Något gick fel");
        }

        return artister;
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;

        VBox layout1 = new VBox(20);
        layout1.setSpacing(7);
        layout1.setAlignment(Pos.TOP_LEFT);
        layout1.setPadding(new Insets(5, 5, 5, 5));
        Label rubrik = new Label("MusikDB Insättare");
        rubrik.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        ComboBox<String> valjArtist = new ComboBox<>();

        //Lägger in värden från array i combobox. namn
        valjArtist.getItems().addAll(getArtists());

        Button nyArtist = new Button("Skapa ny artist");

        Text skivnamn = new Text("Namn");
        TextField namn = new TextField();
        namn.setMaxWidth(150);

        Text utgivningar = new Text("Utgivningsår");
        TextField ar = new TextField();
        ar.setMaxWidth(150);

        Button insert = new Button("Sätt in");

        //Nedan sker vid knapptryck på insert
        insert.setOnAction(event -> {

            if (namn.getText().equals("") || ar.getText().equals("")){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Meddelande");
                alert.setHeaderText("Problem");
                alert.setContentText("Alla fält ej ifyllda");
                alert.showAndWait();

            } else {
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

                    PreparedStatement artist = conn.prepareStatement("INSERT INTO Album VALUES (?, ?, ?) ");
                    artist.setString(1, namn.getText());
                    artist.setInt(2, Integer.parseInt(ar.getText()));
                    artist.setString(3, valjArtist.getSelectionModel().getSelectedItem());


                    artist.executeUpdate();

                } catch (SQLException ex) {
                    System.out.println("Något gick fel");
                }

            }

        });

        //knapp som skiftar till scen 2. Insert av ny artist
        nyArtist.setOnAction(e -> window.setScene(scene2));

        layout1.getChildren().addAll(rubrik, valjArtist, nyArtist,skivnamn, namn,utgivningar, ar,insert);
        scene1 = new Scene(layout1, 500, 500);



        VBox layout2 = new VBox(20);
        layout2.setSpacing(7);
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setPadding(new Insets(5, 5, 5, 5));


        Text artistNamn = new Text("Artistnamn");
        TextField artistNamn2 = new TextField();
        artistNamn2.setMaxWidth(150);

        Text bildades = new Text("Bildades");
        TextField bildades2 = new TextField();
        bildades2.setMaxWidth(150);

        Text genre = new Text("Genre");
        TextField genre2 = new TextField();
        genre2.setMaxWidth(150);

        Button insertArtist = new Button("Lägg till artist");

        insertArtist.setOnAction(event1 -> {

            if (artistNamn2.getText().equals("") || bildades2.getText().equals("") || genre2.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Meddelande");
                alert.setHeaderText("Problem");
                alert.setContentText("Alla fält ej ifyllda");
                alert.showAndWait();

            }
            else {
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

                    PreparedStatement artist = conn.prepareStatement("INSERT INTO Artist VALUES (?, ?, ?) ");
                    artist.setString(1, artistNamn2.getText());
                    artist.setInt(2, Integer.parseInt(bildades2.getText()));
                    artist.setString(3, genre2.getText());

                    artist.executeUpdate();

                    //Ta bort alla items från comboboxen
                    valjArtist.getItems().clear();

                    //Hämta alla items igen (ink nytillagda)
                    valjArtist.getItems().addAll(getArtists());

                } catch (SQLException ex) {
                    System.out.println("Något gick fel");
                }


            }
            });


        Button button2 = new Button("Tillbaka");
        //Kommer tillbaka till scene 1. lägga till album
        button2.setOnAction(e -> window.setScene(scene1));

        layout2.getChildren().addAll(artistNamn,artistNamn2,bildades,bildades2,genre,genre2,insertArtist, button2);
        scene2 = new Scene(layout2, 500, 500);

        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }

}

