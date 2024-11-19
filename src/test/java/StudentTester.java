package com.students.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.sql.*;
import java.util.*;

public class StudentTester {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        // Initialize Excel file
        String excelFilePath = "E:\\NEU\\2024\\Herbst\\T1\\JAVA\\prj\\addressBook\\src\\main\\resources\\studentList.xls";
        List<StudentPO> students = readExcelFile(excelFilePath);
        studentDAO.insertStudents(students); // 使用批量插入

        // Create database table
        createStudentTable();

        // Import students from XML file
        String xmlFilePath = "E:\\NEU\\2024\\Herbst\\T1\\JAVA\\prj\\addressBook\\src\\main\\resources\\students.xml";
        studentDAO.importStudentsFromXML(xmlFilePath);

        // Test CRUD operations
        StudentPO testStudent = new StudentPO("001", "Test Student", "Male", "1234567890", "test@example.com", "Engineering", "Computer Science");
        studentDAO.insertStudent(testStudent);
        System.out.println("Test student added: " + testStudent);

        testStudent.setMajor("Updated Major");
        studentDAO.updateStudent(testStudent);
        System.out.println("Test student updated: " + testStudent);

        studentDAO.deleteStudent("001");
        System.out.println("Test student deleted.");

        // Get all students
        List<StudentPO> allStudents = studentDAO.getAllStudents();
        System.out.println("All students:");
        allStudents.forEach(System.out::println);
    }

    private static List<StudentPO> readExcelFile(String filePath) {
        List<StudentPO> students = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String studentId = getCellValue(row.getCell(0));
                String name = getCellValue(row.getCell(1));
                String gender = getCellValue(row.getCell(2));
                String phone = getCellValue(row.getCell(3));
                String email = getCellValue(row.getCell(4));
                String college = getCellValue(row.getCell(5));
                String major = getCellValue(row.getCell(6));

                students.add(new StudentPO(studentId, name, gender, phone, email, college, major));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static void createStudentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "studentId VARCHAR(255) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "gender VARCHAR(255), " +
                "phone VARCHAR(255), " +
                "email VARCHAR(255), " +
                "college VARCHAR(255), " +
                "major VARCHAR(255))";
        try (Statement stmt = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "").createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}