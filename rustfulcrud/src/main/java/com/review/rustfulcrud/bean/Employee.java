package com.review.rustfulcrud.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter@Setter
public class Employee implements Serializable {
    private Integer eid;
    private String lastName;
    private String email;
    private boolean gender;
    private Integer d_id;

    //外键
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", d_id=" + d_id +
                '}';
    }
}
