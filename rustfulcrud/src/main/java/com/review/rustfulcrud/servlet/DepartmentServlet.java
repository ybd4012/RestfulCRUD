package com.review.rustfulcrud.servlet;

import com.review.rustfulcrud.bean.Department;
import com.review.rustfulcrud.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServlet {
    @Autowired
    DepartmentMapper departmentMapper;
    /**
     * 查询所有
     */
    public List<Department> getNCAllDepartment(){
        return departmentMapper.getNCAllDepartment();
    }
}
