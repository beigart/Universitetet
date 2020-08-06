package Labb3;

import java.sql.*;

public class Uppgift1 {
    public static void main(String[] args) {

        //Hämta drivrutin, skapa connection, påstående till databas, lagra resultat
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

            System.out.println("Artister\n=========\n");

            while (result.next()) {
                System.out.println(result.getString(1) + "(" + result.getString(2) +
                        ") : " + result.getString(3));
            }
            System.out.println();

            ResultSet result2 = statement.executeQuery("SELECT * from Album");

            System.out.println("Album\n=======\n");

            while (result2.next()){
                System.out.println(result2.getString(1) + "(" + result2.getString(2) +
                        ") : " + result2.getString(3) );
            }

        } catch (SQLException ex) {
            System.out.println("Något gick fel");
        }
    }
}
