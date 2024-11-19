package com.addressbook.model;
public class FriendPO implements Comparable<FriendPO> {
    private String name;
    private String phone;
    private String email;
    private String address;

    public FriendPO(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(FriendPO other) {
        return this.phone.compareTo(other.phone);
    }
    @Override
    public String toString() {
        return "FriendPO{name='" + name + "', phone='" + phone + "', email='" + email + "', address='" + address + "'}";
    }
}