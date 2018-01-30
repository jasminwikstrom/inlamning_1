package pack;

import model.Bestallning;
import model.Kund;
import model.Product;
import queries.Queries;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Repository {

    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Kund> getTotalFromKund(String kundId) throws SQLException {

        List<Kund> kundlistan = new ArrayList<>();
        ResultSet rs = null;
        String query = null;

        Connection connect = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

        PreparedStatement statement = null;
        if (kundId != null) {
            query = Queries.totalOneCustomer(kundId);
            statement = connect.prepareStatement(query);

        } else {
            query = Queries.totalAllCustomers();
            statement = connect.prepareStatement(query);
        }

        rs = statement.executeQuery(query);


        String user = null;

        while (rs.next()) {
            kundlistan.add(
                    new Kund(
                            rs.getInt("kund.id"),
                            rs.getString("fornamn"),
                            rs.getString("efternamn"),
                            rs.getBigDecimal("totalt_belopp")
                    )
            );
        }
        connect.close();
        return kundlistan;
    }

    public List<Product> getProductsPerCategory() throws SQLException {

        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        String query = null;

        Connection connect = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

        PreparedStatement statement = null;

        query = Queries.productsPerCategory();
        statement = connect.prepareStatement(query);

        rs = statement.executeQuery(query);


        String user = null;

        while (rs.next()) {
            products.add(
                    new Product(
                            rs.getInt("produkt.id"),
                            rs.getString("produkt.namn"),
                            rs.getBigDecimal("produkt.storlek"),
                            rs.getString("produkt.farg"),
                            rs.getInt("kategori.id"),
                            rs.getString("kategori.namn")
                    )
            );
        }
        connect.close();
        return products;
    }

    public List<Product> getProductsInLager() throws SQLException {

        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        String query = null;

        Connection connect = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

        PreparedStatement statement = null;

        query = Queries.productsInLager();
        statement = connect.prepareStatement(query);

        rs = statement.executeQuery(query);


        String user = null;

        while (rs.next()) {
            products.add(
                    new Product(
                            rs.getInt("produkt.id"),
                            rs.getString("produkt.namn"),
                            rs.getBigDecimal("produkt.storlek"),
                            rs.getString("produkt.farg"),
                            rs.getInt("kategori.id"),
                            rs.getString("kategori.namn")
                    )
            );
        }
        connect.close();
        return products;
    }

    public List<Bestallning> getEjExpideradeBetallningar(String kundId) throws SQLException {

        List<Bestallning> bestallnings = new ArrayList<>();
        ResultSet rs = null;
        String query = null;

        Connection connect = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

        PreparedStatement statement = null;

        query = Queries.ejExpideradeBestallning(kundId);
        statement = connect.prepareStatement(query);

        rs = statement.executeQuery(query);


        String user = null;

        while (rs.next()) {
            bestallnings.add(
                    new Bestallning(
                            rs.getInt("bestallning.id"),
                            rs.getDate("bestallning.bestallningsdatum").toLocalDate()
                    )
            );
        }
        connect.close();
        return bestallnings;
    }

    public void addBestallningViaStoredProcedure(String kundId, String bestallningsId, String produktId) {

        ResultSet rs = null;
        String query = null;

        try {


            Connection connect = DriverManager.getConnection(
                    p.getProperty("connectionString"),
                    p.getProperty("name"),
                    p.getProperty("password"));

            CallableStatement cStmt = connect.prepareCall(
                    "call addtocart(?, ?, ?)"
            );
            cStmt.setInt(1, Integer.valueOf(kundId));

            if (bestallningsId != null) {
                cStmt.setInt(2, Integer.valueOf(bestallningsId));
            } else {
                cStmt.setNull(2, Types.INTEGER);
            }
            cStmt.setInt(3, Integer.valueOf(produktId));


            cStmt.execute();
            rs = cStmt.getResultSet();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {

                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + "  " + columnValue);
                }

            }
            cStmt.close();
            connect.close();
        } catch (SQLException e) {

            e.printStackTrace();

        }
    }
}
