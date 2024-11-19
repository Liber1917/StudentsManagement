package com.students.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection conn;

    public StudentDAO() {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "";
            this.conn = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(StudentPO student) {
        String sql = "INSERT INTO students (studentId, name, gender, phone, email, college, major) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getCollege());
            pstmt.setString(7, student.getMajor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE studentId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(StudentPO student) {
        String sql = "UPDATE students SET name = ?, gender = ?, phone = ?, email = ?, college = ?, major = ? WHERE studentId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getCollege());
            pstmt.setString(6, student.getMajor());
            pstmt.setString(7, student.getStudentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentPO> getAllStudents() {
        List<StudentPO> students = new ArrayList<>();
        String sql = "SELECT studentId, name, gender, phone, email, college, major FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String college = rs.getString("college");
                String major = rs.getString("major");
                students.add(new StudentPO(studentId, name, gender, phone, email, college, major));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 添加批量插入学生的方法
    public void insertStudents(List<StudentPO> students) {
        String sql = "INSERT INTO students (studentId, name, gender, phone, email, college, major) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (StudentPO student : students) {
                pstmt.setString(1, student.getStudentId());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getGender());
                pstmt.setString(4, student.getPhone());
                pstmt.setString(5, student.getEmail());
                pstmt.setString(6, student.getCollege());
                pstmt.setString(7, student.getMajor());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}