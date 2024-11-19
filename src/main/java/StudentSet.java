package com.students.model;

import java.util.TreeSet;

public class StudentSet extends TreeSet<StudentPO> {
    @Override
    public boolean add(StudentPO student) {
        return super.add(student);
    }
}