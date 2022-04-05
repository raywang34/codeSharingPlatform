package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Arrays;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);

//        String url = "jdbc:sqlite:D:/SQLite/westeros.db";
//        SQLiteDataSource dataSource = new SQLiteDataSource();
//        dataSource.setUrl(url);

        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:6555;databaseName=westeros;user=jon;password=snowsnowsnow!";

        try (Connection con = /*dataSource*/DriverManager.getConnection(url)) {
            // Statement creation
            try (Statement statement = con.createStatement()) {
                // Statement execution
//                statement.executeUpdate("CREATE TABLE HOUSES(" +
//                        "id INT PRIMARY KEY," +
//                        "name VARCHAR(50) NOT NULL," +
//                        "words VARCHAR(50) NOT NULL)");
//
//                int i = statement.executeUpdate("INSERT INTO HOUSES VALUES " +
//                        "(1, 'Targaryen of King''s Landing', 'Fire and Blood')," +
//                        "(2, 'Stark of Winterfell', 'Summer is Coming')," +
//                        "(3, 'Lannister of Casterly Rock', 'Hear Me Roar!')");
//
//                int u = statement.executeUpdate("UPDATE HOUSES " +
//                        "SET words = 'Winter is coming' " +
//                        "WHERE id = 2");

                String insert = "INSERT INTO HOUSES (id, name, words) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
                    preparedStatement.setInt(1, 4);
                    preparedStatement.setString(2, "Baratheon");
                    preparedStatement.setString(3, "Ours is the Fury");

                    preparedStatement.executeUpdate();
                }

//                try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
//                    preparedStatement.setInt(1, 4);
//                    preparedStatement.setObject(2, new StringBuilder("Greyjoy"));
//                    preparedStatement.setObject(3, new Long(88));
//
//                    preparedStatement.executeUpdate();
//                }

                String updateOrigin = "UPDATE HOUSES SET words = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = con.prepareStatement(updateOrigin)) {
                    //preparedStatement.setNull(1, Types.VARCHAR);
                    preparedStatement.setString(1, "You know nothing, Jon Snow.");
                    preparedStatement.setInt(2, 2);

                    preparedStatement.executeUpdate();
                }

                try (ResultSet greatHouses = statement.executeQuery("SELECT * FROM HOUSES")) {
                    while (greatHouses.next()) {
                        // Retrieve column values
                        int id = greatHouses.getInt("id");
                        String name = greatHouses.getString("name");
                        String words = greatHouses.getString("words");

                        System.out.printf("House %d%n", id);
                        System.out.printf("\tName: %s%n", name);
                        System.out.printf("\tWords: %s%n", words);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}