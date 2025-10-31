package tr.com.huseyinaydin._01_select;

import java.sql.*;

public class MySqlConnection {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/huseyin_aydin_db",
                    "root",
                    "toor");

            statement = connection.createStatement();

            String sql1 = "SELECT * FROM musteriler";
            String sql2 = "SELECT * FROM musteriler";
            String sql3 = "SELECT * FROM musteriler WHERE yasi > 25";

            resultSet = statement.executeQuery(sql1);

            while (resultSet.next()) {
                /*
                System.out.println(resultSet.getString(1) + "  " +
                         resultSet.getString(2) + "  " +
                         resultSet.getString(3) + "  " +
                         resultSet.getString(4) + "  " +
                         resultSet.getString(5));
                */
                // id, adi, soyadi, sehir, yasi
                System.out.println(
                        resultSet.getInt("id") + "  " +
                                resultSet.getString("adi") + "  " +
                                resultSet.getString("soyadi") + "  " +
                                resultSet.getString("sehir") + "  " +
                                resultSet.getInt("yasi"));
                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        } finally {
            if (resultSet != null || statement != null || connection != null) {
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}