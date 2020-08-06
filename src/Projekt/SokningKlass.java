package Projekt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class SokningKlass extends Application {

    Stage window;

    BorderPane root = new BorderPane();
    private int antalSokresultat;

    public static void main(String[] args) {
        launch(args);
    }

    //Metod som hämtar från databasen och lägger i en ArrayList
    public static ArrayList<Superfigur> skapaSuperfigurArrayList(){
        ArrayList<Superfigur> superfigurArrayList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/Superheroes?useSSL=false",
                "root", "Hallonsaft1")) {
            System.out.println("Connected");

            //Hämta data från tabellerna "superfigur" och "ingar_i"
            Statement statement = conn.createStatement();
            ResultSet rs_superfigur = statement.executeQuery(
                    "SELECT superfigur.Alias, Fnamn, Enamn, Inriktning, Beskrivning, Unamn FROM superfigur " +
                            "JOIN ingar_i ii ON superfigur.Alias = ii.Alias");

            //Strängar som håller värden för de olika kolumnerna
            String alias;
            String fnamn;
            String enamn;
            String inriktning;
            String beskrivning;
            String universum;

            //Hämtar värden från databasen och lägger dem i strängarna
            while(rs_superfigur.next()) {
                alias = rs_superfigur.getString(1);
                fnamn = rs_superfigur.getString(2 );
                enamn = rs_superfigur.getString(3);
                inriktning = rs_superfigur.getString(4);
                beskrivning = rs_superfigur.getString(5);
                universum = rs_superfigur.getString(6);

                //Skapa Superfigur-object och lägg till värden
                superfigurArrayList.add(new Superfigur(alias, fnamn, enamn, inriktning, beskrivning, universum));

            }
            //Hämta data från tabellen "superfigur_tillhör"
            Statement statement2 = conn.createStatement();
            ResultSet rs_organisation = statement2.executeQuery(
                    "SELECT Alias, Onamn, Status FROM superfigur_tillhor");

            while (rs_organisation.next()){
                for (Superfigur s : superfigurArrayList){
                    if ((s.getAlias().equals(rs_organisation.getString(1)) && (rs_organisation.getString(3).equals("Nuvarande")))){
                        s.setNuvarandeOrg(rs_organisation.getString(2));
                    }

                    if ((s.getAlias().equals(rs_organisation.getString(1)) && (rs_organisation.getString(3).equals("Tidigare")))){
                        s.addTidigareOrg(rs_organisation.getString(2));
                    }
                }
            }

            //Hämtar data från tabellen superfigur_har & skapare
            Statement statement3 = conn.createStatement();
            ResultSet rs_skapare = statement3.executeQuery(
                    "SELECT superfigur_har.Alias, CONCAT(skapare.Fnamn, ' ', skapare.Enamn) " +
                            "FROM superfigur_har " +
                            "JOIN skapare ON superfigur_har.SkapareID = skapare.SkapareID");

            while (rs_skapare.next()){
                for (Superfigur s : superfigurArrayList) {

                    if (s.getAlias().equals(rs_skapare.getString(1))) {
                        s.addSkapare(rs_skapare.getString(2));
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong..." + ex.getMessage());
        }

        return superfigurArrayList;
    }

    @Override
    public void start(Stage primaryStage) {


        //--------------------Hämta info om superfigurer och lägg till i en datastruktur--------------------


        //Test att skriva ut all info
       /* ArrayList<Superfigur> superfigurArrayList = skapaSuperfigurArrayList();
        for (Superfigur s : superfigurArrayList){
            System.out.print("Alias: " + s.getAlias() + " | Förnamn: " + s.getFnamn() + " | Efternamn: " + s.getEnamn() + " | Inriktning: " + s.getInriktning() + " | Nuvarande organisation: " + s.getNuvarandeOrg() + " | Universum: " + s.getUniversum() + " |\nTidigare organisationer: ");
            for (String to : s.getTidigareOrg()){
                System.out.print(to + ", ");
            }
            System.out.print(" | Skapare: ");
            for (String sk : s.getSkapareArr()) {
                System.out.print(sk + ", ");
            }
            System.out.println("\n");
        }*/

        window = primaryStage;

        //Arraylist som kallat på ovan metod
        ArrayList<Superfigur> superfigurArrayList = skapaSuperfigurArrayList();

        //Mainpage
        ToolBar toolBar = new ToolBar();
        Button knapp = new Button("Se medlemmar");
        knapp.setPrefSize(150,35);
        Button knapp2 = new Button("Lägg till medlem");
        knapp2.setPrefSize(150,35);
        Button knapp3 = new Button("Sök");
        knapp3.setPrefSize(150,35);


        //Sökning
        TextField sokningTF = new TextField();
        sokningTF.setMaxWidth(100);
        Button sokButton = new Button("Sök");
        HBox sokningHBox = new HBox();
        sokningHBox.setPadding(new Insets(5));
        sokningHBox.getChildren().addAll(sokningTF, sokButton);

        //Listview med sökresultat
        ListView<String> lv_sokresultat = new ListView<>();

        //Superhero-huvudrubrik
        Text alias = new Text();
        alias.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        //Beskrivningar
        Text fnamn = new Text();
        Text enamn = new Text();
        Text inriktning = new Text();
        Text beskrivning = new Text();
        beskrivning.setWrappingWidth(200);
        Text nuvarandeOrg = new Text();
        Text universum = new Text();
        Text skapare = new Text();
        Text tidigareOrg = new Text();

        //Rubriker
        Text fnamn_label = new Text();
        fnamn_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text enamn_label = new Text();
        enamn_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text inriktning_label = new Text();
        inriktning_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text beskrivning_label = new Text();
        beskrivning_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text nuvarandeOrg_label = new Text();
        nuvarandeOrg_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text universum_label = new Text();
        universum_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text skapare_label = new Text();
        skapare_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Text tidigareOrg_label = new Text();
        tidigareOrg_label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        //Kombinerar beskrivningar och rubriker
        HBox alias_Hbox = new HBox(alias);
        alias_Hbox.setPadding(new Insets(5));
        HBox hBoxFnamn = new HBox(fnamn_label, fnamn);
        hBoxFnamn.setPadding(new Insets(5));
        HBox hBoxEnamn = new HBox(enamn_label, enamn);
        hBoxEnamn.setPadding(new Insets(5));
        HBox hBoxInriktning = new HBox(inriktning_label, inriktning);
        hBoxInriktning.setPadding(new Insets(5));
        HBox hBoxBeskrivning = new HBox(beskrivning_label, beskrivning);
        hBoxBeskrivning.setPadding(new Insets(5));
        HBox hBoxNuvarandeOrg = new HBox(nuvarandeOrg_label, nuvarandeOrg);
        hBoxNuvarandeOrg.setPadding(new Insets(5));
        HBox hBoxUniversum = new HBox(universum_label, universum);
        hBoxUniversum.setPadding(new Insets(5));
        HBox hBoxSkapere = new HBox(skapare_label, skapare);
        hBoxSkapere.setPadding(new Insets(5));
        HBox hBoxTidigareOrg = new HBox(tidigareOrg_label, tidigareOrg);
        hBoxTidigareOrg.setPadding(new Insets(5));

        //Sätter samman allt vertikalt
        VBox samladInfo = new VBox(alias_Hbox, hBoxFnamn, hBoxEnamn, hBoxInriktning, hBoxNuvarandeOrg, hBoxUniversum, hBoxSkapere, hBoxTidigareOrg, hBoxBeskrivning);
        samladInfo.setPadding(new Insets(10));

        toolBar.getItems().addAll(knapp, knapp2, sokningHBox);

        //Placerar toolbar och all beskrvning i bp.
        VBox vBox = new VBox();
        Label sokresultat = new Label();
        sokresultat.setPadding(new Insets(5));
        vBox.getChildren().addAll(sokresultat, lv_sokresultat);
        root.setTop(toolBar);
        root.setCenter(samladInfo);

        //Sök-knapp set on action
        sokButton.setOnAction(event -> {
            String sokord = sokningTF.getText().toLowerCase();
            root.setLeft(vBox);
            root.setCenter(samladInfo);

            lv_sokresultat.getItems().clear();

            //Går igenom arraylisten med olika villkor beroende på sökning
            if (!sokord.isEmpty() && !sokord.equals(" ")) {
                for (Superfigur sf : superfigurArrayList) {
                    if ((sf.getAlias().toLowerCase().contains(sokord)) || ((sf.getFnamn() + " " + sf.getEnamn()).toLowerCase().contains(sokord)) || (sf.getInriktning().toLowerCase().equals(sokord))
                            || (sf.getUniversum().toLowerCase().equals(sokord)) || (sf.getSkapareArr().toString().toLowerCase().contains(sokord))) {
                        lv_sokresultat.getItems().addAll(sf.getAlias());
                        antalSokresultat++;
                    }
                }
                //Antal träffar för sökresultat
                sokresultat.setText("Antal träffar: " + antalSokresultat);
                if (antalSokresultat == 0){
                    sokresultat.setText("Inga resultat hittades för \"" + sokningTF.getText() + "\"");
                }
                antalSokresultat = 0;
            }

            else {
                sokresultat.setText("Skriv ett sökord i sökrutan");
            }
        });

        //Lyssnare som läser av
        lv_sokresultat.getSelectionModel().selectedIndexProperty().addListener(e ->{
            String alias_nuvarande = lv_sokresultat.getSelectionModel().getSelectedItem();

            for (Superfigur sf : superfigurArrayList){
                if (sf.getAlias().equals(alias_nuvarande)){
                    alias.setText(sf.getAlias());
                    fnamn.setText(sf.getFnamn());
                    enamn.setText(sf.getEnamn());
                    inriktning.setText(sf.getInriktning());
                    beskrivning.setText(sf.getBeskrivning());
                    nuvarandeOrg.setText(sf.getNuvarandeOrg());
                    universum.setText(sf.getUniversum());
                    skapare.setText("N/A");

                    if (!sf.getSkapareArr().isEmpty()){
                        skapare.setText("");
                        for (int i = 0; i < sf.getSkapareArr().size(); i++){
                            skapare.setText(skapare.getText() + sf.getSkapareArr().get(i));
                            if (i < sf.getSkapareArr().size() - 1){
                                skapare.setText(skapare.getText() + ", ");
                            }
                        }
                    }

                    tidigareOrg.setText("N/A");
                    if (!sf.getTidigareOrg().isEmpty()){
                        tidigareOrg.setText("");
                        for (int i = 0; i < sf.getTidigareOrg().size(); i++){
                            tidigareOrg.setText(tidigareOrg.getText() + sf.getTidigareOrg().get(i));
                            if (i < sf.getTidigareOrg().size() - 1){
                                tidigareOrg.setText(tidigareOrg.getText() + ", ");
                            }
                        }
                    }

                    fnamn_label.setText("Förnamn: ");
                    enamn_label.setText("Efternamn: ");
                    inriktning_label.setText("Inriktning: ");
                    beskrivning_label.setText("Beskrivning: ");
                    nuvarandeOrg_label.setText("Organisation: ");
                    universum_label.setText("Universum: ");
                    skapare_label.setText("Skapare: ");
                    tidigareOrg_label.setText("Tidigare organisationer: ");
                }
            }
        });


        //Frontpage
        Text welcome = new Text("Välkommen till Superhero Manager");
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        root.setCenter(welcome);

        Scene scene = new Scene(root, 750, 500);


        //--------------------------------------------------------------------------------------------
        //SE alla medlemmar FLIK 1

        ListView<String> lv_visaMedlemmar = new ListView<>();   //Ny listview

        for (Superfigur i : superfigurArrayList){
            lv_visaMedlemmar.getItems().addAll(i.getAlias());   //Lägger till namn i listview
        }

        //Lyssnare som har koll på vilket item som är valt i listview
        lv_visaMedlemmar.getSelectionModel().selectedIndexProperty().addListener(e ->{
            String alias_nuvarande = lv_visaMedlemmar.getSelectionModel().getSelectedItem();

            for (Superfigur sf : superfigurArrayList){
                if (sf.getAlias().equals(alias_nuvarande)){ //Om aliaset är samma som det valda itemet
                    alias.setText(sf.getAlias());
                    fnamn.setText(sf.getFnamn());
                    enamn.setText(sf.getEnamn());
                    inriktning.setText(sf.getInriktning());
                    beskrivning.setText(sf.getBeskrivning());
                    nuvarandeOrg.setText(sf.getNuvarandeOrg());
                    universum.setText(sf.getUniversum());
                    skapare.setText("N/A");

                    //Kollar om Skapare-arrayen är tom, annars går den igenom den arrayen och hämtar skapare
                    if (!sf.getSkapareArr().isEmpty()){
                        skapare.setText("");
                        for (int i = 0; i < sf.getSkapareArr().size(); i++){
                            skapare.setText(skapare.getText() + sf.getSkapareArr().get(i));
                            if (i < sf.getSkapareArr().size() - 1){
                                skapare.setText(skapare.getText() + ", ");
                            }
                        }
                    }

                    //Kollar om superhjälten varit med i tidigare organisationer genom att
                    //gå igenom arrayen
                    tidigareOrg.setText("N/A");
                    if (!sf.getTidigareOrg().isEmpty()){
                        tidigareOrg.setText("");
                        for (int i = 0; i < sf.getTidigareOrg().size(); i++){
                            tidigareOrg.setText(tidigareOrg.getText() + sf.getTidigareOrg().get(i));
                            if (i < sf.getTidigareOrg().size() - 1){
                                tidigareOrg.setText(tidigareOrg.getText() + ", ");
                            }
                        }
                    }

                    //Skriver ut rubrikerna
                    fnamn_label.setText("Förnamn: ");
                    enamn_label.setText("Efternamn: ");
                    inriktning_label.setText("Inriktning: ");
                    beskrivning_label.setText("Beskrivning: ");
                    nuvarandeOrg_label.setText("Organisation: ");
                    universum_label.setText("Universum: ");
                    skapare_label.setText("Skapare: ");
                    tidigareOrg_label.setText("Tidigare organisationer: ");
                }
            }
        });

        //Knapp som visar visar alla medlemmar
        knapp.setOnAction(event -> {
            root.getChildren().clear();
            root.setTop(toolBar);
            root.setLeft(lv_visaMedlemmar);
            root.setCenter(samladInfo);

        });

        //////////////////////////////////////////////////

        // VBox för lägga till medlemmar, alla textfield och sen SQL kopplingen för Insert

        VBox vertikal = new VBox();
        vertikal.setSpacing(10.0);
        vertikal.setPadding(new Insets(5.0));
        vertikal.setAlignment(Pos.BASELINE_LEFT);

        //Inmatningar för användare
        TextField alias2 = new TextField();
        alias2.setMaxWidth(150);
        alias2.setPromptText("Alias");
        TextField namn = new TextField();
        namn.setMaxWidth(150);
        namn.setPromptText("Namn");
        TextField efternamn = new TextField();
        efternamn.setMaxWidth(150);
        efternamn.setPromptText("Efternamn");
        TextField inriktning2 = new TextField();
        inriktning2.setMaxWidth(150);
        inriktning2.setPromptText("Inriktning");
        TextField beskrivning2 = new TextField();
        beskrivning2.setMaxWidth(150);
        beskrivning2.setPromptText("Beskrivning");

        //Radio-buttons för universum
        Label selectUniverse = new Label("Välj Universum:");
        final ToggleGroup grupp = new ToggleGroup();
        RadioButton radioKnapp = new RadioButton("DC");
        radioKnapp.setToggleGroup(grupp);
        radioKnapp.setSelected(true);
        RadioButton radioKnapp2 = new RadioButton("Hellboy Universe");
        radioKnapp2.setToggleGroup(grupp);
        RadioButton radioKnapp3 = new RadioButton("Marvel");
        radioKnapp3.setToggleGroup(grupp);

        //Knapp för att lägga till superfigur
        Button addera = new Button("Lägg till");
        addera.setOnAction(event -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver did not load");
            }

            try (Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost/Superheroes?useSSL=false",
                    "root", "Hallonsaft1")) {
                System.out.println("Connected");

                //kolla så inte alias textfield är tomt
                if (alias2.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Något gick fel");
                    alert.setContentText("Du måste fylla i ett Alias.");
                    alert.showAndWait();
                } else {
                    try {
                        //Hämtar inmatningar och skapar påstående för insättning i följande tabeller
                        PreparedStatement adderaMedlem = connect.prepareStatement("INSERT INTO superfigur VALUES (?, ?, ?, ?, ?)");
                        adderaMedlem.setString(1, alias2.getText());
                        adderaMedlem.setString(2, namn.getText());
                        adderaMedlem.setString(3, efternamn.getText());
                        adderaMedlem.setString(4, inriktning2.getText());
                        adderaMedlem.setString(5, beskrivning2.getText());


                        PreparedStatement adderaUniversum = connect.prepareStatement ("INSERT INTO ingar_i VALUES (?, ?)");
                        adderaUniversum.setString(1, alias2.getText());

                        //Sträng som håller värdet på valt universum
                        String selectedUniverse = "";

                        if (radioKnapp.isSelected()) {
                            adderaUniversum.setString(2, "DC");
                            selectedUniverse = "DC";
                        }
                        else if (radioKnapp2.isSelected()) {
                            adderaUniversum.setString(2, "Hellboy Universe");
                            selectedUniverse = "Hellboy Universe";
                        }
                        else if (radioKnapp3.isSelected()) {
                            adderaUniversum.setString(2, "Marvel");
                            selectedUniverse = "Marvel";
                        }

                        //Execute update
                        adderaMedlem.executeUpdate();
                        adderaUniversum.executeUpdate();
                        //Lägg in superhero i arraylist med de inmatade texterna samt valda universumet
                        superfigurArrayList.add(new Superfigur(alias2.getText(), namn.getText(), efternamn.getText(), inriktning2.getText(), beskrivning2.getText(), selectedUniverse));

                        //Cleara listviewen och hämta arraylist igen för uppdaterad lista
                        lv_visaMedlemmar.getItems().clear();
                        for (Superfigur s: superfigurArrayList) {
                            lv_visaMedlemmar.getItems().addAll(s.getAlias());
                        }

                        //Popuprutor
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Insättning lyckades");
                        alert.setContentText("Du har nu lagt till en ny medlem.");
                        alert.showAndWait();
                    } catch (SQLIntegrityConstraintViolationException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Insättning misslyckades");
                        alert.setContentText("Du har försökt lägga till en redan inlagd medlem.");
                        alert.showAndWait();
                    }

                } } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }

        });

        vertikal.getChildren().addAll(alias2, namn, efternamn, inriktning2, beskrivning2, selectUniverse,radioKnapp, radioKnapp2, radioKnapp3, addera);

        //Visar lägg till medlem-layout
        knapp2.setOnAction(event -> {
            root.getChildren().clear();
            root.setTop(toolBar);
            root.setLeft(vertikal);
        });

        window.setScene(scene);
        window.setTitle("Program");
        window.show();
    }
}
