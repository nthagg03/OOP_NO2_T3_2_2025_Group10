package com.example.servingwebcontent.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.Model.Product;

@Service
public class productAiven {

    @Autowired
    private myConnection myConnection;

    public ArrayList<Product> listProducts() {
        ArrayList<Product> list = new ArrayList<>();
        try (Connection conn = myConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT productID, productName, price, stock, description, category FROM Products")) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getString("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getById(String id) {
        try (Connection conn = myConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT productID, productName, price, stock, description, category FROM Products WHERE productID = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getString("productID"));
                    p.setProductName(rs.getString("productName"));
                    p.setPrice(rs.getDouble("price"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("category"));
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Product p) {
        try (Connection conn = myConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO Products(productName, price, stock, description, category) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getCategory());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product p) {
        try (Connection conn = myConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE Products SET productName=?, price=?, stock=?, description=?, category=? WHERE productID=?")) {
            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getCategory());
            ps.setString(6, p.getProductId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try (Connection conn = myConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Products WHERE productID = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
