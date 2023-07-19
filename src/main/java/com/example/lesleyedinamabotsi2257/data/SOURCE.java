package com.example.lesleyedinamabotsi2257.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SOURCE {
    private Connection connection;

    public void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/inventory";
        String username = "DSA";
        String password = "DSA@ksarp123";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGood(Good good) {
        String query = "INSERT INTO goods (name, price, quantity, vendor_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, good.getName());
            statement.setDouble(2, good.getPrice());
            statement.setInt(3, good.getQuantity());
            statement.setInt(4, good.getVendor().getId());
            statement.executeUpdate();
            System.out.println("Good added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Good> getGoods() {
        List<Good> goods = new ArrayList<>();
        String query = "SELECT * FROM Goods";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int vendorId = resultSet.getInt("vendor_id");
                int category = resultSet.getInt("category");

                Vendor vendor = getVendorById(vendorId);

                Good good = new Good(id, name, price, quantity, category, vendor);
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goods;
    }

    public List<Vendor> getVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM vendors";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String contactNumber = resultSet.getString("contact_number");

                Vendor vendor = new Vendor(id, name, address, contactNumber);
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    public List<Bill> getBills() {
        // Implement retrieval of bills from the database
        // similar to the getGoods() and getVendors() methods.
        // Return a list of Bill objects.
        return null;
    }

    public List<IssuedGood> getIssuedGoods() {
        // Implement retrieval of issued goods from the database
        // similar to the getGoods() and getVendors() methods.
        // Return a list of IssuedGood objects.
        return null;
    }

    public void removeGood(int id){
        //implement code for removing a good by id from the database
        return;
    }

    public void updateGood(Good foundGood){
        //implement the code for updating the database with new goods
        return;
    }

    private Vendor getVendorById(int id) {
        // Implement retrieval of a vendor by its ID from the database
        // Return a Vendor object based on the retrieved data.
        return null;
    }

}

