package com.review.rustfulcrud.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Department implements Serializable {
    private Integer d_id;
    private String departmentName;
    //主键所关联的外键
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Department{" +
                "d_id=" + d_id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
