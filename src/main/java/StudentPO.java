package com.students.model;

public class StudentPO {
    private String studentId;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private String college;
    private String major;

    public StudentPO(String studentId, String name, String gender, String phone, String email, String college, String major) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.college = college;
        this.major = major;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    @Override
    public String toString() {
        return "StudentPO{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}