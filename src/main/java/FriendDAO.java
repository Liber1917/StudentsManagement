package com.addressbook.model;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.addressbook.model.*;
public class FriendDAO {
    private Connection conn;

    public FriendDAO() {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/friends";
            String username = "root";
            String password = "";
            this.conn = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 从数据库获取所有朋友
    public List<FriendPO> getAllFriends() {
        List<FriendPO> friends = new ArrayList<>();
        String sql = "SELECT name, phone, email, address FROM friends";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                friends.add(new FriendPO(name, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    // 插入朋友到数据库
    public void insertFriend(FriendPO friend) {
        String sql = "INSERT INTO friends (name, phone, email, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, friend.getName());
            pstmt.setString(2, friend.getPhone());
            pstmt.setString(3, friend.getEmail());
            pstmt.setString(4, friend.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新朋友地址
    public void updateFriendAddress(String name, String newAddress) {
        String sql = "UPDATE friends SET address = ? WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newAddress);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除朋友
    public void deleteFriend(String name) {
        String sql = "DELETE FROM friends WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
