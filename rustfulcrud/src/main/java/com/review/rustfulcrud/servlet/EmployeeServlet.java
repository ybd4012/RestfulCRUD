package com.review.rustfulcrud.servlet;

import com.review.rustfulcrud.bean.Employee;
import com.review.rustfulcrud.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServlet {
    @Autowired
    EmployeeMapper employeeMapper;
    @Cacheable(cacheNames = "emps",key = "#eid")
    //通过eid查询一个员工和关联的部门
    public Employee getEmployee(Integer eid){return employeeMapper.getEmployee(eid);}

    @CacheEvict(cacheNames = "emps",key = "#eid")
    //删除员工 通过eid
    public void deleteEmpByEid(Integer eid){employeeMapper.deleteEmpByEid(eid);}

    @CachePut(cacheNames = "emps",key = "#result.eid")
    //修改员工
    public Employee updateEmp(Employee employee){employeeMapper.updateEmp(employee);return employee;}

    //查询所有员工以及被关联的部门
    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }
    //添加员工 eid自增
    public void addEpmployee(Employee employee){
        employeeMapper.addEmployee(employee);
    }
}
