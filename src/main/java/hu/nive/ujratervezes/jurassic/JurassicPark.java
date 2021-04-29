package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.DB_URL = dbUrl;
        this.DB_USER = dbUser;
        this.DB_PASSWORD = dbPassword;
    }


    public List<String> checkOverpopulation() {

        List<String> overpopulatedSpecies = new ArrayList<>();

        String query =
            "SELECT * " +
            "FROM dinosaur " +
            "WHERE actual > expected " +
            "ORDER BY breed;";

        try(Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                 overpopulatedSpecies.add(rs.getString(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overpopulatedSpecies;

    }


    public Connection getConnection() throws SQLException {

        Connection connection;

        try {
            connection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
        } catch (SQLException e) {
            throw new SQLException();
        }

        return connection;

    }

}
