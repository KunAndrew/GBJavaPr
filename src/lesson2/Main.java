package lesson2;

import java.sql.*;

public class Main {

    private static Statement statement;
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/Lesson2/mainDB.db");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            statement.execute("CREATE TABLE IF NOT EXISTS goods (\n" +
                    "good_id integer PRIMARY KEY,\n" +
                    "good_name VARCHAR NOT NULL,\n" +
                    "good_price int NOT NULL" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
            statement.execute("DELETE FROM goods");


        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            statement.addBatch("INSERT INTO goods (good_name, good_price) VALUES ('Name" + (i+1) + "', " + (i+1) + ")");
        }
        statement.executeBatch();
        connection.setAutoCommit(true);

        findPrice("Name1");
        replacePrice("Name1", "2");

        findGoodInPrice(50,60);

        connection.close();
    }
    private static void findPrice(String s) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT good_price FROM goods WHERE good_name = '" + s + "';");
        if (rs.next()) {
            System.out.println("Price: " + rs.getInt(1));
        } else {
            System.out.println("Product not found!");
        }
    }
    private static void replacePrice(String name, String price) throws SQLException {
        System.out.print("To replace ");
        findPrice(name);
        statement.executeUpdate("UPDATE goods SET good_price = '" + price + "' WHERE good_name = '" + name + "';");
        System.out.print("After replace ");
        findPrice(name);
    }
    private static void findGoodInPrice(int priceFrom, int priceTo) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT good_name,good_price FROM goods WHERE good_price >= '" + priceFrom + "' and good_price <= '" + priceTo + "';");

        System.out.println("good_name | good_price");
        while (rs.next()) {
            System.out.println(rs.getString("good_name") + " | " + rs.getInt(2));
        }
    }
}
